package com.example.project.neisApi.schoolBasicInfo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
public class HeadData {

    @JsonProperty("list_total_count")
    private int listTotalCount;
    @JsonProperty("RESULT")
    private ResultData result;

}
