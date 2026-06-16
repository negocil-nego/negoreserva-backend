package com.negoreserva.common.feature.concrete.user.component;

import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.feature.concrete.user.service.UserService;
import com.negoreserva.common.util.PasswordEncoderGenerator;
import com.negoreserva.common.feature.concrete.user.enums.UserFaker;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserSeeder {
    private final UserService userService;

    @Transactional
    public List<User> seed() {
        List<User> items = new ArrayList<>();
        for (UserFaker uf : UserFaker.values()) {
            var user = uf.getUser();
            user.setPassword(PasswordEncoderGenerator.encode(user.getPassword()));
            items.add(userService.findOrCreate(user));
        }
        return items;
    }
}