package com.negoreserva.common.feature.concrete.category.enums;

import com.negoreserva.common.enums.HugeiconNamed;
import com.negoreserva.common.feature.concrete.category.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.random.RandomGenerator;

@Getter
@AllArgsConstructor
public enum CategoryData {
    HOTEL(
            Category.builder()
                    .name("Hotel")
                    .icon(HugeiconNamed.HOTEL_01.getWeb())
                    .description("Estabelecimento destinado à hospedagem, oferecendo acomodações, conforto e diversos serviços aos hóspedes.")
                    .build()
    ),

    GUESTHOUSE(
            Category.builder()
                    .name("Pousada")
                    .icon(HugeiconNamed.GUESTHOUSE.getWeb())
                    .description("Hospedagem turística de pequeno porte, com ambiente acolhedor e atendimento mais personalizado.")
                    .build()
    ),

    ACCOMMODATION(
            Category.builder()
                    .name("Hospedaria")
                    .icon(HugeiconNamed.HOTEL_02.getWeb())
                    .description("Local de hospedagem simples e econômica para estadias temporárias, podendo incluir serviços básicos.")
                    .build()
    ),

    BED_AND_BREAKFAST(
            Category.builder()
                    .name("Pensão")
                    .icon(HugeiconNamed.HOTEL_04.getWeb())
                    .description("Estabelecimento de hospedagem voltado para estadias prolongadas, geralmente com refeições inclusas.")
                    .build()
    ),

    RESTAURANT(
            Category.builder()
                    .name("Restaurante")
                    .icon(HugeiconNamed.RESTAURANT_01.getWeb())
                    .description("Estabelecimento especializado no preparo e serviço de refeições e bebidas para consumo.")
                    .type(CategoryType.RESTAURANT)
                    .build()
    ),

    BOTEQUIM(
            Category.builder()
                    .name("Botequim")
                    .icon(HugeiconNamed.RESTAURANT.getWeb())
                    .description("Estabelecimento informal voltado à venda de bebidas, petiscos e refeições rápidas.")
                    .type(CategoryType.RESTAURANT)
                    .build()
    ),

    SHOP(
            Category.builder()
                    .name("Loja")
                    .icon(HugeiconNamed.SHOPPING_BAG_03.getWeb())
                    .description("Estabelecimento comercial destinado à venda de produtos e atendimento ao público.")
                    .type(CategoryType.RESTAURANT)
                    .build()
    );

    private final Category category;

    public static List<Category> listCategories() {
        return Arrays.stream(CategoryData.values())
                .map(CategoryData::getCategory)
                .toList();
    }

    public static Category random() {
        var categories = listCategories();
        var random = RandomGenerator.getDefault();
        var index = random.nextInt(categories.size());

        return categories.get(index);
    }
}