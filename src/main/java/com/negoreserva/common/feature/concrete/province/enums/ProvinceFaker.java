package com.negoreserva.common.feature.concrete.province.enums;

import com.negoreserva.common.feature.concrete.province.model.Province;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProvinceFaker {
    BENGO(Province.builder().value("bengo").label("Bengo").build()),
    BENGUELA(Province.builder().value("benguela").label("Benguela").build()),
    BIE(Province.builder().value("bie").label("Bié").build()),
    CABINDA(Province.builder().value("cabinda").label("Cabinda").build()),
    CUANDO_CUBANGO(Province.builder().value("cuando_cubango").label("Cuando Cubango").build()),
    CUANZA_NORTE(Province.builder().value("cuanza_norte").label("Cuanza Norte").build()),
    CUANZA_SUL(Province.builder().value("cuanza_sul").label("Cuanza Sul").build()),
    CUNENE(Province.builder().value("cunene").label("Cunene").build()),
    HUAMBO(Province.builder().value("huambo").label("Huambo").build()),
    HUILA(Province.builder().value("huila").label("Huíla").build()),
    LUANDA(Province.builder().value("luanda").label("Luanda").build()),
    LUNDA_NORTE(Province.builder().value("lunda_norte").label("Lunda Norte").build()),
    LUNDA_SUL(Province.builder().value("lunda_sul").label("Lunda Sul").build()),
    MALANJE(Province.builder().value("malanje").label("Malanje").build()),
    MOXICO(Province.builder().value("moxico").label("Moxico").build()),
    NAMIBE(Province.builder().value("namibe").label("Namibe").build()),
    UIGE(Province.builder().value("uige").label("Uíge").build()),
    ZAIRE(Province.builder().value("zaire").label("Zaire").build());

    private final Province province;
}