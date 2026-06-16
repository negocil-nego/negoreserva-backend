package com.negoreserva.common.feature.concrete.address.service;

import com.negoreserva.common.feature.concrete.address.repository.AddressRepo;
import com.negoreserva.common.feature.concrete.address.model.Address;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends ConcreteService<Address> {
    private final AddressRepo repository;

    public AddressService(AddressRepo repository) {
        super(repository);
        this.repository = repository;
    }

    public Address findOrCreate(Address address) {
        return repository.findByComplementAndProvinceAndMunicipality(
                address.getComplement(),
                address.getProvince(),
                address.getMunicipality()
        ).orElseGet(() -> save(address));
    }
}
