package com.backend.service;

import com.backend.entity.api.Client;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserService {

    Client callToUsers() throws JsonProcessingException;
}
