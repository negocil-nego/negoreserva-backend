package com.negoreserva.common.feature.concrete.province.component;

import com.negoreserva.common.feature.concrete.province.enums.ProvinceMunicipalityFaker;
import com.negoreserva.common.feature.concrete.province.model.Province;
import com.negoreserva.common.feature.concrete.province.repository.ProvinceRepo;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProvinceMunicipalitySeeder {
    private final ProvinceRepo provinceRepo;

    @Transactional
    public List<Province> seed() {
        List<Province> items = new ArrayList<>();

        for (var faker : ProvinceMunicipalityFaker.values()) {
            items.add(findOrCreate(faker.getProvince()));
        }

        return items;
    }

    private Province findOrCreate(Province province) {
        return provinceRepo.findByValue(province.getValue()).orElseGet(() -> {
            province.getMunicipalities().forEach(m -> m.setProvince(province));
            return provinceRepo.save(province);
        });
    }
}
