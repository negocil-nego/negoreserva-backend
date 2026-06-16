package com.negoreserva.common.feature.concrete.address.enums;

import com.negoreserva.common.feature.concrete.address.model.Address;
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
                    .country("United States")
                    .state("New York")
                    .city("New York")
                    .neighborhood("Manhattan")
                    .street("123 Main Street")
                    .number("10001")
                    .zipCode("10001")
                    .build()
    ),
    TECHCORP(
            Address.builder()
                    .country("United States")
                    .state("California")
                    .city("San Francisco")
                    .neighborhood("SoMa")
                    .street("456 Tech Avenue")
                    .number("94102")
                    .zipCode("94102")
                    .build()
    ),
    GLOBAL(
            Address.builder()
                    .country("United States")
                    .state("Illinois")
                    .city("Chicago")
                    .neighborhood("Loop")
                    .street("789 Business Blvd")
                    .number("60601")
                    .zipCode("60601")
                    .build()
    ),
    POUSADA_RECANTO(
            Address.builder()
                    .country("Brasil")
                    .state("MG")
                    .city("Monte Verde")
                    .neighborhood("Zona Rural")
                    .street("Estrada das Montanhas")
                    .number("Km 15")
                    .zipCode("37550-000")
                    .build()
    ),
    PENSAO_FAMILIAR(
            Address.builder()
                    .country("Brasil")
                    .state("MG")
                    .city("Ouro Preto")
                    .neighborhood("Centro")
                    .street("Rua do Imperador")
                    .number("450")
                    .zipCode("35400-000")
                    .build()
    ),
    RESTAURANT_SABER(
            Address.builder()
                    .country("Brasil")
                    .state("SP")
                    .city("São Paulo")
                    .neighborhood("Jardins")
                    .street("Avenida Paulista")
                    .number("1500")
                    .zipCode("01310-100")
                    .build()
    ),
    LOJA_BAIRRO(
            Address.builder()
                    .country("Brasil")
                    .state("SP")
                    .city("São Paulo")
                    .neighborhood("Consolação")
                    .street("Rua Augusta")
                    .number("890")
                    .zipCode("01301-001")
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
