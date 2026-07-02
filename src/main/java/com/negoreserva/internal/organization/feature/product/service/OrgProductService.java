package com.negoreserva.internal.organization.feature.product.service;

import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.product.repository.ProductRepository;
import com.negoreserva.common.feature.concrete.user.service.UserService;
import com.negoreserva.internal.admin.feature.product.dto.queryparam.ProductFilterQueryParam;
import com.negoreserva.internal.admin.feature.product.query.ProductFilterSpecification;
import com.negoreserva.common.feature.concrete.organization.usecase.OrgOrganizationUseCase;
import com.negoreserva.internal.organization.feature.product.dto.response.OrgProductPaginate;

import com.negoreserva.common.feature.concrete.product.exception.notfound.ProductNotFoundException;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
import com.negoreserva.common.feature.concrete.product.model.Product;
import com.negoreserva.common.feature.general.storage.service.StorageService;
import com.negoreserva.internal.organization.feature.product.dto.request.OrgProductCreateRequest;
import com.negoreserva.common.feature.concrete.product.model.ProductFile;
import com.negoreserva.common.feature.concrete.product.enums.ProductFileType;
import com.negoreserva.common.enums.StoragePathNamed;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

@Service
public class OrgProductService extends ConcreteService<Product> {
    private final ProductRepository productRepository;
    private final UserService userService;
    private final StorageService storageService;

    public OrgProductService(
            UserService userService,
            ProductRepository productRepository,
            StorageService storageService
    ) {
        super(productRepository);
        this.userService = userService;
        this.productRepository = productRepository;
        this.storageService = storageService;
    }

    public Organization find(Authentication authentication) {
        var usecase = new OrgOrganizationUseCase(authentication, userService);
        return usecase.applyUseCase();
    }

    public OrgProductPaginate paginate(Pageable pageable, Authentication authentication) {
        var organization = find(authentication);
        var page = productRepository.findAllByOrganization(organization, pageable);
        return OrgProductPaginate.of(page);
    }

    public OrgProductPaginate paginate(PaginateRequest paginateRequest, Authentication authentication) {
        return paginate(PageRequest.of(paginateRequest.pageNumber(), paginateRequest.pageSize()), authentication);
    }

    public OrgProductPaginate paginate(ProductFilterQueryParam filter, Authentication authentication) {
        var pageRequest = PageRequest.of(Optional.of(filter.getPageNumber()).orElse(0), Optional.of(filter.getPageSize()).orElse(10));
        var spec = new ProductFilterSpecification(filter);
        var organization = find(authentication);

        var page = productRepository.findAllByOrganization(organization, spec, pageRequest);

        return OrgProductPaginate.of(page);
    }

    @Override
    public Product findByUuid(UUID uuid) {
        return productRepository.findByUuid(uuid).orElseThrow(() -> new ProductNotFoundException(uuid));
    }

    public Product updateImageProduct(UUID uuid, MultipartFile file) {
        var product = findByUuid(uuid);
        var path = StoragePathNamed.PRODUCT_IMAGE.suffix(uuid);
        var url = storageService.uploadFile(file, path);
        product.setImage(url);
        return productRepository.save(product);
    }

    @Override
    public Product update(UUID uuid, Product product) {
        var item = findByUuid(uuid);
        item.setDescription(product.getDescription());
        item.setName(product.getName());
        return productRepository.save(item);
    }

    public Product createProduct(
            OrgProductCreateRequest request,
            List<MultipartFile> images,
            MultipartFile video,
            Authentication authentication
    ) {
        var organization = find(authentication);
        var product = Product.builder()
                .name(request.name())
                .description(request.description())
                .organization(organization)
                .productFiles(new ArrayList<>())
                .productPrices(new ArrayList<>())
                .productTagInfos(new ArrayList<>())
                .build();
        
        // Build and associate prices
        if (request.prices() != null) {
            for (var priceReq : request.prices()) {
                var price = priceReq.toModel();
                price.setProduct(product);
                product.getProductPrices().add(price);
            }
        }

        // Build and associate tags
        if (request.tags() != null) {
            for (var tagReq : request.tags()) {
                var tag = tagReq.toModel();
                tag.setProduct(product);
                product.getProductTagInfos().add(tag);
            }
        }

        // Save product first to have UUID for file storage path name
        product.applySlug();
        var savedProduct = productRepository.save(product);

        // Upload and associate images
        if (images != null) {
            for (int i = 0; i < images.size(); i++) {
                var imgFile = images.get(i);
                if (imgFile != null && !imgFile.isEmpty()) {
                    var path = StoragePathNamed.PRODUCT_IMAGE.suffix(savedProduct.getUuid());
                    var url = storageService.uploadFile(imgFile, path);
                    
                    var prodFile = ProductFile.builder()
                            .product(savedProduct)
                            .url(url)
                            .title("Image " + (i + 1))
                            .type(ProductFileType.IMAGE)
                            .isPrimary(i == 0) // first is primary
                            .build();
                    savedProduct.getProductFiles().add(prodFile);
                }
            }
        }

        // Upload and associate video
        if (video != null && !video.isEmpty()) {
            var path = StoragePathNamed.PRODUCT_VIDEO.suffix(savedProduct.getUuid());
            var url = storageService.uploadFile(video, path);
            
            var prodFile = ProductFile.builder()
                    .product(savedProduct)
                    .url(url)
                    .title("Video")
                    .type(ProductFileType.VIDEO)
                    .isPrimary(false)
                    .build();
            savedProduct.getProductFiles().add(prodFile);
        }

        // Resave product to save files
        return productRepository.save(savedProduct);
    }
}