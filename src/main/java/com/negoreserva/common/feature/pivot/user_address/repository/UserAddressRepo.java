package com.negoreserva.common.feature.pivot.user_address.repository;

import com.negoreserva.common.feature.concrete.address.model.Address;
import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.feature.pivot.user_address.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAddressRepo extends JpaRepository<UserAddress, Long> {
    Optional<UserAddress> findByUserAndAddress(User user, Address address);
}
