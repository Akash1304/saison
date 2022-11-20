package com.akash.saison.service;

import com.akash.saison.dto.Permit;
import com.akash.saison.dto.PermitData;
import com.akash.saison.util.HttpHeaderUtil;
import com.akash.saison.util.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermitService implements IPermitService {

    private static String baseUrl = "https://data.sfgov.org/resource/rqzj-sfat.json";

    @Autowired
    private RestClient restClient;

    public List<Permit> getByApplicantName(String applicant){
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(baseUrl).queryParam("applicant", applicant);
        return restClient.get(uriComponentsBuilder.build().toString(), HttpHeaderUtil.getHeaderJsonData(), new ParameterizedTypeReference<List<Permit>>(){});
    }
}
