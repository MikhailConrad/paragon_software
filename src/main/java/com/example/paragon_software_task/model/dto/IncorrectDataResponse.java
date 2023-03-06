package com.example.paragon_software_task.model.dto;

public class IncorrectDataResponse {

    private String info;

    public IncorrectDataResponse(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
