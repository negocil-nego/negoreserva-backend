package com.negoreserva.external.payment.api.rest;

import com.negoreserva.common.feature.concrete.payment.service.PaymentReceiptService;
import com.negoreserva.external.payment.dto.response.PaymentReceiptResponse;
import com.negoreserva.external.payment.util.PaymentRouteNamed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(PaymentRouteNamed.PATH)
@Tag(name = "Public - Payment", description = "Endpoints for public payments")
public class PubPaymentReceiptController {

    private final PaymentReceiptService paymentReceiptService;

    @PostMapping(value = PaymentRouteNamed.RECEIPT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Create payment with receipt comprovativo")
    public ResponseEntity<PaymentReceiptResponse> createPaymentWithReceipt(
            @RequestPart("file") MultipartFile file,
            @RequestParam("productUuid") UUID productUuid,
            @RequestParam("priceUuid") UUID priceUuid,
            @RequestParam("amount") Integer amount,
            Authentication authentication
    ) {
        var payment = paymentReceiptService.createPaymentWithReceipt(file, productUuid, priceUuid, amount, authentication);
        return ResponseEntity.ok(PaymentReceiptResponse.of(payment));
    }
}
