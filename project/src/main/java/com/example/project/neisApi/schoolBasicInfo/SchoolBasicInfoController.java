package com.example.project.neisApi.schoolBasicInfo;

import com.example.project.neisApi.schoolBasicInfo.dto.SearchSchoolDto;
import com.example.project.neisApi.schoolBasicInfo.dto.schoolInfo.SchoolInfoResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schoolBasicInfo")
public class SchoolBasicInfoController {

    private final SchoolBasicInfoService schoolBasicInfoService;

    @GetMapping("/apiCall")
    public String apiCall(@RequestParam(defaultValue = "25") int pIndex,
                          @RequestParam(defaultValue = "100") int pSize){
        return schoolBasicInfoService.apiCall(pIndex, pSize);
    }

    // 매달 1일 3시에 api 호출 후 저장
    @Scheduled(cron = "0 0 3 1 * *")
//    @GetMapping("/apiSaveAll")
    public String apiSaveAll() throws JsonProcessingException, SQLException {
        final SchoolInfoResponse apiResponseOne = schoolBasicInfoService.getApiResponseOne();

        final boolean apiCallSuccess = schoolBasicInfoService.apiCallWorkCk(apiResponseOne);
        if(!apiCallSuccess){
            return "fail";
        }

        final int listCount = apiResponseOne.getListCount();
        if(!schoolBasicInfoService.apiAllSave(listCount))
            return "fail";

        return "success";
    }

    // 매달 15일 3시에 api 호출 후 2개월 경과 데이터 삭제
    @Scheduled(cron = "0 0 3 15 * *")
//    @GetMapping("/apiDeleteAll")
    public String apiDeleteAll() throws JsonProcessingException, SQLException {
        schoolBasicInfoService.deleteAllNMonthAgo(2);
        return "success";
    }

/*    @GetMapping("/getSchool")
    public String getSchool(@RequestParam(defaultValue = "25") int pIndex,
                            @RequestParam(defaultValue = "100") int pSize,
                            @RequestParam(required = false) SearchSchoolDto searchSchoolDto){

//        schoolBasicInfoService.selectSchool(pIndex, pSize, searchSchoolDto);
        return "";
    }*/

}
