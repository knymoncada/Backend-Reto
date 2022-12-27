package com.backend.web;

import com.backend.entity.Response;
import com.backend.entity.api.Client;
import com.backend.entity.api.DataClient;
import com.backend.entity.api.DataResponse;
import com.backend.service.UserService;
import com.backend.util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/backend")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/users")
    public Response getResponse() throws JsonProcessingException {
        String formattedDate = Util.applyFormatIsoDateTime(new Date().toString());
        Client client = service.callToUsers();
        Response rs = new Response();
        List<DataResponse> listDataRs = new ArrayList<>();
        for (DataClient dataIn : client.getData()) {
            DataResponse dataRs = new DataResponse();
            dataRs.setEmail(dataIn.getEmail());
            dataRs.setId(dataIn.getId());
            dataRs.setLastName(dataIn.getLastName());
            listDataRs.add(dataRs);
        }
        rs.setData(listDataRs);
        rs.setOperationDate(formattedDate);
        return rs;
    }


}
