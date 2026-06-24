package com.negoreserva.common.feature.concrete.product_tag_info.enums;

import com.negoreserva.common.enums.HugeiconNamed;
import com.negoreserva.common.feature.concrete.product.enums.ProductData;
import com.negoreserva.common.feature.concrete.product_tag_info.model.ProductTagInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductTagInfoFaker {

    // --- HOTEL DELUXE 1 ---
    HOTEL_DELUXE_1_QUARTO(
            ProductTagInfo.builder()
                    .product(ProductData.HOTEL_DELUXE_1.getProduct())
                    .icon(HugeiconNamed.CURTAINS.getWeb())
                    .title("Quarto")
                    .value("3")
                    .build()
    ),
    HOTEL_DELUXE_1_BANHEIRO(
            ProductTagInfo.builder()
                    .product(ProductData.HOTEL_DELUXE_1.getProduct())
                    .icon(HugeiconNamed.HOT_TUB.getWeb())
                    .title("Casa de Banho")
                    .value("2")
                    .build()
    ),
    HOTEL_DELUXE_1_SALA(
            ProductTagInfo.builder()
                    .product(ProductData.HOTEL_DELUXE_1.getProduct())
                    .icon(HugeiconNamed.SOFA_02.getWeb())
                    .title("Sala de Estar")
                    .value("1")
                    .build()
    ),

    // --- HOTEL DELUXE 2 ---
    HOTEL_DELUXE_2_QUARTO(
            ProductTagInfo.builder()
                    .product(ProductData.HOTEL_DELUXE_2.getProduct())
                    .icon(HugeiconNamed.CURTAINS.getWeb())
                    .title("Quarto")
                    .value("3")
                    .build()
    ),
    HOTEL_DELUXE_2_BANHEIRO(
            ProductTagInfo.builder()
                    .product(ProductData.HOTEL_DELUXE_2.getProduct())
                    .icon(HugeiconNamed.HOT_TUB.getWeb())
                    .title("Casa de Banho")
                    .value("1")
                    .build()
    ),

    // --- HOTEL DELUXE 3 ---
    HOTEL_DELUXE_3_QUARTO(
            ProductTagInfo.builder()
                    .product(ProductData.HOTEL_DELUXE_3.getProduct())
                    .icon(HugeiconNamed.CURTAINS.getWeb())
                    .title("Quarto")
                    .value("3")
                    .build()
    ),
    HOTEL_DELUXE_3_BANHEIRO(
            ProductTagInfo.builder()
                    .product(ProductData.HOTEL_DELUXE_3.getProduct())
                    .icon(HugeiconNamed.HOT_TUB.getWeb())
                    .title("Casa de Banho")
                    .value("2")
                    .build()
    ),

    // --- HOTEL STANDARD 1 ---
    HOTEL_STANDARD_1_QUARTO(
            ProductTagInfo.builder()
                    .product(ProductData.HOTEL_STANDARD_1.getProduct())
                    .icon(HugeiconNamed.CURTAINS.getWeb())
                    .title("Quarto")
                    .value("3")
                    .build()
    ),
    HOTEL_STANDARD_1_BANHEIRO(
            ProductTagInfo.builder()
                    .product(ProductData.HOTEL_STANDARD_1.getProduct())
                    .icon(HugeiconNamed.HOT_TUB.getWeb())
                    .title("Casa de Banho")
                    .value("1")
                    .build()
    ),

    // --- HOTEL STANDARD 2 ---
    HOTEL_STANDARD_2_QUARTO(
            ProductTagInfo.builder()
                    .product(ProductData.HOTEL_STANDARD_2.getProduct())
                    .icon(HugeiconNamed.CURTAINS.getWeb())
                    .title("Quarto")
                    .value("3")
                    .build()
    ),
    HOTEL_STANDARD_2_BANHEIRO(
            ProductTagInfo.builder()
                    .product(ProductData.HOTEL_STANDARD_2.getProduct())
                    .icon(HugeiconNamed.HOT_TUB.getWeb())
                    .title("Casa de Banho")
                    .value("1")
                    .build()
    ),

    // --- HOTEL STANDARD 3 ---
    HOTEL_STANDARD_3_QUARTO(
            ProductTagInfo.builder()
                    .product(ProductData.HOTEL_STANDARD_3.getProduct())
                    .icon(HugeiconNamed.CURTAINS.getWeb())
                    .title("Quarto")
                    .value("3")
                    .build()
    ),
    HOTEL_STANDARD_3_BANHEIRO(
            ProductTagInfo.builder()
                    .product(ProductData.HOTEL_STANDARD_3.getProduct())
                    .icon(HugeiconNamed.HOT_TUB.getWeb())
                    .title("Casa de Banho")
                    .value("2")
                    .build()
    ),

    // --- HOTEL TECHCORP 1 ---
    HOTEL_TECHCORP_1_QUARTO(
            ProductTagInfo.builder()
                    .product(ProductData.HOTEL_TECHCORP_1.getProduct())
                    .icon(HugeiconNamed.CURTAINS.getWeb())
                    .title("Quarto")
                    .value("3")
                    .build()
    ),
    HOTEL_TECHCORP_1_BANHEIRO(
            ProductTagInfo.builder()
                    .product(ProductData.HOTEL_TECHCORP_1.getProduct())
                    .icon(HugeiconNamed.HOT_TUB.getWeb())
                    .title("Casa de Banho")
                    .value("1")
                    .build()
    ),
    HOTEL_TECHCORP_1_SALA(
            ProductTagInfo.builder()
                    .product(ProductData.HOTEL_TECHCORP_1.getProduct())
                    .icon(HugeiconNamed.SOFA_02.getWeb())
                    .title("Sala de Estar")
                    .value("1")
                    .build()
    ),

    // --- HOTEL TECHCORP 2 ---
    HOTEL_TECHCORP_2_QUARTO(
            ProductTagInfo.builder()
                    .product(ProductData.HOTEL_TECHCORP_2.getProduct())
                    .icon(HugeiconNamed.CURTAINS.getWeb())
                    .title("Quarto")
                    .value("3")
                    .build()
    ),
    HOTEL_TECHCORP_2_BANHEIRO(
            ProductTagInfo.builder()
                    .product(ProductData.HOTEL_TECHCORP_2.getProduct())
                    .icon(HugeiconNamed.HOT_TUB.getWeb())
                    .title("Casa de Banho")
                    .value("2")
                    .build()
    ),

    // --- HOTEL TECHCORP 3 ---
    HOTEL_TECHCORP_3_QUARTO(
            ProductTagInfo.builder()
                    .product(ProductData.HOTEL_TECHCORP_3.getProduct())
                    .icon(HugeiconNamed.CURTAINS.getWeb())
                    .title("Quarto")
                    .value("3")
                    .build()
    ),
    HOTEL_TECHCORP_3_BANHEIRO(
            ProductTagInfo.builder()
                    .product(ProductData.HOTEL_TECHCORP_3.getProduct())
                    .icon(HugeiconNamed.HOT_TUB.getWeb())
                    .title("Casa de Banho")
                    .value("1")
                    .build()
    ),

    // --- ACME PRESIDENTIAL ---
    ACME_PRESIDENTIAL_QUARTO(
            ProductTagInfo.builder()
                    .product(ProductData.ACME_PRESIDENTIAL.getProduct())
                    .icon(HugeiconNamed.CURTAINS.getWeb())
                    .title("Quarto")
                    .value("2")
                    .build()
    ),
    ACME_PRESIDENTIAL_BANHEIRO(
            ProductTagInfo.builder()
                    .product(ProductData.ACME_PRESIDENTIAL.getProduct())
                    .icon(HugeiconNamed.HOT_TUB.getWeb())
                    .title("Casa de Banho")
                    .value("3")
                    .build()
    ),
    ACME_PRESIDENTIAL_SALA(
            ProductTagInfo.builder()
                    .product(ProductData.ACME_PRESIDENTIAL.getProduct())
                    .icon(HugeiconNamed.SOFA_02.getWeb())
                    .title("Sala de Estar")
                    .value("2")
                    .build()
    ),
    ACME_PRESIDENTIAL_PISCINA(
            ProductTagInfo.builder()
                    .product(ProductData.ACME_PRESIDENTIAL.getProduct())
                    .icon(HugeiconNamed.HOT_TUB.getWeb())
                    .title("Piscina Privativa")
                    .value("1")
                    .build()
    ),

    // --- TECHCORP COWORKING ---
    TECHCORP_COWORKING_MESA(
            ProductTagInfo.builder()
                    .product(ProductData.TECHCORP_COWORKING.getProduct())
                    .icon(HugeiconNamed.SOFA_02.getWeb())
                    .title("Estações de Trabalho")
                    .value("20")
                    .build()
    ),
    TECHCORP_COWORKING_SALA(
            ProductTagInfo.builder()
                    .product(ProductData.TECHCORP_COWORKING.getProduct())
                    .icon(HugeiconNamed.HOTEL_01.getWeb())
                    .title("Salas de Reunião")
                    .value("4")
                    .build()
    ),

    // --- GLOBAL EVENT SPACE ---
    GLOBAL_EVENT_CAPACIDADE(
            ProductTagInfo.builder()
                    .product(ProductData.GLOBAL_EVENT_SPACE.getProduct())
                    .icon(HugeiconNamed.SOFA_02.getWeb())
                    .title("Capacidade")
                    .value("300 pessoas")
                    .build()
    ),
    GLOBAL_EVENT_PALCO(
            ProductTagInfo.builder()
                    .product(ProductData.GLOBAL_EVENT_SPACE.getProduct())
                    .icon(HugeiconNamed.RESTAURANT_01.getWeb())
                    .title("Palco Profissional")
                    .value("1")
                    .build()
    ),

    // --- POUSADA RECANTO VERDE ---
    POUSADA_RUSTICO_QUARTO(
            ProductTagInfo.builder()
                    .product(ProductData.POUSADA_QUARTO_RUSTICO.getProduct())
                    .icon(HugeiconNamed.CURTAINS.getWeb())
                    .title("Quarto")
                    .value("1")
                    .build()
    ),
    POUSADA_RUSTICO_BANHEIRO(
            ProductTagInfo.builder()
                    .product(ProductData.POUSADA_QUARTO_RUSTICO.getProduct())
                    .icon(HugeiconNamed.HOT_TUB.getWeb())
                    .title("Casa de Banho")
                    .value("1")
                    .build()
    ),
    POUSADA_RUSTICO_LAREIRA(
            ProductTagInfo.builder()
                    .product(ProductData.POUSADA_QUARTO_RUSTICO.getProduct())
                    .icon(HugeiconNamed.HOTEL_02.getWeb())
                    .title("Lareira")
                    .value("1")
                    .build()
    ),

    POUSADA_SUITE_QUARTO(
            ProductTagInfo.builder()
                    .product(ProductData.POUSADA_SUITE_MASTER.getProduct())
                    .icon(HugeiconNamed.CURTAINS.getWeb())
                    .title("Quarto")
                    .value("1")
                    .build()
    ),
    POUSADA_SUITE_BANHEIRO(
            ProductTagInfo.builder()
                    .product(ProductData.POUSADA_SUITE_MASTER.getProduct())
                    .icon(HugeiconNamed.HOT_TUB.getWeb())
                    .title("Casa de Banho")
                    .value("2")
                    .build()
    ),
    POUSADA_SUITE_HIDRO(
            ProductTagInfo.builder()
                    .product(ProductData.POUSADA_SUITE_MASTER.getProduct())
                    .icon(HugeiconNamed.HOT_TUB.getWeb())
                    .title("Hidromassagem")
                    .value("1")
                    .build()
    ),

    POUSADA_CHALE_QUARTO(
            ProductTagInfo.builder()
                    .product(ProductData.POUSADA_CHALE.getProduct())
                    .icon(HugeiconNamed.CURTAINS.getWeb())
                    .title("Quartos")
                    .value("2")
                    .build()
    ),
    POUSADA_CHALE_BANHEIRO(
            ProductTagInfo.builder()
                    .product(ProductData.POUSADA_CHALE.getProduct())
                    .icon(HugeiconNamed.HOT_TUB.getWeb())
                    .title("Casa de Banho")
                    .value("2")
                    .build()
    ),
    POUSADA_CHALE_SALA(
            ProductTagInfo.builder()
                    .product(ProductData.POUSADA_CHALE.getProduct())
                    .icon(HugeiconNamed.SOFA_02.getWeb())
                    .title("Sala de Estar")
                    .value("1")
                    .build()
    ),
    POUSADA_CHALE_COZINHA(
            ProductTagInfo.builder()
                    .product(ProductData.POUSADA_CHALE.getProduct())
                    .icon(HugeiconNamed.HOTEL_01.getWeb())
                    .title("Cozinha")
                    .value("1")
                    .build()
    ),
    POUSADA_CHALE_OFURO(
            ProductTagInfo.builder()
                    .product(ProductData.POUSADA_CHALE.getProduct())
                    .icon(HugeiconNamed.HOT_TUB.getWeb())
                    .title("Ofurô")
                    .value("1")
                    .build()
    ),

    // --- PENSAO FAMILIAR ---
    PENSAO_SIMPLES_QUARTO(
            ProductTagInfo.builder()
                    .product(ProductData.PENSAO_QUARTO_SIMPLES.getProduct())
                    .icon(HugeiconNamed.CURTAINS.getWeb())
                    .title("Quarto")
                    .value("1")
                    .build()
    ),

    PENSAO_DUPLO_QUARTO(
            ProductTagInfo.builder()
                    .product(ProductData.PENSAO_QUARTO_DUPLO.getProduct())
                    .icon(HugeiconNamed.CURTAINS.getWeb())
                    .title("Quarto")
                    .value("1")
                    .build()
    ),
    PENSAO_DUPLO_BANHEIRO(
            ProductTagInfo.builder()
                    .product(ProductData.PENSAO_QUARTO_DUPLO.getProduct())
                    .icon(HugeiconNamed.HOT_TUB.getWeb())
                    .title("Casa de Banho")
                    .value("1")
                    .build()
    ),

    PENSAO_SUITE_QUARTO(
            ProductTagInfo.builder()
                    .product(ProductData.PENSAO_SUITE.getProduct())
                    .icon(HugeiconNamed.CURTAINS.getWeb())
                    .title("Quarto")
                    .value("1")
                    .build()
    ),
    PENSAO_SUITE_BANHEIRO(
            ProductTagInfo.builder()
                    .product(ProductData.PENSAO_SUITE.getProduct())
                    .icon(HugeiconNamed.HOT_TUB.getWeb())
                    .title("Casa de Banho")
                    .value("1")
                    .build()
    ),
    PENSAO_SUITE_VARANDA(
            ProductTagInfo.builder()
                    .product(ProductData.PENSAO_SUITE.getProduct())
                    .icon(HugeiconNamed.GUESTHOUSE.getWeb())
                    .title("Varanda")
                    .value("1")
                    .build()
    ),

    // --- RESTAURANTE SABOR & ARTE ---
    RESTAURANTE_JANTAR_TEMPOS(
            ProductTagInfo.builder()
                    .product(ProductData.RESTAURANTE_JANTAR_EXECUTIVO.getProduct())
                    .icon(HugeiconNamed.RESTAURANT.getWeb())
                    .title("Menu")
                    .value("4 tempos")
                    .build()
    ),
    RESTAURANTE_JANTAR_BEBIDA(
            ProductTagInfo.builder()
                    .product(ProductData.RESTAURANTE_JANTAR_EXECUTIVO.getProduct())
                    .icon(HugeiconNamed.RESTAURANT_01.getWeb())
                    .title("Bebida Inclusa")
                    .value("Sim")
                    .build()
    ),

    RESTAURANTE_CHEF_TEMPOS(
            ProductTagInfo.builder()
                    .product(ProductData.RESTAURANTE_CHEF_TABLE.getProduct())
                    .icon(HugeiconNamed.RESTAURANT.getWeb())
                    .title("Menu")
                    .value("7 tempos")
                    .build()
    ),
    RESTAURANTE_CHEF_VINHOS(
            ProductTagInfo.builder()
                    .product(ProductData.RESTAURANTE_CHEF_TABLE.getProduct())
                    .icon(HugeiconNamed.RESTAURANT_01.getWeb())
                    .title("Harmonização")
                    .value("Vinhos selecionados")
                    .build()
    ),
    RESTAURANTE_CHEF_PESSOAS(
            ProductTagInfo.builder()
                    .product(ProductData.RESTAURANTE_CHEF_TABLE.getProduct())
                    .icon(HugeiconNamed.SOFA_02.getWeb())
                    .title("Capacidade")
                    .value("Até 12 pessoas")
                    .build()
    ),

    RESTAURANTE_EVENTO_CAPACIDADE(
            ProductTagInfo.builder()
                    .product(ProductData.RESTAURANTE_EVENTO.getProduct())
                    .icon(HugeiconNamed.SOFA_02.getWeb())
                    .title("Capacidade")
                    .value("80 pessoas")
                    .build()
    ),

    // --- LOJA DO BAIRRO ---
    LOJA_CESTA_ITENS(
            ProductTagInfo.builder()
                    .product(ProductData.LOJA_CESTA_BASICA.getProduct())
                    .icon(HugeiconNamed.SHOPPING_BAG_03.getWeb())
                    .title("Itens")
                    .value("15 produtos")
                    .build()
    ),
    LOJA_CESTA_ENTREGA(
            ProductTagInfo.builder()
                    .product(ProductData.LOJA_CESTA_BASICA.getProduct())
                    .icon(HugeiconNamed.AIR_PLANE_TAKE_OFF_01.getWeb())
                    .title("Entrega")
                    .value("Grátis no bairro")
                    .build()
    ),

    LOJA_HORTIFRUTI_ITENS(
            ProductTagInfo.builder()
                    .product(ProductData.LOJA_HORTIFRUTI.getProduct())
                    .icon(HugeiconNamed.RESTAURANT.getWeb())
                    .title("Itens")
                    .value("10 variedades")
                    .build()
    ),
    LOJA_HORTIFRUTI_CERTIFICACAO(
            ProductTagInfo.builder()
                    .product(ProductData.LOJA_HORTIFRUTI.getProduct())
                    .icon(HugeiconNamed.SHOPPING_BAG_03.getWeb())
                    .title("Certificação")
                    .value("Orgânico")
                    .build()
    ),

    LOJA_PRESENTES_ITENS(
            ProductTagInfo.builder()
                    .product(ProductData.LOJA_PRESENTES.getProduct())
                    .icon(HugeiconNamed.SHOPPING_BAG_03.getWeb())
                    .title("Tipo")
                    .value("Kit luxo")
                    .build()
    ),
    LOJA_PRESENTES_EMBALAGEM(
            ProductTagInfo.builder()
                    .product(ProductData.LOJA_PRESENTES.getProduct())
                    .icon(HugeiconNamed.SHOPPING_BAG_03.getWeb())
                    .title("Embalagem")
                    .value("Presente")
                    .build()
    );

    private final ProductTagInfo productTagInfo;
}