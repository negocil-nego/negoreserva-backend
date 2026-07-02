package com.negoreserva.common.feature.concrete.payment.model;

import com.negoreserva.common.feature.concrete.payment.model.Payment;
import com.negoreserva.common.feature.core.model.ConcreteModel;
import com.negoreserva.common.variable.EntityVariable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = EntityVariable.PAYMENT_FILE_RECEIPT)
public class PaymentFileReceipt extends ConcreteModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @NotBlank
    @Size(max = 255)
    private String fileUrl;

    @Size(max = 100)
    private String type;

    private Long size;
}
