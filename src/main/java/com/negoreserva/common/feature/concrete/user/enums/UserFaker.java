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
            .password("123")
            .email("bob.silva@gmail.com")
            .phone("+551111111111")
            .build()
    ),
    JANE(User.builder()
            .name("Jane Santos")
            .username("jane.santos")
            .password("123")
            .email("jane.santos@gmail.com")
            .phone("+551111111112")
            .build()
    ),
    JOHN(User.builder()
            .name("John Souza")
            .username("john.souza")
            .password("123")
            .email("john.souza@gmail.com")
            .phone("+551111111113")
            .build()
    ),
    MARIA(User.builder()
            .name("Maria Oliveira")
            .username("maria.oliveira")
            .password("123")
            .email("maria.oliveira@gmail.com")
            .phone("+551111111114")
            .build()
    ),
    PEDRO(User.builder()
            .name("Pedro Costa")
            .username("pedro.costa")
            .password("123")
            .email("pedro.costa@gmail.com")
            .phone("+551111111115")
            .build()
    ),
    ANA(User.builder()
            .name("Ana Rodrigues")
            .username("ana.rodrigues")
            .password("123")
            .email("ana.rodrigues@gmail.com")
            .phone("+551111111116")
            .build()
    ),
    CARLOS(User.builder()
            .name("Carlos Pereira")
            .username("carlos.pereira")
            .password("123")
            .email("carlos.pereira@gmail.com")
            .phone("+551111111117")
            .build()
    );

    private final User user;
}
