package com.egitim.kardesligi.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egitim.kardesligi.Entity.District;
import com.egitim.kardesligi.Operation.DistrictOperation;

@RestController
@RequestMapping("/egitim")
public class DistrictController {
    @GetMapping("/districts/{cityId}")
    public List<District> GetCities(@PathVariable long cityId) throws Exception{
        return DistrictOperation.GetDistrictsByCityId(cityId);
    }
}
