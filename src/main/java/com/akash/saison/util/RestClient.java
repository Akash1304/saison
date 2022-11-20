package com.akash.saison.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class RestClient {

    private static final Logger logger = LoggerFactory.getLogger(RestClient.class);

    @Autowired
    @Qualifier("slowRestTemplate")
    private RestTemplate restTemplate;


    public <T> T get(String url, HttpHeaders headers, ParameterizedTypeReference<T> respType) {
        if (headers == null) {
            headers = new HttpHeaders();
        }
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, respType);
        return response.getBody();
    }

    private static HttpHeaders populateHeaders(Map<String, String> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (null != headers) {
            headers.forEach((key, val) -> httpHeaders.put(key, Collections.singletonList(val)));
        }
        return httpHeaders;
    }
}

