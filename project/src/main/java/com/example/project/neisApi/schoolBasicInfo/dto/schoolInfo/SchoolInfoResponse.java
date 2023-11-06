package com.example.project.neisApi.schoolBasicInfo.dto.schoolInfo;

import com.example.project.neisApi.schoolBasicInfo.dto.NeisApiResponse;
import com.example.project.neisApi.schoolBasicInfo.dto.ResultData;
import lombok.Getter;

import java.util.List;


@Getter
public class SchoolInfoResponse implements NeisApiResponse {

    private List<SchoolInfoData> schoolInfo;

    public int getListCount(){
       return schoolInfo.get(0).getHead().get(0).getListTotalCount();
    }

    public ResultData getResult(){
        return schoolInfo.get(0).getHead().get(1).getResult();
    }

    public List<SchoolInfoRow> getRow(){
        return schoolInfo.get(1).getRow();
    }
}
