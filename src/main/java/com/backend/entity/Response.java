package com.backend.entity;

import java.util.List;

import com.backend.entity.api.DataResponse;

public class Response {

    private String operationDate;

    private List<DataResponse> data;

    public Response() {
    }

    public String getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate;
    }

    public List<DataResponse> getData() {
        return data;
    }

    public void setData(List<DataResponse> data) {
        this.data = data;
    }

}
