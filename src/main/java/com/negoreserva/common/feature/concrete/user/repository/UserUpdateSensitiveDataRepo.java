package com.negoreserva.common.feature.concrete.user.repository;

import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.feature.concrete.user.model.UserUpdateSensitiveData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserUpdateSensitiveDataRepo extends JpaRepository<UserUpdateSensitiveData, Long> {
    Optional<UserUpdateSensitiveData> findByUser(User user);
}
