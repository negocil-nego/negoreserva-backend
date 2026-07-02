package com.negoreserva.common.feature.concrete.payment.service;

import com.negoreserva.common.enums.StoragePathNamed;
import com.negoreserva.common.feature.concrete.payment.enums.PaymentMethod;
import com.negoreserva.common.feature.concrete.payment.repository.PaymentRepo;
import com.negoreserva.common.feature.concrete.payment.model.Payment;
import com.negoreserva.common.feature.concrete.payment.model.PaymentFileReceipt;
import com.negoreserva.common.feature.concrete.payment.repository.PaymentFileReceiptRepo;
import com.negoreserva.common.feature.concrete.product.service.ProductService;
import com.negoreserva.common.feature.concrete.product.service.ProductPriceService;
import com.negoreserva.common.feature.concrete.transaction.model.Transaction;
import com.negoreserva.common.feature.concrete.transaction.service.TransactionService;
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
public class PaymentReceiptService extends ConcreteService<Payment> {
    private final PaymentFileReceiptRepo paymentFileReceiptRepo;
    private final ProductPriceService productPriceService;
    private final TransactionService transactionService;
    private final ProductService productService;
    private final StorageService storageService;
    private final UserService userService;

    public PaymentReceiptService(
            PaymentRepo repository,
            UserService userService,
            StorageService storageService,
            ProductService productService,
            TransactionService transactionService,
            ProductPriceService productPriceService,
            PaymentFileReceiptRepo paymentFileReceiptRepo
    ) {
        super(repository);
        this.productService = productService;
        this.productPriceService = productPriceService;
        this.userService = userService;
        this.storageService = storageService;
        this.paymentFileReceiptRepo = paymentFileReceiptRepo;
        this.transactionService = transactionService;
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

        transaction = transactionService.save(transaction);

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
