package com.negoreserva.common.feature.concrete.payment.service;

import com.negoreserva.common.enums.StoragePathNamed;
import com.negoreserva.common.feature.concrete.payment.enums.PaymentMethod;
import com.negoreserva.common.feature.concrete.payment.repository.PaymentRepo;
import com.negoreserva.common.feature.concrete.payment.model.Payment;
import com.negoreserva.common.feature.concrete.payment_file_receipt.model.PaymentFileReceipt;
import com.negoreserva.common.feature.concrete.payment_file_receipt.repository.PaymentFileReceiptRepo;
import com.negoreserva.common.feature.concrete.product.service.ProductService;
import com.negoreserva.common.feature.concrete.product_price.service.ProductPriceService;
import com.negoreserva.common.feature.concrete.transaction.model.Transaction;
import com.negoreserva.common.feature.concrete.transaction.repository.TransactionRepo;
import com.negoreserva.common.feature.concrete.user.service.UserService;
import com.negoreserva.common.feature.core.service.ConcreteService;
import com.negoreserva.common.feature.general.storage.service.StorageService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PaymentService extends ConcreteService<Payment> {
    private final PaymentRepo repository;
    private final ProductService productService;
    private final ProductPriceService productPriceService;
    private final UserService userService;
    private final StorageService storageService;
    private final PaymentFileReceiptRepo paymentFileReceiptRepo;
    private final TransactionRepo transactionRepo;

    public PaymentService(
            PaymentRepo repository,
            ProductService productService,
            ProductPriceService productPriceService,
            UserService userService,
            StorageService storageService,
            PaymentFileReceiptRepo paymentFileReceiptRepo,
            TransactionRepo transactionRepo
    ) {
        super(repository);
        this.repository = repository;
        this.productService = productService;
        this.productPriceService = productPriceService;
        this.userService = userService;
        this.storageService = storageService;
        this.paymentFileReceiptRepo = paymentFileReceiptRepo;
        this.transactionRepo = transactionRepo;
    }

    @Transactional
    public Payment createPaymentWithReceipt(
            MultipartFile file,
            UUID productUuid,
            UUID priceUuid,
            Integer amount,
            Authentication authentication
    ) {
        var product = productService.findByUuid(productUuid);
        var user = userService.findBy(authentication);
        var price = productPriceService.findByUuid(priceUuid);

        var total = price.getValue().multiply(BigDecimal.valueOf(amount));

        var transaction = Transaction.builder()
                .product(product)
                .user(user)
                .amount(amount)
                .price(total)
                .build();
        transaction = transactionRepo.save(transaction);

        var payment = Payment.builder()
                .transaction(transaction)
                .type(PaymentMethod.RECEIPT)
                .build();
        payment = super.save(payment);

        var path = StoragePathNamed.PAYMENT_RECEIPT.suffix(payment.getUuid());
        var fileUrl = storageService.uploadFile(file, path);

        var receipt = PaymentFileReceipt.builder()
                .payment(payment)
                .fileUrl(fileUrl)
                .type(file.getContentType())
                .size(file.getSize())
                .build();
        paymentFileReceiptRepo.save(receipt);

        return payment;
    }
}
