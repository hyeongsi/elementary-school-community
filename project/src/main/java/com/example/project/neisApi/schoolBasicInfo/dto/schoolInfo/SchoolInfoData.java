package com.example.project.neisApi.schoolBasicInfo.dto.schoolInfo;

import com.example.project.dto.searchSchool.SchoolInfoRow;
import com.example.project.neisApi.schoolBasicInfo.dto.HeadData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchoolInfoData {

    private List<HeadData> head;
    private List<SchoolInfoRow> row;

}
