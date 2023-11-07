package com.example.project.neisApi.schoolBasicInfo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HeadData {

    @JsonProperty("list_total_count")
    private int listTotalCount;
    @JsonProperty("RESULT")
    private ResultData result;

}
