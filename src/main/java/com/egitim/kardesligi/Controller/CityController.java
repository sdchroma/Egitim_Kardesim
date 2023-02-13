package com.egitim.kardesligi.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egitim.kardesligi.Entity.City;
import com.egitim.kardesligi.Operation.CityOperations;

@RestController
@RequestMapping("/egitim")
public class CityController {
    
    @GetMapping("/cities")
    public List<City> GetCities() throws Exception{
        return CityOperations.GetCities();
    }
}
