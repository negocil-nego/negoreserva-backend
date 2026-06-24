package com.negoreserva.common.feature.concrete.product.enums;

import com.negoreserva.common.feature.concrete.organization.enums.OrganizationData;
import com.negoreserva.common.feature.concrete.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.random.RandomGenerator;

@Getter
@AllArgsConstructor
public enum ProductData {
    // --- ACME Products ---
    HOTEL_STANDARD_1(
            Product.builder()
                    .name("Quarto Standard")
                    .order(1)
                    .description("Quarto confortável com cama queen-size, ar condicionado, TV LED 32 polegadas, frigobar e banheiro privativo. Ideal para viajantes que buscam praticidade e conforto com excelente custo-benefício. Inclui café da manhã continental e Wi-Fi de alta velocidade.")
                    .organization(OrganizationData.ACME.getOrganization())
                    .price(new BigDecimal("33.432"))
                    .image("https://images.unsplash.com/photo-1563911892437-1feda0179e1b?w=400&h=300&fit=crop")
                    .build()
    ),
    HOTEL_STANDARD_2(
            Product.builder()
                    .name("Quarto Medium")
                    .order(2)
                    .description("Apartamento espaçoso com cama king-size, sala de estar integrada, bancada de trabalho ergonômica, TV 50 polegadas e banheiro com ducha higiênica. Perfeito para estadias de negócios ou lazer, com acesso ao lounge executivo e serviço de quarto 24 horas.")
                    .organization(OrganizationData.ACME.getOrganization())
                    .price(new BigDecimal("43.432"))
                    .image("https://images.unsplash.com/photo-1584132967334-10e028bd69f7?w=400&h=300&fit=crop")
                    .build()
    ),
    HOTEL_STANDARD_3(
            Product.builder()
                    .name("Quarto Max")
                    .order(3)
                    .description("Suíte master premium com dois ambientes, cama super king-size, closet privativo, banheira de hidromassagem e varanda com vista panorâmica para a cidade. Inclui serviço de mordomo, amenities de luxo, garrafa de vinho cortesia e check-in prioritário.")
                    .organization(OrganizationData.ACME.getOrganization())
                    .price(new BigDecimal("37.132"))
                    .image("https://images.unsplash.com/photo-1578683010236-d716f9a3f461?w=400&h=300&fit=crop")
                    .build()
    ),
    ACME_PRESIDENTIAL(
            Product.builder()
                    .name("Suíte Presidencial")
                    .order(4)
                    .description("Nossa suíte mais exclusiva com 120m², sala de jantar, escritório privativo, banheira de hidromassagem com cromoterapia e terraço com piscina privativa. Experiência completa com mordomo particular, traslado executivo e acesso ao clube privê do hotel.")
                    .organization(OrganizationData.ACME.getOrganization())
                    .price(new BigDecimal("62.442"))
                    .image("https://images.unsplash.com/photo-1600607687939-ce8a6c25118c?w=400&h=300&fit=crop")
                    .build()
    ),

    // --- TECHCORP Products ---
    HOTEL_TECHCORP_1(
            Product.builder()
                    .name("TechCorp Quarto Smart")
                    .description("Quarto inteligente com automação completa, assistente virtual integrado, painéis de controle touch screen, iluminação regulável e cortinas automatizadas. Cama ergonômica com ajuste de firmeza, TV 65 polegadas e dock para carregamento sem fio.")
                    .organization(OrganizationData.TECHCORP.getOrganization())
                    .price(new BigDecimal("13.432"))
                    .image("https://images.unsplash.com/photo-1610945415295-d9bbf067e59c?w=400&h=300&fit=crop")
                    .build()
    ),
    HOTEL_TECHCORP_2(
            Product.builder()
                    .name("TechCorp Suíte Executive")
                    .order(6)
                    .description("Suíte executiva com estação de trabalho multimídia, monitor ultrawide, webcam 4K e cadeira ergonômica. Ambiente integrado com sala de reunião para até 6 pessoas, sistema de videoconferência e acesso prioritário ao business center.")
                    .organization(OrganizationData.TECHCORP.getOrganization())
                    .price(new BigDecimal("13.432"))
                    .image("https://images.unsplash.com/photo-1522071820081-009f0129c71c?w=400&h=300&fit=crop")
                    .build()
    ),
    HOTEL_TECHCORP_3(
            Product.builder()
                    .name("TechCorp Penthouse")
                    .order(7)
                    .description("Cobertura tecnológica de 200m² com automação residencial completa, cinema em casa com projetor 4K, kitchenette gourmet equipada com eletrodomésticos smart e terraço com ofurô aquecido. Experiência imersiva com realidade virtual disponível.")
                    .organization(OrganizationData.TECHCORP.getOrganization())
                    .price(new BigDecimal("37.323"))
                    .image("https://images.unsplash.com/photo-1600047509807-ba8f99d2cdde?w=400&h=300&fit=crop")
                    .build()
    ),
    TECHCORP_COWORKING(
            Product.builder()
                    .name("Espaço Coworking Tech")
                    .order(8)
                    .description("Ambiente de coworking premium com 20 estações de trabalho equipadas, salas de reunião inteligentes, impressão 3D, internet dedicada de 1Gbps e café especial artesanal. Inclui acesso a eventos de networking e mentorias com especialistas em tecnologia.")
                    .organization(OrganizationData.TECHCORP.getOrganization())
                    .price(new BigDecimal("33.432"))
                    .image("https://images.unsplash.com/photo-1497366216548-37526070297c?w=400&h=300&fit=crop")
                    .build()
    ),

    // --- GLOBAL Products ---
    HOTEL_DELUXE_1(
            Product.builder()
                    .name("Quarto Deluxe Standard")
                    .order(9)
                    .description("Quarto deluxe com decoração sofisticada, cama king-size com lençóis egípcios, TV 55 polegadas, sistema de som ambiente e minibar premium. Banheiro revestido em mármore com ducha dupla e amenities de grife. Vista parcial para o jardim.")
                    .organization(OrganizationData.GLOBAL.getOrganization())
                    .price(new BigDecimal("10.932"))
                    .image("https://images.unsplash.com/photo-1631049307264-da0ec9d70304?w=400&h=300&fit=crop")
                    .build()
    ),
    HOTEL_DELUXE_2(
            Product.builder()
                    .name("Quarto Deluxe Premium")
                    .order(10)
                    .description("Suíte premium com sala de estar separada, papel de parede importado, lustre cristal, banheira de hidromassagem e varanda privativa. Acesso ao clube executivo com open bar e open food, serviço de concierge personalizado e traslado executivo.")
                    .organization(OrganizationData.GLOBAL.getOrganization())
                    .price(new BigDecimal("976.239"))
                    .image("https://images.unsplash.com/photo-1596394516093-501ba68a0ba6?w=400&h=300&fit=crop")
                    .build()
    ),
    HOTEL_DELUXE_3(
            Product.builder()
                    .name("Quarto Deluxe Master")
                    .order(11)
                    .description("Suíte master com 80m², quarto e sala amplos, cozinha compacta equipada, lavabo social e terraço com jardim privativo. Decoração assinada por designer renomado, obras de arte originais e atendimento personalizado 24 horas.")
                    .organization(OrganizationData.GLOBAL.getOrganization())
                    .price(new BigDecimal("335.432"))
                    .image("https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?w=400&h=300&fit=crop")
                    .build()
    ),
    GLOBAL_EVENT_SPACE(
            Product.builder()
                    .name("Salão de Eventos Global")
                    .order(12)
                    .description("Salão de eventos versátil com capacidade para até 300 pessoas, infraestrutura completa de áudio e vídeo, palco profissional, cozinha industrial e área externa para coquetéis. Perfeito para casamentos, conferências e lançamentos de produtos.")
                    .organization(OrganizationData.GLOBAL.getOrganization())
                    .price(new BigDecimal("761.412"))
                    .image("https://images.unsplash.com/photo-1519167758481-83f550bb49b3?w=400&h=300&fit=crop")
                    .build()
    ),

    // --- POUSADA RECANTO VERDE Products ---
    POUSADA_QUARTO_RUSTICO(
            Product.builder()
                    .name("Quarto Rústico Aconchegante")
                    .order(13)
                    .description("Quarto decorado em estilo rústico-chique com lareira a lenha, cama queen-size com roupa de cama de algodão egípcio e edredom de pluma. Aquecimento central, banheiro com ducha aquecida e varanda com rede e vista para as montanhas. Inclui café da manhã colonial completo.")
                    .organization(OrganizationData.POUSADA_RECANTO.getOrganization())
                    .price(new BigDecimal("763.239"))
                    .image("https://images.unsplash.com/photo-1562778612-e1e0cda9915c?w=400&h=300&fit=crop")
                    .build()
    ),
    POUSADA_SUITE_MASTER(
            Product.builder()
                    .name("Suíte Master Natureza")
                    .order(14)
                    .description("Suíte ampla com hidromassagem privativa com vista panorâmica, lareira dupla-face, cama king-size, closet e varanda gourmet com churrasqueira. Experiência completa com jantar romântico à luz de velas, massagem relaxante no quarto e passeio ecológico personalizado.")
                    .organization(OrganizationData.POUSADA_RECANTO.getOrganization())
                    .price(new BigDecimal("76.239"))
                    .image("https://images.unsplash.com/photo-1540541338287-41700207dee6?w=400&h=300&fit=crop")
                    .build()
    ),
    POUSADA_CHALE(
            Product.builder()
                    .name("Chalé Familiar")
                    .order(15)
                    .description("Chalé independente com 2 quartos, sala com lareira, cozinha completa, banheiro social e ofurô aquecido na varanda. Perfeito para famílias ou grupos de até 6 pessoas, com área de lazer privativa, churrasqueira e vista deslumbrante para a serra.")
                    .organization(OrganizationData.POUSADA_RECANTO.getOrganization())
                    .price(new BigDecimal("678.239"))
                    .image("https://images.unsplash.com/photo-1600585154340-be6161a56a0c?w=400&h=300&fit=crop")
                    .build()
    ),

    // --- PENSAO FAMILIAR Products ---
    PENSAO_QUARTO_SIMPLES(
            Product.builder()
                    .name("Quarto Simples")
                    .order(16)
                    .description("Quarto aconchegante com cama de solteiro, escrivaninha, guarda-roupa e ventilador de teto. Banheiro compartilhado com chuveiro quente. Ideal para viajantes individuais ou estudantes. Inclui café da manhã, almoço e jantar caseiros preparados pela família.")
                    .organization(OrganizationData.PENSAO_FAMILIAR.getOrganization())
                    .price(new BigDecimal("394.239"))
                    .image("https://images.unsplash.com/photo-1586023492125-27b2c045efd7?w=400&h=300&fit=crop")
                    .build()
    ),
    PENSAO_QUARTO_DUPLO(
            Product.builder()
                    .name("Quarto Duplo Familiar")
                    .order(17)
                    .description("Quarto espaçoso com duas camas de solteiro ou uma cama de casal, armário amplo, mesa de estudos e banheiro privativo. TV tubo com canais abertos e Wi-Fi gratuito. Refeições caseiras inclusas, com horários flexíveis e dieta especial mediante solicitação.")
                    .organization(OrganizationData.PENSAO_FAMILIAR.getOrganization())
                    .price(new BigDecimal("230.239"))
                    .image("https://images.unsplash.com/photo-1554995207-c18c203602cb?w=400&h=300&fit=crop")
                    .build()
    ),
    PENSAO_SUITE(
            Product.builder()
                    .name("Suíte Master Familiar")
                    .order(18)
                    .description("Suíte ampla com cama king-size, TV LED 42 polegadas, mini geladeira, banheiro privativo com box blindex e varanda com rede. Decoração acolhedora com móveis de época. Pensão completa com refeições preparadas com ingredientes frescos da horta familiar.")
                    .organization(OrganizationData.PENSAO_FAMILIAR.getOrganization())
                    .price(new BigDecimal("200.239"))
                    .image("https://images.unsplash.com/photo-1505693416388-ac5ce068fe85?w=400&h=300&fit=crop")
                    .build()
    ),

    // --- RESTAURANTE SABOR & ARTE Products ---
    RESTAURANTE_JANTAR_EXECUTIVO(
            Product.builder()
                    .name("Jantar Executivo")
                    .description("Menu degustação de 4 tempos com entrada, prato principal, sobremesa e bebida não alcoólica. Pratos elaborados pelo chef com ingredientes sazonais e orgânicos. Experiência gastronômica completa em ambiente sofisticado com vista panorâmica da cidade.")
                    .organization(OrganizationData.RESTAURANT_SABER.getOrganization())
                    .image("https://images.unsplash.com/photo-1414235077428-338989a2e8c0?w=400&h=300&fit=crop")
                    .price(new BigDecimal("9.239"))
                    .build()
    ),
    RESTAURANTE_CHEF_TABLE(
            Product.builder()
                    .name("Chef's Table Experience")
                    .description("Experiência gastronômica exclusiva na cozinha do chef, com menu personalizado de 7 tempos harmonizado com vinhos selecionados. Acompanhamento do chef sommelier, explicação detalhada de cada prato e certificado de degustação personalizado.")
                    .organization(OrganizationData.RESTAURANT_SABER.getOrganization())
                    .price(new BigDecimal("7.239"))
                    .image("https://images.unsplash.com/photo-1555396273-367ea4eb4db5?w=400&h=300&fit=crop")
                    .build()
    ),
    RESTAURANTE_EVENTO(
            Product.builder()
                    .name("Espaço para Eventos")
                    .description("Salão privativo para até 80 pessoas com buffet completo personalizado, decoração inclusa, equipe de garçons dedicada e barman. Ideal para aniversários, confraternizações empresariais e casamentos intimistas. Menu customizado com degustação prévia.")
                    .organization(OrganizationData.RESTAURANT_SABER.getOrganization())
                    .price(new BigDecimal("4.239"))
                    .image("https://images.unsplash.com/photo-1464366400600-7168b8af9bc3?w=400&h=300&fit=crop")
                    .build()
    ),

    // --- LOJA DO BAIRRO Products ---
    LOJA_CESTA_BASICA(
            Product.builder()
                    .name("Cesta Básica Premium")
                    .description("Cesta completa com itens essenciais de alta qualidade: arroz, feijão, macarrão, óleo, açúcar, café, leite, farinha, biscoitos, enlatados, temperos e muito mais. Embalagem ecológica e entrega gratuita no bairro. Produtos selecionados de pequenos produtores locais.")
                    .organization(OrganizationData.LOJA_BAIRR0.getOrganization())
                    .price(new BigDecimal("5.233"))
                    .image("https://images.unsplash.com/photo-1542838132-92c53300491e?w=400&h=300&fit=crop")
                    .build()
    ),
    LOJA_HORTIFRUTI(
            Product.builder()
                    .name("Cesta de Hortifrúti")
                    .description("Seleção semanal de frutas, verduras e legumes frescos colhidos diretamente de produtores orgânicos da região. Cesta variada com 10 itens da estação, entregue fresquinha na sua casa. Produtos sem agrotóxicos e com certificação orgânica.")
                    .organization(OrganizationData.LOJA_BAIRR0.getOrganization())
                    .price(new BigDecimal("9.239"))
                    .image("https://images.unsplash.com/photo-1540420773420-3366772f4999?w=400&h=300&fit=crop")
                    .build()
    ),
    LOJA_PRESENTES(
            Product.builder()
                    .name("Kit Presente Especial")
                    .description("Kit presente personalizado com vinhos importados, queijos finos, cervejas artesanais, chocolates belgas e cestas de café especial. Embalagem elegante com laço e cartão personalizado. Perfeito para aniversários, Dia das Mães, Dia dos Namorados e Natal.")
                    .organization(OrganizationData.LOJA_BAIRR0.getOrganization())
                    .price(new BigDecimal("8.239"))
                    .image("https://images.unsplash.com/photo-1513889961551-628c1e5e2ee9?w=400&h=300&fit=crop")
                    .build()
    );

    private final Product product;

    public static List<Product> listProducts() {
        return Arrays.stream(ProductData.values()).map(ProductData::getProduct).toList();
    }

    public static Product random() {
        var products = listProducts();
        var random = RandomGenerator.getDefault();
        var index = random.nextInt(products.size());
        return products.get(index);
    }
}
