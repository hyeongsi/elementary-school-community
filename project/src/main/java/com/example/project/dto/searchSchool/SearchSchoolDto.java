package com.example.project.dto.searchSchool;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Getter
@NoArgsConstructor
@Alias("SearchSchoolDto")
public class SearchSchoolDto {

    @JsonProperty("ATPT_OFCDC_SC_CODE")
    private String atptOfcdcScCode;
    @JsonProperty("ATPT_OFCDC_SC_NM")
    private String atptOfcdcScNm;
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

    public SearchSchoolDto(String atptOfcdcScCode, String sdSchulCode, String schulNm, String schulKndScNm, String lctnScNm, String fondScNm) {
        this.atptOfcdcScCode = atptOfcdcScCode;
        this.sdSchulCode = sdSchulCode;
        this.schulNm = schulNm;
        this.schulKndScNm = schulKndScNm;
        this.lctnScNm = lctnScNm;
        this.fondScNm = fondScNm;
    }
}
