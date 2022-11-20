package com.akash.saison.controller;

import com.akash.saison.dto.Permit;
import com.akash.saison.dto.PermitData;
import com.akash.saison.service.IPermitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class FoodTruckController {

    @Autowired
    private IPermitService permitService;

    @RequestMapping(value = "/applicant", method = RequestMethod.GET)
    public List<Permit> getByApplicantName(@RequestParam String name){
        return permitService.getByApplicantName(name);
    }
}
