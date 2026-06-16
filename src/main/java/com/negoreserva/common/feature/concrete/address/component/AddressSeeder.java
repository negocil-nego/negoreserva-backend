package com.negoreserva.common.feature.concrete.address.component;

import com.negoreserva.common.feature.concrete.address.model.Address;
import com.negoreserva.common.feature.concrete.address.service.AddressService;
import com.negoreserva.common.feature.concrete.address.enums.AddressFaker;
import com.negoreserva.common.feature.concrete.municipality.model.Municipality;
import com.negoreserva.common.feature.concrete.province.model.Province;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressSeeder {
    private final AddressService addressService;

    @Setter
    private List<Province> provinces;

    @Setter
    private List<Municipality> municipalities;

    public AddressSeeder(AddressService addressService) {
        this.addressService = addressService;
    }

    @Transactional
    public List<Address> seed() {
        List<Address> items = new ArrayList<>();
        for (var faker : AddressFaker.values()) {
            var address = faker.getAddress();

            var province = provinces.stream()
                    .filter(it -> it.getValue().equals(address.getProvince().getValue()))
                    .findFirst()
                    .orElse(null);

            var municipality = municipalities.stream()
                    .filter(it -> it.getValue().equals(address.getMunicipality().getValue()))
                    .findFirst()
                    .orElse(null);

            if (province == null || municipality == null) continue;

            address.setProvince(province);
            address.setMunicipality(municipality);
            address.setDefault(true);

            items.add(addressService.findOrCreate(address));
        }
        return items;
    }
}
