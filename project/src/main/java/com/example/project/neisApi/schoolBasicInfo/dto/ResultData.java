package com.example.project.neisApi.schoolBasicInfo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ResultData {

    @JsonProperty("CODE")
    private String code;
    @JsonProperty("MESSAGE")
    private String message;

    @Override
    public String toString() {
        return "ResultData{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
