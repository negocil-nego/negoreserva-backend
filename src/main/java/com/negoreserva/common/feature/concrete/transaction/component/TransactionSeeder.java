package com.negoreserva.common.feature.concrete.transaction.component;

import com.negoreserva.common.feature.concrete.product.model.Product;
import com.negoreserva.common.feature.concrete.transaction.enums.TransactionData;
import com.negoreserva.common.feature.concrete.transaction.model.Transaction;
import com.negoreserva.common.feature.concrete.transaction.service.TransactionService;
import com.negoreserva.common.feature.concrete.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class TransactionSeeder {
    private final TransactionService transactionService;

    @Setter
    private List<User> users;

    @Setter
    private List<Product> products;

    @Transactional
    public List<Transaction> seed() {
        List<Transaction> items = new ArrayList<>();

        for (var data : TransactionData.values()) {
            var user = users.stream()
                    .filter(it -> it.getName().equals(data.getTransaction().getUser().getName()))
                    .findFirst()
                    .orElse(null);

            var product = products.stream()
                    .filter(it -> it.getName().equals(data.getTransaction().getProduct().getName()))
                    .findFirst()
                    .orElse(null);

            if (user == null || product == null) continue;

            var random = ThreadLocalRandom.current();
            var item = Transaction.builder()
                    .user(user)
                    .product(product)
                    .price(product.getPrice())
                    .amount(random.nextInt(1,10))
                    .build();

            items.add(transactionService.findOrCreate(item));
        }

        return items;
    }
}
