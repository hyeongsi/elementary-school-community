package com.example.project.neisApi.schoolBasicInfo;

import com.example.project.dto.searchSchool.SearchSchoolDto;
import com.example.project.neisApi.schoolBasicInfo.dto.schoolInfo.SchoolInfoResponse;
import com.example.project.dto.searchSchool.SearchSchoolPageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schoolBasicInfo")
public class SchoolBasicInfoController {

    private final SchoolBasicInfoService schoolBasicInfoService;

    @GetMapping("/apiCallTest")
    public String apiCallTest() throws JsonProcessingException {
        schoolBasicInfoService.getApiResponseOne();
        return "";
    }

    @GetMapping("/apiCall")
    public String apiCall(@RequestParam(defaultValue = "1") int pIndex,
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
        schoolBasicInfoService.deleteAllNMonthAgo(0);
        return "success";
    }

    @GetMapping("/getSchool")
    public SchoolInfoResponse getSchool(@RequestParam(defaultValue = "1") int pIndex,
                            @RequestParam(defaultValue = "100") int pSize,
                            @RequestParam(required = false) String ATPT_OFCDC_SC_CODE,
                            @RequestParam(required = false) String SD_SCHUL_CODE,
                            @RequestParam(required = false) String SCHUL_NM,
                            @RequestParam(required = false) String SCHUL_KND_SC_NM,
                            @RequestParam(required = false) String LCTN_SC_NM,
                            @RequestParam(required = false) String FOND_SC_NM) throws JsonProcessingException {

        final int start = pIndex * pSize - pSize + 1;
        final int end = pIndex * pSize;
        final SearchSchoolDto searchSchoolDto = new SearchSchoolDto(ATPT_OFCDC_SC_CODE, SD_SCHUL_CODE, SCHUL_NM,
                SCHUL_KND_SC_NM, LCTN_SC_NM, FOND_SC_NM);
        final SearchSchoolPageDto searchSchoolPageDto = new SearchSchoolPageDto(start, end, searchSchoolDto);

        return schoolBasicInfoService.selectSchool(pIndex, pSize, searchSchoolPageDto);
    }

}
