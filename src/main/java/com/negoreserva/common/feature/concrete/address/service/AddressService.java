package com.negoreserva.common.feature.concrete.address.service;

import com.negoreserva.common.feature.concrete.address.dto.queryparam.AddressFilterQueryParam;
import com.negoreserva.common.feature.concrete.address.query.AddressFilterSpecification;
import com.negoreserva.common.feature.concrete.address.dto.response.AddressPaginate;
import com.negoreserva.common.feature.concrete.address.repository.AddressRepo;
import com.negoreserva.common.feature.concrete.address.model.Address;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService extends ConcreteService<Address> {
    private final AddressRepo repository;

    public AddressService(AddressRepo repository) {
        super(repository);
        this.repository = repository;
    }

    public AddressPaginate findAll(PaginateRequest paginateRequest) {
        var pageRequest = PageRequest.of(
                Optional.of(paginateRequest.pageNumber()).orElse(0),
                Optional.of(paginateRequest.pageSize()).orElse(10)
        );
        return AddressPaginate.of(repository.findAll(pageRequest));
    }

    public AddressPaginate findAll(AddressFilterQueryParam filter) {
        var pageRequest = PageRequest.of(
                Optional.of(filter.getPageNumber()).orElse(0),
                Optional.of(filter.getPageSize()).orElse(10)
        );
        var spec = new AddressFilterSpecification(filter);
        var page = findAll(spec, pageRequest);
        return AddressPaginate.of(page);
    }

    @Transactional
    public Address findOrCreate(Address address) {
        return repository.findByComplementAndProvinceAndMunicipality(
                address.getComplement(),
                address.getProvince(),
                address.getMunicipality()
        ).orElseGet(() -> save(address));
    }
}
