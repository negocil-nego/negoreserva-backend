package com.negoreserva.common.feature.concrete.catalog.enums;

import com.negoreserva.common.feature.concrete.catalog.model.Catalog;
import com.negoreserva.common.feature.concrete.organization.enums.OrganizationFaker;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.random.RandomGenerator;

@Getter
@AllArgsConstructor
public enum CatalogFaker {
    // --- ACME ---
    ACME_QUARTOS(Catalog.builder().name("Catálogo de Quartos").description("Nossas acomodações premium para sua estadia perfeita.").organization(OrganizationFaker.ACME.getOrganization()).build()),
    ACME_EVENTOS(Catalog.builder().name("Catálogo de Eventos").description("Espaços e serviços para eventos corporativos e sociais.").organization(OrganizationFaker.ACME.getOrganization()).build()),
    ACME_GASTRONOMIA(Catalog.builder().name("Catálogo Gastronômico").description("Experiências culinárias exclusivas.").type(CatalogType.FOOD).organization(OrganizationFaker.ACME.getOrganization()).build()),

    // --- TECHCORP ---
    TECHCORP_ACOMODACOES(Catalog.builder().name("Acomodações Tech").description("Quartos inteligentes com tecnologia de ponta.").organization(OrganizationFaker.TECHCORP.getOrganization()).build()),
    TECHCORP_COWORKING(Catalog.builder().name("Espaços Coworking").description("Ambientes de trabalho inovadores.").organization(OrganizationFaker.TECHCORP.getOrganization()).build()),

    // --- GLOBAL ---
    GLOBAL_HOSPEDAGEM(Catalog.builder().name("Hospedagem Premium").description("Suítes e quartos de luxo.").organization(OrganizationFaker.GLOBAL.getOrganization()).build()),
    GLOBAL_SERVICOS(Catalog.builder().name("Serviços Corporativos").description("Soluções executivas completas.").organization(OrganizationFaker.GLOBAL.getOrganization()).build()),
    GLOBAL_LAZER(Catalog.builder().name("Lazer & Bem-Estar").description("Atividades de lazer e relaxamento.").organization(OrganizationFaker.GLOBAL.getOrganization()).build()),

    // --- POUSADA RECANTO ---
    POUSADA_ACOMODACOES(Catalog.builder().name("Acomodações Recanto").description("Quartos e chalés em meio à natureza.").organization(OrganizationFaker.POUSADA_RECANTO.getOrganization()).build()),
    POUSADA_EXPERIENCIAS(Catalog.builder().name("Experiências Ecológicas").description("Trilhas, passeios e contato com a natureza.").organization(OrganizationFaker.POUSADA_RECANTO.getOrganization()).build()),

    // --- PENSAO FAMILIAR ---
    PENSAO_QUARTOS(Catalog.builder().name("Quartos Pensão").description("Acomodações simples e aconchegantes.").organization(OrganizationFaker.PENSAO_FAMILIAR.getOrganization()).build()),
    PENSAO_REFEICOES(Catalog.builder().name("Refeições Caseiras").description("Café da manhã, almoço e jantar caseiros.").type(CatalogType.FOOD).organization(OrganizationFaker.PENSAO_FAMILIAR.getOrganization()).build()),

    // --- RESTAURANTE SABOR & ARTE ---
    RESTAURANTE_CARDAPIO(Catalog.builder().name("Cardápio Executivo").description("Pratos elaborados para almoço e jantar.").type(CatalogType.FOOD).organization(OrganizationFaker.RESTAURANT_SABER.getOrganization()).build()),
    RESTAURANTE_VINHOS(Catalog.builder().name("Carta de Vinhos").description("Seleção especial de vinhos nacionais e importados.").type(CatalogType.DRIK).organization(OrganizationFaker.RESTAURANT_SABER.getOrganization()).build()),

    // --- LOJA DO BAIRRO ---
    LOJA_PRODUTOS(Catalog.builder().name("Produtos da Semana").description("Ofertas e novidades em produtos.").organization(OrganizationFaker.LOJA_BAIRR0.getOrganization()).build()),
    LOJA_HORTIFRUTI(Catalog.builder().name("Hortifrúti Fresco").description("Frutas, verduras e legumes fresquinhos.").type(CatalogType.FOOD).organization(OrganizationFaker.LOJA_BAIRR0.getOrganization()).build());

    private final Catalog catalog;

    public static List<Catalog> listCatalogs() {
        return Arrays.stream(CatalogFaker.values()).map(CatalogFaker::getCatalog).toList();
    }

    public static Catalog random() {
        var catalogs = listCatalogs();
        var random = RandomGenerator.getDefault();
        var index = random.nextInt(catalogs.size());
        return catalogs.get(index);
    }
}
