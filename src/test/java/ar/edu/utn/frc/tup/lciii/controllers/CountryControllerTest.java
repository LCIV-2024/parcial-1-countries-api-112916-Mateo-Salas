package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    @Test
    void getAllCountriesOrByCodeOrByName() throws Exception {
        List<CountryDTO> expect = new ArrayList<>();
        expect.add(getCountry());

        when(countryService.getAllCountriesDTOorByNameorCode(null,null)).thenReturn(expect);

        mockMvc.perform(get("/api/countries")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllCountriesByContinent() throws Exception {
        List<CountryDTO> expect = new ArrayList<>();
        expect.add(getCountry());

        when(countryService.getAllCountriesByContinent("Americas")).thenReturn(expect);

        mockMvc.perform(get("/api/countries/Americas/continent")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllCountriesByLanguage() throws Exception {
        List<CountryDTO> expect = new ArrayList<>();
        expect.add(getCountry());

        when(countryService.getAllCountriesByLanguage("Spanish")).thenReturn(expect);

        mockMvc.perform(get("/api/countries/Spanish/language")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private CountryDTO getCountry(){
        CountryDTO countryDTO = new CountryDTO("ARG","Argentina");
        return countryDTO;
    }
}