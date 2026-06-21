package com.negoreserva.common.feature.concrete.organization.enums;

import com.negoreserva.common.feature.concrete.organization.model.Organization;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.random.RandomGenerator;

@Getter
@AllArgsConstructor
public enum OrganizationData {
    ACME(Organization.builder()
            .name("Acme Corporation")
            .email("acmecorparation@gmail.com")
            .description("Referência em soluções inovadoras para empresas modernas. Especializada em gestão de viagens corporativas, nossa rede hoteleira oferece acomodações premium, instalações de conferência de última geração e serviços de hospitalidade excepcionais, projetados tanto para profissionais de negócios quanto para viajantes a lazer.")
            .phone("+1234567890")
            .address("123 Main Street, New York, NY 10001")
            .logo("https://img.icons8.com/ios/50/homeadvisor.png")
            .image("https://images.unsplash.com/photo-1657998241530-2112ddce927d?q=600&w=600")
            .rating(4)
            .isHighlight(true)
            .status(OrganizationStatus.VISIBLE)
            .build()),

    TECHCORP(Organization.builder()
            .name("TechCorp Inc")
            .email("techCorpinc@gmail.com")
            .description("Empresa de tecnologia e desenvolvimento de software com uma moderna divisão de hospitalidade. Unimos tecnologia de ponta com conforto, oferecendo quartos inteligentes com integração IoT, conectividade de alta velocidade e espaços de trabalho inovadores que atendem nômades digitais e entusiastas de tecnologia em busca de uma experiência futurista.")
            .phone("+1987654321")
            .address("456 Tech Avenue, San Francisco, CA 94102")
            .logo("https://img.icons8.com/emoji/48/hotel-emoji.png")
            .image("https://images.unsplash.com/photo-1739063273523-3175f850450e?q=600&w=600")
            .rating(5)
            .status(OrganizationStatus.VISIBLE)
            .build()),

    GLOBAL(Organization.builder()
            .name("Global Solutions")
            .email("globalsolutions@gmail.com")
            .description("Consultoria internacional de negócios com uma rede premium de hospitalidade operando nas principais cidades do mundo. Nossos estabelecimentos combinam o charme local com padrões internacionais de excelência, proporcionando experiências selecionadas que vão desde acomodações de luxo a restaurantes refinados e espaços exclusivos para eventos.")
            .phone("+1555555555")
            .address("789 Business Blvd, Chicago, IL 60601")
            .logo("https://img.icons8.com/plasticine/100/hotel-room.png")
            .image("https://images.unsplash.com/photo-1692153142886-9881d0457b82?q=600&w=600")
            .rating(3)
            .isHighlight(true)
            .status(OrganizationStatus.VISIBLE)
            .build()),

    POUSADA_RECANTO(Organization.builder()
            .name("Pousada Recanto Verde")
            .email("recanto.verde@pousada.com.br")
            .description("Encantadora pousada situada em meio à natureza exuberante da Serra da Mantiqueira, oferecendo aconchego e tranquilidade para seus hóspedes. Com quartos decorados com elegância rústica, lareiras aconchegantes e um jardim exuberante com vista para as montanhas, é o refúgio perfeito para casais e famílias que buscam descanso e contato com a natureza. Desfrute de um café da manhã colonial com produtos frescos e regionais, trilhas ecológicas e momentos inesquecíveis de paz.")
            .phone("+5535123456789")
            .address("Estrada das Montanhas, Km 15, Zona Rural, Monte Verde - MG")
            .logo("https://img.icons8.com/color/48/cottage.png")
            .image("https://images.unsplash.com/photo-1564013799919-ab600027ffc6?q=600&w=600")
            .rating(5)
            .status(OrganizationStatus.VISIBLE)
            .build()),

    PENSAO_FAMILIAR(Organization.builder()
            .name("Pensão Familiar")
            .email("contato@pensaofamiliar.com.br")
            .description("Ambiente acolhedor e familiar com refeições caseiras inclusas, localizada no coração do centro histórico. Ideal para estadias prolongadas, nossa pensão oferece quartos confortáveis com móveis de época, café da manhã reforçado, almoço e jantar preparados com carinho pela família Oliveira. Os hóspedes desfrutam de uma experiência autêntica, com atendimento personalizado, proximidade dos principais pontos turísticos e a atmosfera calorosa de um verdadeiro lar longe de casa.")
            .phone("+5531988887777")
            .address("Rua do Imperador, 450, Centro, Ouro Preto - MG")
            .logo("https://img.icons8.com/color/48/home.png")
            .image("https://images.unsplash.com/photo-1582719508461-905c673771fd?q=600&w=600")
            .rating(4)
            .status(OrganizationStatus.VISIBLE)
            .build()),

    RESTAURANT_SABER(Organization.builder()
            .name("Restaurante Sabor & Arte")
            .email("contato@saborearte.com.br")
            .description("Gastronomia contemporânea brasileira com ingredientes frescos e sazonais, premiado como um dos melhores restaurantes da cidade. Nosso chef executivo renomado cria pratos únicos que combinam tradição e inovação, utilizando produtos orgânicos de produtores locais. O ambiente sofisticado e aconchegante, com decoração assinada e vista panorâmica, é perfeito para jantares românticos, encontros de negócios e celebrações especiais. Harmonização com vinhos selecionados e sobremesas artesanais imperdíveis.")
            .phone("+5511988889999")
            .address("Avenida Paulista, 1500, Jardins, São Paulo - SP")
            .logo("https://img.icons8.com/color/48/restaurant.png")
            .image("https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?q=600&w=600")
            .rating(5)
            .status(OrganizationStatus.VISIBLE)
            .build()),

    LOJA_BAIRR0(Organization.builder()
            .name("Loja do Bairro")
            .email("vendas@lojadobairro.com.br")
            .description("Sua loja de conveniência e variedades completa, referência no bairro há mais de 20 anos. Oferecemos uma ampla gama de produtos que vão desde mantimentos básicos e hortifrúti fresquinho até itens especiais importados, artigos para casa, brinquedos e presentes. Com atendimento amigo e personalizado, preços justos e entrega gratuita no bairro, somos a escolha certa para quem valoriza qualidade, proximidade e confiança. Trabalhamos com produtos artesanais de pequenos produtores da região.")
            .phone("+551133334444")
            .address("Rua Augusta, 890, Consolação, São Paulo - SP")
            .logo("https://img.icons8.com/color/48/shop.png")
            .image("https://images.unsplash.com/photo-1604719312566-8912e9227c6a?q=600&w=600")
            .rating(4)
            .isHighlight(true)
            .status(OrganizationStatus.VISIBLE)
            .build());

    private final Organization organization;

    public static List<Organization> listOrganizations() {
        return Arrays.stream(OrganizationData.values()).map(OrganizationData::getOrganization).toList();
    }

    public static Organization random() {
        var organizations = listOrganizations();
        var random = RandomGenerator.getDefault();
        var index = random.nextInt(organizations.size());
        return organizations.get(index);
    }
}