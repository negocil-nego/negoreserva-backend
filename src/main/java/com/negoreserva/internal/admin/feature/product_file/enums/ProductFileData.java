package com.negoreserva.internal.admin.feature.product_file.enums;

import com.negoreserva.common.feature.concrete.product.enums.ProductData;
import com.negoreserva.common.feature.concrete.product_file.enums.ProductFileType;
import com.negoreserva.common.feature.concrete.product_file.model.ProductFile;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.random.RandomGenerator;

@Getter
@AllArgsConstructor
public enum ProductFileData {
    HOTEL_STANDARD_IMG_1(
            ProductFile.builder()
                    .title("Quarto Standard Vista Geral")
                    .description("Quarto standard amplo com cama queen-size confortável, móveis modernos e uma vista deslumbrante da cidade")
                    .url("https://images.unsplash.com/photo-1661258229239-a72356cddf3d?q=80&w=1170")
                    .type(ProductFileType.IMAGE)
                    .product(ProductData.HOTEL_STANDARD_1.getProduct())
                    .build()
    ),
    HOTEL_STANDARD_IMG_2(
            ProductFile.builder()
                    .title("Quarto Standard Médio")
                    .description("Elegante quarto standard médio com roupa de cama premium, iluminação aconchegante e decoração cuidadosamente projetada para uma estadia relaxante")
                    .url("https://images.unsplash.com/photo-1618773928121-c32242e63f39?q=80&w=1170")
                    .type(ProductFileType.IMAGE)
                    .product(ProductData.HOTEL_STANDARD_2.getProduct())
                    .build()
    ),
    HOTEL_STANDARD_IMG_3(
            ProductFile.builder()
                    .title("Quarto Standard Máximo")
                    .description("Quarto standard generoso oferecendo espaço extra, uma confortável cama king-size e todas as comodidades necessárias para uma experiência confortável e memorável")
                    .url("https://images.unsplash.com/photo-1611892440504-42a792e24d32?q=80&w=1170")
                    .type(ProductFileType.IMAGE)
                    .product(ProductData.HOTEL_STANDARD_3.getProduct())
                    .build()
    ),

    HOTEL_TECHCORP_IMG_1(
            ProductFile.builder()
                    .title("Suíte Premium Techcorp")
                    .description("Suíte deluxe Techcorp com cama king-size macia, lençóis de seda e vistas panorâmicas de tirar o fôlego que redefinem o luxo")
                    .url("https://images.unsplash.com/photo-1631049307264-da0ec9d70304?q=80&w=2070")
                    .type(ProductFileType.IMAGE)
                    .product(ProductData.HOTEL_TECHCORP_1.getProduct())
                    .build()
    ),
    HOTEL_TECHCORP_IMG_2(
            ProductFile.builder()
                    .title("Sala de Estar Techcorp")
                    .description("Área de lounge Techcorp adornada com móveis de design, iluminação ambiente e amplas janelas do chão ao teto com vista para a cidade")
                    .url("https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?q=80&w=1170")
                    .type(ProductFileType.IMAGE)
                    .product(ProductData.HOTEL_TECHCORP_2.getProduct())
                    .build()
    ),
    HOTEL_TECHCORP_IMG_3(
            ProductFile.builder()
                    .title("Retiro Executivo Techcorp")
                    .description("Retiro executivo exclusivo Techcorp oferecendo ambiente sereno, obras de arte selecionadas e comodidades de classe mundial para os hóspedes mais exigentes")
                    .url("https://images.unsplash.com/photo-1584132967334-10e028bd69f7?q=80&w=1170")
                    .type(ProductFileType.IMAGE)
                    .product(ProductData.HOTEL_TECHCORP_3.getProduct())
                    .build()
    ),

    HOTEL_DELUXE_IMG_1(
            ProductFile.builder()
                    .title("Quarto Premium Suíte Deluxe")
                    .description("Suíte deluxe luxuosa com cama king-size macia, lençóis de seda e vistas panorâmicas de tirar o fôlego que redefinem o luxo")
                    .url("https://plus.unsplash.com/premium_photo-1661964402307-02267d1423f5?q=80&w=1073")
                    .type(ProductFileType.IMAGE)
                    .product(ProductData.HOTEL_DELUXE_1.getProduct())
                    .build()
    ),
    HOTEL_DELUXE_IMG_2(
            ProductFile.builder()
                    .title("Sala de Estar Suíte Deluxe")
                    .description("Sofisticada área de lounge adornada com móveis de design, iluminação ambiente e amplas janelas do chão ao teto com vista para a cidade")
                    .url("https://images.unsplash.com/photo-1590490360182-c33d57733427?q=80&w=2070")
                    .type(ProductFileType.IMAGE)
                    .product(ProductData.HOTEL_DELUXE_2.getProduct())
                    .build()
    ),
    HOTEL_DELUXE_IMG_3(
            ProductFile.builder()
                    .title("Retiro Executivo Suíte Deluxe")
                    .description("Retiro executivo exclusivo oferecendo ambiente sereno, obras de arte selecionadas e comodidades de classe mundial para os hóspedes mais exigentes")
                    .url("https://images.unsplash.com/photo-1578683010236-d716f9a3f461?q=80&w=2070")
                    .type(ProductFileType.IMAGE)
                    .product(ProductData.HOTEL_DELUXE_3.getProduct())
                    .build()
    ),

    // --- ACME PRESIDENTIAL ---
    ACME_PRESIDENTIAL_IMG(
            ProductFile.builder()
                    .title("Sala de Estar Suíte Presidencial")
                    .description("Suíte presidencial opulenta com ampla sala de estar, janelas do chão ao teto e decoração moderna sofisticada com acabamentos premium")
                    .url("https://images.unsplash.com/photo-1582719478250-c89cae4dc85b?q=80&w=1170")
                    .type(ProductFileType.IMAGE)
                    .product(ProductData.ACME_PRESIDENTIAL.getProduct())
                    .build()
    ),

    // --- TECHCORP COWORKING ---
    TECHCORP_COWORKING_IMG(
            ProductFile.builder()
                    .title("Espaço Coworking TechCorp")
                    .description("Espaço de trabalho colaborativo moderno com tecnologia de ponta, móveis ergonômicos e uma atmosfera inspiradora projetada para inovação e produtividade")
                    .url("https://images.unsplash.com/photo-1497366216548-37526070297c?q=80&w=1170")
                    .type(ProductFileType.IMAGE)
                    .product(ProductData.TECHCORP_COWORKING.getProduct())
                    .build()
    ),

    // --- GLOBAL EVENT SPACE ---
    GLOBAL_EVENT_SPACE_IMG(
            ProductFile.builder()
                    .title("Salão de Eventos Global")
                    .description("Grande salão de eventos com lustres elegantes, palco profissional e capacidade para até 300 convidados em uma atmosfera sofisticada")
                    .url("https://images.unsplash.com/photo-1519167758481-83f550bb49b3?q=80&w=1170")
                    .type(ProductFileType.IMAGE)
                    .product(ProductData.GLOBAL_EVENT_SPACE.getProduct())
                    .build()
    ),

    // --- POUSADA RECANTO VERDE ---
    POUSADA_RUSTICO_IMG(
            ProductFile.builder()
                    .title("Quarto Rústico com Lareira")
                    .description("Encantador quarto rústico com lareira de pedra, vigas de madeira, cama queen-size confortável e iluminação ambiente aconchegante para um refúgio na montanha")
                    .url("https://images.unsplash.com/photo-1621293954908-907159247fc8?q=80&w=1170")
                    .type(ProductFileType.IMAGE)
                    .product(ProductData.POUSADA_QUARTO_RUSTICO.getProduct())
                    .build()
    ),
    POUSADA_SUITE_IMG(
            ProductFile.builder()
                    .title("Suíte Natureza com Jacuzzi")
                    .description("Suíte luxuosa com jacuzzi privativa com vista para as montanhas, cama king-size macia e elegante decoração rústico-chique criando o refúgio romântico perfeito")
                    .url("https://images.unsplash.com/photo-1590490360182-c33d57733427?q=80&w=1170")
                    .type(ProductFileType.IMAGE)
                    .product(ProductData.POUSADA_SUITE_MASTER.getProduct())
                    .build()
    ),
    POUSADA_CHALE_IMG(
            ProductFile.builder()
                    .title("Chalé Familiar Externo")
                    .description("Chalé privativo em meio à natureza com ofurô ao ar livre, área de churrasco e amplo deck oferecendo vistas panorâmicas deslumbrantes das montanhas")
                    .url("https://images.unsplash.com/photo-1564013799919-ab600027ffc6?q=80&w=1170")
                    .type(ProductFileType.IMAGE)
                    .product(ProductData.POUSADA_CHALE.getProduct())
                    .build()
    ),

    // --- PENSAO FAMILIAR ---
    PENSAO_SIMPLES_IMG(
            ProductFile.builder()
                    .title("Quarto Simples Aconchegante")
                    .description("Quarto simples mas aconchegante com luz natural, móveis de madeira e uma atmosfera acolhedora perfeita para viajantes com orçamento limitado")
                    .url("https://images.unsplash.com/photo-1595576508898-0ad5c879a061?q=80&w=1170")
                    .type(ProductFileType.IMAGE)
                    .product(ProductData.PENSAO_QUARTO_SIMPLES.getProduct())
                    .build()
    ),
    PENSAO_DUPLO_IMG(
            ProductFile.builder()
                    .title("Quarto Duplo Familiar")
                    .description("Amplo quarto duplo com duas camas confortáveis, móveis vintage e decoração caseira refletindo o aconchego de um lar familiar tradicional")
                    .url("https://images.unsplash.com/photo-1566665797739-1674de7a421a?q=80&w=1170")
                    .type(ProductFileType.IMAGE)
                    .product(ProductData.PENSAO_QUARTO_DUPLO.getProduct())
                    .build()
    ),
    PENSAO_SUITE_IMG(
            ProductFile.builder()
                    .title("Suíte Master Familiar")
                    .description("Ampla suíte master com cama king-size, varanda privativa, mini geladeira e encantadores móveis de época combinando conforto com caráter histórico")
                    .url("https://images.unsplash.com/photo-1618773928121-c32242e63f39?q=80&w=1170")
                    .type(ProductFileType.IMAGE)
                    .product(ProductData.PENSAO_SUITE.getProduct())
                    .build()
    ),

    // --- RESTAURANTE SABOR & ARTE ---
    RESTAURANTE_JANTAR_IMG(
            ProductFile.builder()
                    .title("Jantar Executivo")
                    .description("Elegante mesa de jantar com porcelana fina, copos de cristal e apresentação artística exibindo a requintada culinária contemporânea do chef")
                    .url("https://images.unsplash.com/photo-1414235077428-338989a2e8c0?q=80&w=1170")
                    .type(ProductFileType.IMAGE)
                    .product(ProductData.RESTAURANTE_JANTAR_EXECUTIVO.getProduct())
                    .build()
    ),
    RESTAURANTE_CHEF_TABLE_IMG(
            ProductFile.builder()
                    .title("Mesa do Chef")
                    .description("Mesa exclusiva do chef dentro da cozinha, oferecendo um lugar privilegiado para a arte culinária com menu de degustação personalizado e harmonização de vinhos")
                    .url("https://images.unsplash.com/photo-1559339352-11d035aa65de?q=80&w=1170")
                    .type(ProductFileType.IMAGE)
                    .product(ProductData.RESTAURANTE_CHEF_TABLE.getProduct())
                    .build()
    ),
    RESTAURANTE_EVENTO_IMG(
            ProductFile.builder()
                    .title("Salão Privativo para Eventos")
                    .description("Elegante salão privativo com decoração sofisticada, iluminação ambiente e layout flexível perfeito para eventos corporativos e celebrações")
                    .url("https://images.unsplash.com/photo-1464366400600-7168b8af9bc3?q=80&w=1170")
                    .type(ProductFileType.IMAGE)
                    .product(ProductData.RESTAURANTE_EVENTO.getProduct())
                    .build()
    ),

    // --- LOJA DO BAIRRO ---
    LOJA_CESTA_IMG(
            ProductFile.builder()
                    .title("Cesta Básica Premium")
                    .description("Cesta de mantimentos premium cuidadosamente selecionada com arroz orgânico, feijão, macarrão, café e outros itens essenciais de produtores locais")
                    .url("https://images.unsplash.com/photo-1542838132-92c53300491e?q=80&w=1170")
                    .type(ProductFileType.IMAGE)
                    .product(ProductData.LOJA_CESTA_BASICA.getProduct())
                    .build()
    ),
    LOJA_HORTIFRUTI_IMG(
            ProductFile.builder()
                    .title("Hortifrúti Orgânico Fresco")
                    .description("Seleção vibrante de frutas e verduras orgânicas frescas, recém-colhidas de fazendas locais e entregues diretamente na sua porta")
                    .url("https://images.unsplash.com/photo-1610348725531-843dff563e2c?q=80&w=1170")
                    .type(ProductFileType.IMAGE)
                    .product(ProductData.LOJA_HORTIFRUTI.getProduct())
                    .build()
    ),
    LOJA_PRESENTES_IMG(
            ProductFile.builder()
                    .title("Kit Presente Especial")
                    .description("Conjunto de presente lindamente embalado com vinhos importados, queijos artesanais, chocolates belgas e café especial em uma elegante caixa de apresentação")
                    .url("https://images.unsplash.com/photo-1512909006721-3d6018887383?q=80&w=1170")
                    .type(ProductFileType.IMAGE)
                    .product(ProductData.LOJA_PRESENTES.getProduct())
                    .build()
    );

    private final ProductFile productFile;

    public static List<ProductFile> listProductFiles() {
        return Arrays.stream(ProductFileData.values()).map(ProductFileData::getProductFile).toList();
    }

    public static ProductFile random() {
        var files = listProductFiles();
        var random = RandomGenerator.getDefault();
        var index = random.nextInt(files.size());
        return files.get(index);
    }
}