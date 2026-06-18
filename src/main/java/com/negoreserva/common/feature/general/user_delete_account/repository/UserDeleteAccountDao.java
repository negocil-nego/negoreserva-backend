package com.negoreserva.common.feature.general.user_delete_account.repository;

import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.feature.general.user_delete_account.model.UserDeleteAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface UserDeleteAccountDao extends JpaRepository<UserDeleteAccount, Long> {
    boolean existsByUserAndIsDeletedAndExpiredAtAfter(User user, Boolean isDeleted, Instant now);

    Optional<UserDeleteAccount> findByUserAndInputAndIsDeletedAndExpiredAtAfter(
            User user,
            String input,
            Boolean isDeleted,
            Instant now
    );
}
