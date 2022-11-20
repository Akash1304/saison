package com.akash.saison.controller;

import com.akash.saison.dto.Permit;
import com.akash.saison.service.IPermitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class FoodTruckPermitController {

    @Autowired
    private IPermitService permitService;

    @RequestMapping(value = "/applicant", method = RequestMethod.GET)
    public List<Permit> getByApplicantName(@RequestParam String name){
        return permitService.getByApplicantName(name.trim());
    }


    @RequestMapping(value = "/expiredPermits", method = RequestMethod.GET)
    public List<Permit> getExpiredPermits(@RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date expirationdate){
        return permitService.getExpiredPermits(expirationdate);
    }

    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public List<Permit> getByAddress(@RequestParam String street){
        return permitService.getByAddress(street.trim());
    }
}
