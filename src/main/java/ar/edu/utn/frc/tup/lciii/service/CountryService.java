package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {

        private final CountryRepository countryRepository;

        private final RestTemplate restTemplate;

        public List<Country> getAllCountries() {
                String url = "https://restcountries.com/v3.1/all";
                List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);
                return response.stream().map(this::mapToCountry).collect(Collectors.toList());
        }

        /**
         * Agregar mapeo de campo cca3 (String)
         * Agregar mapeo campos borders ((List<String>))
         */
        private Country mapToCountry(Map<String, Object> countryData) {
                Map<String, Object> nameData = (Map<String, Object>) countryData.get("name");
                String codeCca3 = countryData.get("cca3").toString();
                return Country.builder()
                        .name((String) nameData.get("common"))
                        .code(codeCca3)
                        .population(((Number) countryData.get("population")).longValue())
                        .area(((Number) countryData.get("area")).doubleValue())
                        .region((String) countryData.get("region"))
                        .languages((Map<String, String>) countryData.get("languages"))
                        .borders((List<String>) countryData.get("borders"))
                        .build();
        }

        public List<CountryDTO> getAllCountriesDTOorByNameorCode(String nombre, String code){
                List<CountryDTO> result = new ArrayList<>();
                //Objects.nonNull
                List<Country> allCountries = getAllCountries();
                if (Objects.nonNull(nombre)){
                        allCountries.forEach(country -> {
                                if(country.getName().equalsIgnoreCase(nombre)){
                                        result.add(mapToDTO(country));
                                }
                        });
                } else if (Objects.nonNull(code)){
                        allCountries.forEach(country -> {
                                if(country.getCode().equalsIgnoreCase(code)){
                                        result.add(mapToDTO(country));
                                }
                        });
                } else {
                        allCountries.forEach(country -> {
                                result.add(mapToDTO(country));
                        });

                }

                return result;
        }


        private CountryDTO mapToDTO(Country country) {
                return new CountryDTO(country.getCode(), country.getName());
        }

        public List<CountryDTO> getAllCountriesByContinent(String continent) {
                List<CountryDTO> result = new ArrayList<>();
                //Objects.nonNull
                List<Country> allCountries = getAllCountries();
                allCountries.forEach(country -> {
                        if (country.getRegion().equalsIgnoreCase(continent)){
                                result.add(mapToDTO(country));
                        }
                });
                return result;
        }

        public List<CountryDTO> getAllCountriesByLanguage(String language) {
                List<CountryDTO> result = new ArrayList<>();
                List<Country> allCountries = getAllCountries();
                allCountries.forEach(country -> {
                        if (Objects.nonNull(country.getLanguages())){
                                if (country.getLanguages().containsValue(language)){
                                        result.add(mapToDTO(country));
                                }
                        }
                });
                return result;
        }
}