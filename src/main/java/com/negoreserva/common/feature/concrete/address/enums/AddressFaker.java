package com.negoreserva.common.feature.concrete.address.enums;

import com.negoreserva.common.feature.concrete.address.model.Address;
import com.negoreserva.common.feature.concrete.municipality.enums.MunicipalityFaker;
import com.negoreserva.common.feature.concrete.province.enums.ProvinceFaker;
import com.negoreserva.common.feature.concrete.province.model.Province;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.random.RandomGenerator;

@Getter
@AllArgsConstructor
public enum AddressFaker {
    ACME(
            Address.builder()
                    .province(ProvinceFaker.LUANDA.getProvince())
                    .municipality(MunicipalityFaker.TALATONA.getMunicipality())
                    .complement("Project nova vida")
                    .build()
    ),
    TECHCORP(
            Address.builder()
                    .province(ProvinceFaker.HUAMBO.getProvince())
                    .municipality(MunicipalityFaker.HUAMBO.getMunicipality())
                    .complement("Bairro dos Queimados")
                    .build()
    ),
    GLOBAL(
            Address.builder()
                    .province(ProvinceFaker.BENGUELA.getProvince())
                    .municipality(MunicipalityFaker.BENGUELA.getMunicipality())
                    .complement("Quioxe")
                    .build()
    ),
    POUSADA_RECANTO(
            Address.builder()
                    .province(ProvinceFaker.HUILA.getProvince())
                    .municipality(MunicipalityFaker.LUBANGO.getMunicipality())
                    .complement("Lubango")
                    .build()
    ),
    PENSAO_FAMILIAR(
            Address.builder()
                    .province(ProvinceFaker.NAMIBE.getProvince())
                    .municipality(MunicipalityFaker.TOMBUA.getMunicipality())
                    .complement("Tombua")
                    .build()
    ),
    RESTAURANT_SABER(
            Address.builder()
                    .province(ProvinceFaker.LUNDA_SUL.getProvince())
                    .municipality(MunicipalityFaker.CACOLO.getMunicipality())
                    .complement("Cacolo")
                    .build()
    ),
    LOJA_BAIRRO(
            Address.builder()
                    .province(ProvinceFaker.MALANJE.getProvince())
                    .municipality(MunicipalityFaker.CALANDULA.getMunicipality())
                    .complement("Calandula")
                    .build()
    );

    private final Address address;

    public static List<Address> listAddresses() {
        return Arrays.stream(AddressFaker.values())
                .map(AddressFaker::getAddress)
                .toList();
    }

    public static Address random() {
        var addresses = listAddresses();
        var random = RandomGenerator.getDefault();
        var index = random.nextInt(addresses.size());
        return addresses.get(index);
    }
}
