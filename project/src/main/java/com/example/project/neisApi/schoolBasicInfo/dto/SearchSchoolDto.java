package com.example.project.neisApi.schoolBasicInfo.dto;

import lombok.Getter;
import org.apache.ibatis.type.Alias;

@Getter
@Alias("SearchSchoolDto")
public class SearchSchoolDto {

    private String ATPT_OFCDC_SC_CODE;
    private String SD_SCHUL_CODE;
    private String SCHUL_NM;
    private String SCHUL_KND_SC_NM;
    private String LCTN_SC_NM;
    private String FOND_SC_NM;

}
