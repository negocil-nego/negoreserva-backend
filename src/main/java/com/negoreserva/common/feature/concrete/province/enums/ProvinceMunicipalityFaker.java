package com.negoreserva.common.feature.concrete.province.enums;

import com.negoreserva.common.feature.concrete.municipality.model.Municipality;
import com.negoreserva.common.feature.concrete.province.model.Province;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.random.RandomGenerator;

@Getter
@AllArgsConstructor
public enum ProvinceMunicipalityFaker {
    BENGO(
            Province.builder()
                    .value("bengo")
                    .label("Bengo")
                    .municipalities(List.of(
                            Municipality.builder().value("dande").label("Dande").build(),
                            Municipality.builder().value("ambriz").label("Ambriz").build(),
                            Municipality.builder().value("nambuangongo").label("Nambuangongo").build()
                    ))
                    .build()
    ),
    BENGUELA(
            Province.builder()
                    .value("benguela")
                    .label("Benguela")
                    .municipalities(List.of(
                            Municipality.builder().value("benguela").label("Benguela").build(),
                            Municipality.builder().value("lobito").label("Lobito").build(),
                            Municipality.builder().value("catumbela").label("Catumbela").build(),
                            Municipality.builder().value("baia_farta").label("Baía Farta").build()
                    ))
                    .build()
    ),
    BIE(
            Province.builder()
                    .value("bie")
                    .label("Bié")
                    .municipalities(List.of(
                            Municipality.builder().value("kuito").label("Kuito").build(),
                            Municipality.builder().value("andulo").label("Andulo").build(),
                            Municipality.builder().value("catabola").label("Catabola").build()
                    ))
                    .build()
    ),
    CABINDA(
            Province.builder()
                    .value("cabinda")
                    .label("Cabinda")
                    .municipalities(List.of(
                            Municipality.builder().value("cabinda").label("Cabinda").build(),
                            Municipality.builder().value("cacongo").label("Cacongo").build(),
                            Municipality.builder().value("buco_zau").label("Buco-Zau").build()
                    ))
                    .build()
    ),
    CUANDO_CUBANGO(
            Province.builder()
                    .value("cuando_cubango")
                    .label("Cuando Cubango")
                    .municipalities(List.of(
                            Municipality.builder().value("menongue").label("Menongue").build(),
                            Municipality.builder().value("cuito_cuanavale").label("Cuito Cuanavale").build()
                    ))
                    .build()
    ),
    CUANZA_NORTE(
            Province.builder()
                    .value("cuanza_norte")
                    .label("Cuanza Norte")
                    .municipalities(List.of(
                            Municipality.builder().value("ndalatando").label("Ndalatando").build(),
                            Municipality.builder().value("golungo_alto").label("Golungo Alto").build()
                    ))
                    .build()
    ),
    CUANZA_SUL(
            Province.builder()
                    .value("cuanza_sul")
                    .label("Cuanza Sul")
                    .municipalities(List.of(
                            Municipality.builder().value("sumbe").label("Sumbe").build(),
                            Municipality.builder().value("porto_amboim").label("Porto Amboim").build(),
                            Municipality.builder().value("wakukungo").label("Waku Kungo").build()
                    ))
                    .build()
    ),
    CUNENE(
            Province.builder()
                    .value("cunene")
                    .label("Cunene")
                    .municipalities(List.of(
                            Municipality.builder().value("ondjiva").label("Ondjiva").build(),
                            Municipality.builder().value("cuanhama").label("Cuanhama").build()
                    ))
                    .build()
    ),
    HUAMBO(
            Province.builder()
                    .value("huambo")
                    .label("Huambo")
                    .municipalities(List.of(
                            Municipality.builder().value("huambo").label("Huambo").build(),
                            Municipality.builder().value("bailundo").label("Bailundo").build(),
                            Municipality.builder().value("caala").label("Caála").build()
                    ))
                    .build()
    ),
    HUILA(
            Province.builder()
                    .value("huila")
                    .label("Huíla")
                    .municipalities(List.of(
                            Municipality.builder().value("lubango").label("Lubango").build(),
                            Municipality.builder().value("matala").label("Matala").build(),
                            Municipality.builder().value("chibia").label("Chibia").build()
                    ))
                    .build()
    ),
    LUANDA(
            Province.builder()
                    .value("luanda")
                    .label("Luanda")
                    .municipalities(List.of(
                            Municipality.builder().value("luanda").label("Luanda").build(),
                            Municipality.builder().value("talatona").label("Talatona").build(),
                            Municipality.builder().value("viana").label("Viana").build(),
                            Municipality.builder().value("cazenga").label("Cazenga").build(),
                            Municipality.builder().value("belas").label("Belas").build(),
                            Municipality.builder().value("cacuaco").label("Cacuaco").build(),
                            Municipality.builder().value("quiçama").label("Quiçama").build(),
                            Municipality.builder().value("iccolo_e_bengo").label("Ícolo e Bengo").build()
                    ))
                    .build()
    ),
    LUNDA_NORTE(
            Province.builder()
                    .value("lunda_norte")
                    .label("Lunda Norte")
                    .municipalities(List.of(
                            Municipality.builder().value("lucapa").label("Lucapa").build(),
                            Municipality.builder().value("dundo").label("Dundo").build()
                    ))
                    .build()
    ),
    LUNDA_SUL(
            Province.builder()
                    .value("lunda_sul")
                    .label("Lunda Sul")
                    .municipalities(List.of(
                            Municipality.builder().value("saurimo").label("Saurimo").build(),
                            Municipality.builder().value("cacolo").label("Cacolo").build()
                    ))
                    .build()
    ),
    MALANJE(
            Province.builder()
                    .value("malanje")
                    .label("Malanje")
                    .municipalities(List.of(
                            Municipality.builder().value("malanje").label("Malanje").build(),
                            Municipality.builder().value("calandula").label("Calandula").build()
                    ))
                    .build()
    ),
    MOXICO(
            Province.builder()
                    .value("moxico")
                    .label("Moxico")
                    .municipalities(List.of(
                            Municipality.builder().value("luena").label("Luena").build(),
                            Municipality.builder().value("cazombo").label("Cazombo").build()
                    ))
                    .build()
    ),
    NAMIBE(
            Province.builder()
                    .value("namibe")
                    .label("Namibe")
                    .municipalities(List.of(
                            Municipality.builder().value("namibe").label("Namibe").build(),
                            Municipality.builder().value("tombua").label("Tômbua").build()
                    ))
                    .build()
    ),
    UIGE(
            Province.builder()
                    .value("uige")
                    .label("Uíge")
                    .municipalities(List.of(
                            Municipality.builder().value("uige").label("Uíge").build(),
                            Municipality.builder().value("negage").label("Negage").build()
                    ))
                    .build()
    ),
    ZAIRE(
            Province.builder()
                    .value("zaire")
                    .label("Zaire")
                    .municipalities(List.of(
                            Municipality.builder().value("mbanza_congo").label("Mbanza Congo").build(),
                            Municipality.builder().value("soyos").label("Soyo").build()
                    ))
                    .build()
    );

    private final Province province;

    public static List<Province> listProvinces() {
        return Arrays.stream(ProvinceMunicipalityFaker.values())
                .map(ProvinceMunicipalityFaker::getProvince)
                .toList();
    }

    public static Province random() {
        var provinces = listProvinces();
        var random = RandomGenerator.getDefault();
        var index = random.nextInt(provinces.size());
        return provinces.get(index);
    }
}
