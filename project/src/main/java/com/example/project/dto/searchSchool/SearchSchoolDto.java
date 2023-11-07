package com.example.project.dto.searchSchool;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Alias("SearchSchoolDto")
public class SearchSchoolDto {

    @JsonProperty("ATPT_OFCDC_SC_CODE")
    private String atptOfCdcScCode;
    @JsonProperty("SD_SCHUL_CODE")
    private String sdSchulCode;
    @JsonProperty("SCHUL_NM")
    private String schulNm;
    @JsonProperty("SCHUL_KND_SC_NM")
    private String schulKndScNm;
    @JsonProperty("LCTN_SC_NM")
    private String lctnScNm;
    @JsonProperty("FOND_SC_NM")
    private String fondScNm;

}
