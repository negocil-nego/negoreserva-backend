package com.negoreserva.common.feature.concrete.address.component;

import com.negoreserva.common.feature.concrete.address.model.Address;
import com.negoreserva.common.feature.concrete.address.service.AddressService;
import com.negoreserva.common.feature.concrete.address.enums.AddressFaker;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressSeeder {
    private final AddressService addressService;

    public AddressSeeder(AddressService addressService) {
        this.addressService = addressService;
    }

    @Transactional
    public List<Address> seed() {
        List<Address> items = new ArrayList<>();
        for (AddressFaker faker : AddressFaker.values()) {
            items.add(addressService.findOrCreate(faker.getAddress()));
        }
        return items;
    }
}
