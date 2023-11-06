package com.example.project.neisApi.schoolBasicInfo.dto.schoolInfo;

import com.example.project.neisApi.schoolBasicInfo.dto.HeadData;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class SchoolInfoData {

    private List<HeadData> head;
    private List<SchoolInfoRow> row;

}
