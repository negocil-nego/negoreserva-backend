package com.negoreserva.common.feature.concrete.municipality.component;

import com.negoreserva.common.feature.concrete.municipality.service.MunicipalityService;
import com.negoreserva.common.feature.concrete.municipality.enums.MunicipalityFaker;
import com.negoreserva.common.feature.concrete.municipality.model.Municipality;
import com.negoreserva.common.feature.concrete.province.model.Province;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MunicipalitySeeder {
    private final MunicipalityService municipalityService;

    @Setter
    private List<Province> provinces;

    @Transactional
    public List<Municipality> seed() {
        List<Municipality> items = new ArrayList<>();

        for (var item : MunicipalityFaker.values()) {
            var municipality = item.getMunicipality();
            var optional = provinces.stream().filter(it -> it.getValue().equals(municipality.getProvince().getValue())).findFirst();
            if(optional.isPresent()) {
                municipality.setProvince(optional.get());
                items.add(municipalityService.findOrCreate(municipality));
            }
        }
        return items;
    }
}