package ar.edu.utn.frc.tup.lciii.controllers;
import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping()
    public List<CountryDTO> getAllCountriesOrByCodeOrByName(@RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "code", required = false)String code){
        return  countryService.getAllCountriesDTOorByNameorCode(nombre,code);
    }

    @GetMapping("/{continent}/continent")
    public List<CountryDTO> getAllCountriesByContinent(@PathVariable String continent){
        return  countryService.getAllCountriesByContinent(continent);
    }

    @GetMapping("/{language}/language")
    public List<CountryDTO> getAllCountriesByLanguage(@PathVariable String language){
        return  countryService.getAllCountriesByLanguage(language);
    }
}