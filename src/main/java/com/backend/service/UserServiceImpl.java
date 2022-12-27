package com.backend.service;

import com.backend.config.Application;
import com.backend.entity.api.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Application application;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Client callToUsers() throws JsonProcessingException {
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("user-agent", "Application");
    	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	HttpEntity<String> entity = new HttpEntity<>(headers);

    	ResponseEntity<String> response = restTemplate.exchange(application.getApiHost(), HttpMethod.GET, entity, String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Client rs = mapper.readValue(response.getBody(), Client.class);
        return rs;
    }
}
