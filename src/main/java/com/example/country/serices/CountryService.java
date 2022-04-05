package com.example.country.serices;

import com.example.country.bean.Country;
import com.example.country.controllers.AddResponse;
import com.example.country.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service

public class CountryService{

    @Autowired
    CountryRepository countryRepository;

    public List<Country> getAllCountries(){
        return countryRepository.findAll();
    }

    public Country getCountryById(int id){
        return countryRepository.findById(id).get();
    }

    public Country getCountryByName(String countryName){
        List<Country> countries = countryRepository.findAll();
        Country country = null;
        for (Country con : countries){
            if(con.getCountryName().equalsIgnoreCase(countryName))
                country = con;
        }
        return country;
    }

    public Country addCountry(Country country){
        country.setId(getMaxId());
        return countryRepository.save(country);
    }
    public int getMaxId(){
       return countryRepository.findAll().size()+1;
    }

    public Country updateCountry(Country country){
        countryRepository.save(country);
        return country;
    }

    public AddResponse deleteCountry(int id){
        countryRepository.deleteById(id);
        AddResponse res = new AddResponse();
        res.setMsg("Country deleted !");
        res.setId(id);
        return res;
    }

}
