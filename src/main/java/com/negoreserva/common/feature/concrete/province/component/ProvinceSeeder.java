package com.negoreserva.common.feature.concrete.province.component;

import com.negoreserva.common.feature.concrete.province.enums.ProvinceFaker;
import com.negoreserva.common.feature.concrete.province.model.Province;
import com.negoreserva.common.feature.concrete.province.service.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProvinceSeeder {
    private final ProvinceService provinceService;

    @Transactional
    public List<Province> seed() {
        List<Province> items = new ArrayList<>();
        for (var province : ProvinceFaker.values()) {
            items.add(provinceService.findOrCreate(province.getProvince()));
        }
        return items;
    }
}
