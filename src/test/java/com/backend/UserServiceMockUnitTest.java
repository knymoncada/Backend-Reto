package com.backend;

import com.backend.config.Application;
import com.backend.entity.api.Client;
import com.backend.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
class UserServiceMockUnitTest {
    private final ObjectMapper mapper = new ObjectMapper();

    private HttpEntity<String> entity;
    private String urlClient;
    @InjectMocks
    private UserServiceImpl userService = new UserServiceImpl();
    @Spy
    private RestTemplate restTemplate;
    @Mock
    private Application application;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        urlClient = "https://reqres.in/api/users";
        
        HttpHeaders headers = new HttpHeaders();
    	headers.add("user-agent", "Application");
    	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	entity = new HttpEntity<>(headers);
    	
    }

    @Test
    @DisplayName("Consulta get cliente")
    void validateClientThenReturnsDataIsSuccessful() throws IOException {
        Path path = Paths.get("src/test/resources/client.json");
        String clientOfFile = new String(Files.readAllBytes(path));
        Client cli = mapper.readValue(clientOfFile, Client.class);
        
        Mockito.when(application.getApiHost()).thenReturn(urlClient);
        Mockito.when(restTemplate.exchange(application.getApiHost(), HttpMethod.GET, entity, Client.class)).thenReturn(new ResponseEntity<>(cli, HttpStatus.OK));
        Client client = userService.callToUsers();
        Assertions.assertEquals(cli.getPage(), client.getPage());
        Assertions.assertEquals(cli.getTotalPages(), client.getTotalPages());
    }
    
    @Test
    @DisplayName("Consulta atributo 'data' vacio")
    void validateDataVacia() throws IOException {
        Path path = Paths.get("src/test/resources/client_vacio.json");
        String clientOfFile = new String(Files.readAllBytes(path));
        Client cli = mapper.readValue(clientOfFile, Client.class);
        
        Mockito.when(application.getApiHost()).thenReturn(urlClient);
        Mockito.when(restTemplate.exchange(application.getApiHost(), HttpMethod.GET, entity, Client.class)).thenReturn(new ResponseEntity<>(cli, HttpStatus.OK));
        Client client = userService.callToUsers();
        Assertions.assertTrue(cli.getData().isEmpty());
    }
}
