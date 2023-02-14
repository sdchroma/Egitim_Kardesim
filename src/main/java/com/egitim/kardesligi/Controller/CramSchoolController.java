package com.egitim.kardesligi.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egitim.kardesligi.Entity.CramSchool;
import com.egitim.kardesligi.Entity.CramSchoolQuota;
import com.egitim.kardesligi.Operation.CramSchoolOperations;

@RestController
@RequestMapping("/egitim")
public class CramSchoolController {

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/cramschools/{districtId}")
    public List<CramSchool> GetCities(@PathVariable long districtId) throws Exception{
        return CramSchoolOperations.GetDistrictsByCityId(districtId);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/cramschools/quotas/{cramSchoolId}")
    public List<CramSchoolQuota> GetCramSchoolQuotasBySchoolId(@PathVariable long cramSchoolId) throws Exception{
        return CramSchoolOperations.GetCramSchoolQuotasBySchoolId(cramSchoolId);
    } 
}
