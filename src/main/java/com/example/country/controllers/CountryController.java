package com.example.country.controllers;

import com.example.country.bean.Country;
import com.example.country.serices.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {
    @Autowired
    CountryService countryService;

    @GetMapping("/countries")
    public List<Country> getCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/countries/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable(value = "id") int id) {
        try {
            Country country = countryService.getCountryById(id);
            return new ResponseEntity<Country>(country, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/countries/countryname")
    public ResponseEntity<Country> getCountryByName(@RequestParam(value = "name") String countryName) {
        try {
            Country country = countryService.getCountryByName(countryName);
            return new ResponseEntity<Country>(country, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/countries")
    public Country addCountry(@RequestBody Country country) {
        return countryService.addCountry(country);
    }

    @PutMapping("/countries/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable(value = "id") int id, @RequestBody Country country) {
        try {
            Country existCountry = countryService.getCountryById(id);
            existCountry.setCountryName(country.getCountryName());
            existCountry.setCountryCapital(country.getCountryCapital());
            Country updated_Country = countryService.updateCountry(existCountry);
            return new ResponseEntity<Country>(updated_Country, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/countries/{id}")
    public AddResponse deleteCountry(@PathVariable(value = "id") int id) {
        return countryService.deleteCountry(id);
    }
}
