package com.negoreserva.common.feature.concrete.transaction.enums;

import com.negoreserva.common.feature.concrete.product.enums.ProductData;
import com.negoreserva.common.feature.concrete.transaction.model.Transaction;
import com.negoreserva.common.feature.concrete.user.enums.UserData;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionData {
    L1(Transaction.builder().user(UserData.ANA.getUser()).product(ProductData.POUSADA_CHALE.getProduct()).code("FK000001").build()),
    L10(Transaction.builder().user(UserData.ANA.getUser()).product(ProductData.RESTAURANTE_JANTAR_EXECUTIVO.getProduct()).code("FK000002").build()),
    L7(Transaction.builder().user(UserData.ANA.getUser()).product(ProductData.HOTEL_STANDARD_2.getProduct()).code("FK000003").build()),
    L16(Transaction.builder().user(UserData.ANA.getUser()).product(ProductData.ACME_PRESIDENTIAL.getProduct()).code("FK000004").build()),
    L2(Transaction.builder().user(UserData.BOB.getUser()).product(ProductData.ACME_PRESIDENTIAL.getProduct()).code("FK000005").build()),
    L6(Transaction.builder().user(UserData.BOB.getUser()).product(ProductData.HOTEL_DELUXE_3.getProduct()).code("FK000006").build()),
    L15(Transaction.builder().user(UserData.BOB.getUser()).product(ProductData.LOJA_HORTIFRUTI.getProduct()).code("FK000007").build()),
    L11(Transaction.builder().user(UserData.BOB.getUser()).product(ProductData.POUSADA_QUARTO_RUSTICO.getProduct()).code("FK000008").build()),
    L3(Transaction.builder().user(UserData.CARLOS.getUser()).product(ProductData.GLOBAL_EVENT_SPACE.getProduct()).code("FK000009").build()),
    L8(Transaction.builder().user(UserData.CARLOS.getUser()).product(ProductData.HOTEL_STANDARD_1.getProduct()).code("FK000010").build()),
    L12(Transaction.builder().user(UserData.CARLOS.getUser()).product(ProductData.TECHCORP_COWORKING.getProduct()).code("FK000011").build()),
    L17(Transaction.builder().user(UserData.CARLOS.getUser()).product(ProductData.LOJA_HORTIFRUTI.getProduct()).code("FK000012").build()),
    L4(Transaction.builder().user(UserData.JANE.getUser()).product(ProductData.HOTEL_DELUXE_1.getProduct()).code("FK000013").build()),
    L13(Transaction.builder().user(UserData.JANE.getUser()).product(ProductData.RESTAURANTE_CHEF_TABLE.getProduct()).code("FK000014").build()),
    L14(Transaction.builder().user(UserData.JOHN.getUser()).product(ProductData.RESTAURANTE_JANTAR_EXECUTIVO.getProduct()).code("FK000015").build()),
    L5(Transaction.builder().user(UserData.JOHN.getUser()).product(ProductData.HOTEL_DELUXE_2.getProduct()).code("FK000016").build()),
    L9(Transaction.builder().user(UserData.PEDRO.getUser()).product(ProductData.HOTEL_STANDARD_3.getProduct()).code("FK000017").build()),
    L18(Transaction.builder().user(UserData.PEDRO.getUser()).product(ProductData.PENSAO_QUARTO_SIMPLES.getProduct()).code("FK000018").build());

    private final Transaction transaction;
}
