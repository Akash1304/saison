package com.akash.saison.service;

import com.akash.saison.dto.Permit;
import com.akash.saison.util.HttpHeaderUtil;
import com.akash.saison.util.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermitService implements IPermitService {

    //Local database
    public static List<Permit> permits = new ArrayList<>();
    private static String baseUrl = "https://data.sfgov.org/resource/rqzj-sfat.json";

    @Autowired
    private RestClient restClient;

    public List<Permit> getByApplicantName(String applicant){
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(baseUrl).queryParam("applicant", applicant);
        List<Permit> applicants = restClient.get(uriComponentsBuilder.build().toString(), HttpHeaderUtil.getHeaderJsonData(), new ParameterizedTypeReference<List<Permit>>(){});
        if(applicants==null || applicants.isEmpty()){
            applicants = permits.stream().filter(permit -> applicant.equals(permit.getApplicant())).collect(Collectors.toList());
        }else{
            applicants.addAll(permits.stream().filter(permit -> applicant.equals(permit.getApplicant())).collect(Collectors.toList()));
        }
        return applicants;
    }

    @Override
    public List<Permit> getExpiredPermits(Date expirationDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        String queryString = expirationDate!=null? "'" + dateFormat.format(expirationDate) + "' > expirationdate" : "expirationdate < '" + LocalDateTime.now() + "'";
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(baseUrl).queryParam("$where", queryString);
        List<Permit> applicants = restClient.get(uriComponentsBuilder.build().toString(), HttpHeaderUtil.getHeaderJsonData(), new ParameterizedTypeReference<List<Permit>>(){});

        return applicants;
    }

    @Override
    public List<Permit> getByAddress(String address) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(baseUrl).queryParam("address", address);
        List<Permit> applicants = restClient.get(uriComponentsBuilder.build().toString(), HttpHeaderUtil.getHeaderJsonData(), new ParameterizedTypeReference<List<Permit>>(){});

        return applicants;
    }

    @Override
    public List<Permit> addFoodTruckEntry(Permit permit) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(baseUrl);
        permits.add(permit);
        List<Permit> applicants = restClient.get(uriComponentsBuilder.build().toString(), HttpHeaderUtil.getHeaderJsonData(), new ParameterizedTypeReference<List<Permit>>(){});
        if(applicants==null || applicants.isEmpty()){
            applicants = permits;
        }else{
            applicants.addAll(permits);
        }
        return applicants;
    }
}
