package com.negoreserva.common.feature.concrete.user.enums;

import com.negoreserva.common.feature.concrete.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public enum UserFaker {
    BOB(User.builder()
            .name("Bob Silva")
            .username("bob.silva")
            .password("12345678")
            .email("bob.silva@gmail.com")
            .phone("+551111111111")
            .type(UserType.ORGANIZATION)
            .build()
    ),
    JANE(User.builder()
            .name("Jane Santos")
            .username("jane.santos")
            .password("12345678")
            .email("jane.santos@gmail.com")
            .phone("+551111111112")
            .type(UserType.ORGANIZATION)
            .build()
    ),
    JOHN(User.builder()
            .name("John Souza")
            .username("john.souza")
            .password("12345678")
            .email("john.souza@gmail.com")
            .phone("+551111111113")
            .type(UserType.ORGANIZATION)
            .build()
    ),
    MARIA(User.builder()
            .name("Maria Oliveira")
            .username("maria.oliveira")
            .password("12345678")
            .email("maria.oliveira@gmail.com")
            .phone("+551111111114")
            .type(UserType.ORGANIZATION)
            .build()
    ),
    PEDRO(User.builder()
            .name("Pedro Costa")
            .username("pedro.costa")
            .password("12345678")
            .email("pedro.costa@gmail.com")
            .phone("+551111111115")
            .type(UserType.ORGANIZATION)
            .build()
    ),
    ANA(User.builder()
            .name("Ana Rodrigues")
            .username("ana.rodrigues")
            .password("12345678")
            .email("ana.rodrigues@gmail.com")
            .phone("+551111111116")
            .type(UserType.ORGANIZATION)
            .build()
    ),
    CARLOS(User.builder()
            .name("Carlos Pereira")
            .username("carlos.pereira")
            .password("12345678")
            .email("carlos.pereira@gmail.com")
            .type(UserType.ORGANIZATION)
            .phone("+551111111117")
            .build()
    );

    private final User user;
}
