package com.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Application {

    @Value("${api.host.baseurl}")
    private String apiHost;
    
    public String getApiHost() {
    	return this.apiHost;
    }
}
