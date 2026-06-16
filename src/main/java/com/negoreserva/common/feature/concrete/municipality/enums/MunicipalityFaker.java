package com.negoreserva.common.feature.concrete.municipality.enums;

import com.negoreserva.common.feature.concrete.municipality.model.Municipality;
import com.negoreserva.common.feature.concrete.province.enums.ProvinceFaker;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MunicipalityFaker {
    DANDE(Municipality.builder().value("dande").label("Dande").province(ProvinceFaker.BENGO.getProvince()).build()),
    AMBRIZ(Municipality.builder().value("ambriz").label("Ambriz").province(ProvinceFaker.BENGO.getProvince()).build()),
    NAMBUANGONGO(Municipality.builder().value("nambuangongo").label("Nambuangongo").province(ProvinceFaker.BENGO.getProvince()).build()),

    BENGUELA(Municipality.builder().value("benguela").label("Benguela").province(ProvinceFaker.BENGUELA.getProvince()).build()),
    LOBITO(Municipality.builder().value("lobito").label("Lobito").province(ProvinceFaker.BENGUELA.getProvince()).build()),
    CATUMBELA(Municipality.builder().value("catumbela").label("Catumbela").province(ProvinceFaker.BENGUELA.getProvince()).build()),
    BAIA_FARTA(Municipality.builder().value("baia_farta").label("Baía Farta").province(ProvinceFaker.BENGUELA.getProvince()).build()),

    KUITO(Municipality.builder().value("kuito").label("Kuito").province(ProvinceFaker.BIE.getProvince()).build()),
    ANDULO(Municipality.builder().value("andulo").label("Andulo").province(ProvinceFaker.BIE.getProvince()).build()),
    CATABOLA(Municipality.builder().value("catabola").label("Catabola").province(ProvinceFaker.BIE.getProvince()).build()),

    CABINDA_MUN(Municipality.builder().value("cabinda").label("Cabinda").province(ProvinceFaker.CABINDA.getProvince()).build()),
    CACONGO(Municipality.builder().value("cacongo").label("Cacongo").province(ProvinceFaker.CABINDA.getProvince()).build()),
    BUCO_ZAU(Municipality.builder().value("buco_zau").label("Buco-Zau").province(ProvinceFaker.CABINDA.getProvince()).build()),

    MENONGUE(Municipality.builder().value("menongue").label("Menongue").province(ProvinceFaker.CUANDO_CUBANGO.getProvince()).build()),
    CUITO_CUANAVALE(Municipality.builder().value("cuito_cuanavale").label("Cuito Cuanavale").province(ProvinceFaker.CUANDO_CUBANGO.getProvince()).build()),

    NDALATANDO(Municipality.builder().value("ndalatando").label("Ndalatando").province(ProvinceFaker.CUANZA_NORTE.getProvince()).build()),
    GOLUNGO_ALTO(Municipality.builder().value("golungo_alto").label("Golungo Alto").province(ProvinceFaker.CUANZA_NORTE.getProvince()).build()),

    SUMBE(Municipality.builder().value("sumbe").label("Sumbe").province(ProvinceFaker.CUANZA_SUL.getProvince()).build()),
    PORTO_AMBOIM(Municipality.builder().value("porto_amboim").label("Porto Amboim").province(ProvinceFaker.CUANZA_SUL.getProvince()).build()),
    WAKUKUNGO(Municipality.builder().value("wakukungo").label("Waku Kungo").province(ProvinceFaker.CUANZA_SUL.getProvince()).build()),

    ONDJIVA(Municipality.builder().value("ondjiva").label("Ondjiva").province(ProvinceFaker.CUNENE.getProvince()).build()),
    CUANHAMA(Municipality.builder().value("cuanhama").label("Cuanhama").province(ProvinceFaker.CUNENE.getProvince()).build()),

    HUAMBO(Municipality.builder().value("huambo").label("Huambo").province(ProvinceFaker.HUAMBO.getProvince()).build()),
    BAILUNDO(Municipality.builder().value("bailundo").label("Bailundo").province(ProvinceFaker.HUAMBO.getProvince()).build()),
    CAALA(Municipality.builder().value("caala").label("Caála").province(ProvinceFaker.HUAMBO.getProvince()).build()),

    LUBANGO(Municipality.builder().value("lubango").label("Lubango").province(ProvinceFaker.HUILA.getProvince()).build()),
    MATALA(Municipality.builder().value("matala").label("Matala").province(ProvinceFaker.HUILA.getProvince()).build()),
    CHIBIA(Municipality.builder().value("chibia").label("Chibia").province(ProvinceFaker.HUILA.getProvince()).build()),

    LUANDA(Municipality.builder().value("luanda").label("Luanda").province(ProvinceFaker.LUANDA.getProvince()).build()),
    TALATONA(Municipality.builder().value("talatona").label("Talatona").province(ProvinceFaker.LUANDA.getProvince()).build()),
    VIANA(Municipality.builder().value("viana").label("Viana").province(ProvinceFaker.LUANDA.getProvince()).build()),
    CAZENGA(Municipality.builder().value("cazenga").label("Cazenga").province(ProvinceFaker.LUANDA.getProvince()).build()),
    BELAS(Municipality.builder().value("belas").label("Belas").province(ProvinceFaker.LUANDA.getProvince()).build()),
    CACUACO(Municipality.builder().value("cacuaco").label("Cacuaco").province(ProvinceFaker.LUANDA.getProvince()).build()),
    QUICAMA(Municipality.builder().value("quiçama").label("Quiçama").province(ProvinceFaker.LUANDA.getProvince()).build()),
    ICCOLO_E_BENGO(Municipality.builder().value("iccolo_e_bengo").label("Ícolo e Bengo").province(ProvinceFaker.LUANDA.getProvince()).build()),

    LUCAPA(Municipality.builder().value("lucapa").label("Lucapa").province(ProvinceFaker.LUNDA_NORTE.getProvince()).build()),
    DUNDO(Municipality.builder().value("dundo").label("Dundo").province(ProvinceFaker.LUNDA_NORTE.getProvince()).build()),

    SAURIMO(Municipality.builder().value("saurimo").label("Saurimo").province(ProvinceFaker.LUNDA_SUL.getProvince()).build()),
    CACOLO(Municipality.builder().value("cacolo").label("Cacolo").province(ProvinceFaker.LUNDA_SUL.getProvince()).build()),

    MALANJE(Municipality.builder().value("malanje").label("Malanje").province(ProvinceFaker.MALANJE.getProvince()).build()),
    CALANDULA(Municipality.builder().value("calandula").label("Calandula").province(ProvinceFaker.MALANJE.getProvince()).build()),

    LUENA(Municipality.builder().value("luena").label("Luena").province(ProvinceFaker.MOXICO.getProvince()).build()),
    CAZOMBO(Municipality.builder().value("cazombo").label("Cazombo").province(ProvinceFaker.MOXICO.getProvince()).build()),

    NAMIBE(Municipality.builder().value("namibe").label("Namibe").province(ProvinceFaker.NAMIBE.getProvince()).build()),
    TOMBUA(Municipality.builder().value("tombua").label("Tômbua").province(ProvinceFaker.NAMIBE.getProvince()).build()),

    UIGE_MUN(Municipality.builder().value("uige").label("Uíge").province(ProvinceFaker.UIGE.getProvince()).build()),
    NEGAGE(Municipality.builder().value("negage").label("Negage").province(ProvinceFaker.UIGE.getProvince()).build()),

    MBANZA_CONGO(Municipality.builder().value("mbanza_congo").label("Mbanza Congo").province(ProvinceFaker.ZAIRE.getProvince()).build()),
    SOYOS(Municipality.builder().value("soyos").label("Soyo").province(ProvinceFaker.ZAIRE.getProvince()).build());

    private final Municipality municipality;
}