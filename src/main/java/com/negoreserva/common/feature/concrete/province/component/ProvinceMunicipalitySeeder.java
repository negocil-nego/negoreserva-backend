package com.negoreserva.common.feature.concrete.province.component;

import com.negoreserva.common.feature.concrete.municipality.model.Municipality;
import com.negoreserva.common.feature.concrete.province.model.Province;
import com.negoreserva.common.feature.concrete.province.repository.ProvinceRepo;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProvinceMunicipalitySeeder {
    private final ProvinceRepo provinceRepo;

    @Transactional
    public List<Province> seed() {
        List<Province> items = new ArrayList<>();

        for (var entry : DATA.entrySet()) {
            var provinceValue = entry.getKey();
            var provinceLabel = entry.getValue().label();
            var municipios = entry.getValue().municipios();

            var province = Province.builder()
                    .value(provinceValue)
                    .label(provinceLabel)
                    .build();

            var municipalities = municipios.stream()
                    .map(m -> Municipality.builder()
                            .value(m.value())
                            .label(m.label())
                            .province(province)
                            .build())
                    .toList();
            province.setMunicipalities(new ArrayList<>(municipalities));
            items.add(provinceRepo.save(province));
        }

        return items;
    }

    private record MunicipioData(String value, String label) {}
    private record ProvinceData(String label, List<MunicipioData> municipios) {}

    private static final Map<String, ProvinceData> DATA = Map.ofEntries(
            Map.entry("bengo", new ProvinceData("Bengo", List.of(
                    new MunicipioData("dande", "Dande"),
                    new MunicipioData("ambriz", "Ambriz"),
                    new MunicipioData("nambuangongo", "Nambuangongo")
            ))),
            Map.entry("benguela", new ProvinceData("Benguela", List.of(
                    new MunicipioData("benguela", "Benguela"),
                    new MunicipioData("lobito", "Lobito"),
                    new MunicipioData("catumbela", "Catumbela"),
                    new MunicipioData("baia_farta", "Baía Farta")
            ))),
            Map.entry("bie", new ProvinceData("Bié", List.of(
                    new MunicipioData("kuito", "Kuito"),
                    new MunicipioData("andulo", "Andulo"),
                    new MunicipioData("catabola", "Catabola")
            ))),
            Map.entry("cabinda", new ProvinceData("Cabinda", List.of(
                    new MunicipioData("cabinda", "Cabinda"),
                    new MunicipioData("cacongo", "Cacongo"),
                    new MunicipioData("buco_zau", "Buco-Zau")
            ))),
            Map.entry("cuando_cubango", new ProvinceData("Cuando Cubango", List.of(
                    new MunicipioData("menongue", "Menongue"),
                    new MunicipioData("cuito_cuanavale", "Cuito Cuanavale")
            ))),
            Map.entry("cuanza_norte", new ProvinceData("Cuanza Norte", List.of(
                    new MunicipioData("ndalatando", "Ndalatando"),
                    new MunicipioData("golungo_alto", "Golungo Alto")
            ))),
            Map.entry("cuanza_sul", new ProvinceData("Cuanza Sul", List.of(
                    new MunicipioData("sumbe", "Sumbe"),
                    new MunicipioData("porto_amboim", "Porto Amboim"),
                    new MunicipioData("wakukungo", "Waku Kungo")
            ))),
            Map.entry("cunene", new ProvinceData("Cunene", List.of(
                    new MunicipioData("ondjiva", "Ondjiva"),
                    new MunicipioData("cuanhama", "Cuanhama")
            ))),
            Map.entry("huambo", new ProvinceData("Huambo", List.of(
                    new MunicipioData("huambo", "Huambo"),
                    new MunicipioData("bailundo", "Bailundo"),
                    new MunicipioData("caala", "Caála")
            ))),
            Map.entry("huila", new ProvinceData("Huíla", List.of(
                    new MunicipioData("lubango", "Lubango"),
                    new MunicipioData("matala", "Matala"),
                    new MunicipioData("chibia", "Chibia")
            ))),
            Map.entry("luanda", new ProvinceData("Luanda", List.of(
                    new MunicipioData("luanda", "Luanda"),
                    new MunicipioData("talatona", "Talatona"),
                    new MunicipioData("viana", "Viana"),
                    new MunicipioData("cazenga", "Cazenga"),
                    new MunicipioData("belas", "Belas"),
                    new MunicipioData("cacuaco", "Cacuaco"),
                    new MunicipioData("quiçama", "Quiçama"),
                    new MunicipioData("iccolo_e_bengo", "Ícolo e Bengo")
            ))),
            Map.entry("lunda_norte", new ProvinceData("Lunda Norte", List.of(
                    new MunicipioData("lucapa", "Lucapa"),
                    new MunicipioData("dundo", "Dundo")
            ))),
            Map.entry("lunda_sul", new ProvinceData("Lunda Sul", List.of(
                    new MunicipioData("saurimo", "Saurimo"),
                    new MunicipioData("cacolo", "Cacolo")
            ))),
            Map.entry("malanje", new ProvinceData("Malanje", List.of(
                    new MunicipioData("malanje", "Malanje"),
                    new MunicipioData("calandula", "Calandula")
            ))),
            Map.entry("moxico", new ProvinceData("Moxico", List.of(
                    new MunicipioData("luena", "Luena"),
                    new MunicipioData("cazombo", "Cazombo")
            ))),
            Map.entry("namibe", new ProvinceData("Namibe", List.of(
                    new MunicipioData("namibe", "Namibe"),
                    new MunicipioData("tombua", "Tômbua")
            ))),
            Map.entry("uige", new ProvinceData("Uíge", List.of(
                    new MunicipioData("uige", "Uíge"),
                    new MunicipioData("negage", "Negage")
            ))),
            Map.entry("zaire", new ProvinceData("Zaire", List.of(
                    new MunicipioData("mbanza_congo", "Mbanza Congo"),
                    new MunicipioData("soyos", "Soyo")
            )))
    );
}
