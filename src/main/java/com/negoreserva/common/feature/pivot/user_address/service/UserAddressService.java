package com.negoreserva.common.feature.pivot.user_address.service;

import com.negoreserva.common.feature.pivot.user_address.model.UserAddress;
import com.negoreserva.common.feature.pivot.user_address.repository.UserAddressRepo;
import org.springframework.stereotype.Service;

@Service
public class UserAddressService {
    private final UserAddressRepo userAddressRepo;

    public UserAddressService(UserAddressRepo userAddressRepo) {
        this.userAddressRepo = userAddressRepo;
    }

    public UserAddress findOrCreate(UserAddress userAddress) {
        return userAddressRepo.findByUserAndAddress(
                userAddress.getUser(),
                userAddress.getAddress()
        ).orElseGet(() -> userAddressRepo.save(userAddress));
    }
}
