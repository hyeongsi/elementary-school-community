package com.example.project.neisApi.schoolBasicInfo;

import com.example.project.neisApi.schoolBasicInfo.dto.ResultData;
import com.example.project.neisApi.schoolBasicInfo.dto.schoolInfo.SchoolInfoResponse;
import com.example.project.neisApi.schoolBasicInfo.dto.schoolInfo.SchoolInfoRow;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Service
public class SchoolBasicInfoService {

    private final SchoolBasicInfoMapper schoolBasicInfoMapper;
    private final WebClient webClient;
    private final ObjectMapper objectMapper;
    private final DataSource dataSource;

    public SchoolBasicInfoService(SchoolBasicInfoMapper schoolBasicInfoMapper, ObjectMapper objectMapper, DataSource dataSource) {
        this.schoolBasicInfoMapper = schoolBasicInfoMapper;
        this.webClient = WebClient.builder()
                                .baseUrl("https://open.neis.go.kr")
                                .exchangeStrategies(ExchangeStrategies.builder()
                                        .codecs(configurer ->
                                                configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)).build())
                                .build();
        this.objectMapper = objectMapper;
        this.dataSource = dataSource;
    }

    public String apiCall(){
        return apiCall(25,100);
    }

    // schoolInfo neis api 호출
    public String apiCall(final int pIndex, final int pSize) {
        final String jsonStr = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/hub/schoolInfo")
                        .queryParam("KEY", "d3348e90712e42a0a67f03cad20d4336")
                        .queryParam("Type", "json")
                        .queryParam("pIndex", pIndex)
                        .queryParam("pSize", pSize)
                        .queryParam("SCHUL_KND_SC_NM", "초등학교")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return jsonStr;
    }

    // schoolInfo neis api 호출 테스트
    public boolean apiCallWorkCk(final SchoolInfoResponse schoolInfoResponse) throws JsonProcessingException {
        // 호출 실패
        if(schoolInfoResponse == null){
            throw new NoSuchFieldError("api의 데이터가 없습니다.");
        }

        final ResultData result = schoolInfoResponse.getResult();

        // 호출 실패
        if(!result.getCode().equals("INFO-000")){
            throw new UnknownError(result.toString());
        }

        return true;
    }

    // api 1개 호출해서 결고 반환 (호출 성공 및 전체 데이터 개수 확인 용도)
    public SchoolInfoResponse getApiResponseOne() throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String ckJsonStr = apiCall(1,1);
        final SchoolInfoResponse ckSchoolInfoResponse = objectMapper.readValue(ckJsonStr, SchoolInfoResponse.class);

        return ckSchoolInfoResponse;
    }

    // api 호출 후 저장
    @Transactional
    public int apiSave(SchoolInfoResponse schoolInfoResponse) throws JsonProcessingException {

        final List<SchoolInfoRow> rowList = schoolInfoResponse.getRow();
        return schoolBasicInfoMapper.insertRowList(rowList);
    }

    // api 전체 데이터 저장
    @Transactional
    public boolean apiAllSave(final int listCount) throws SQLException {
        Connection connection = dataSource.getConnection();
        int insertCnt = 0;

        try {
            connection.setAutoCommit(false);

            // 1페이지 ~ n페이지까지 1000개씩 호출 후 저장
            final int MAX_CALL_SIZE = 1000;
            final int CALL_COUNT = listCount / MAX_CALL_SIZE;

            final List<SchoolInfoRow> row = new LinkedList<>();

            for(int pIndex = 0; pIndex <= CALL_COUNT; pIndex++){

                final String jsonStr = apiCall(pIndex + 1, MAX_CALL_SIZE);
                final SchoolInfoResponse schoolInfoResponse = jsonStrToSchoolInfoResponse(jsonStr);

                insertCnt += apiSave(schoolInfoResponse);
            }

            System.out.println("전체 " + listCount + "개의 데이터 중 "+ insertCnt + "개의 schoolInfo 저장 완료");
        }catch (Exception e){
            e.printStackTrace();
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        connection.commit();
        return true;
    }

    // jsonString 데이터 SchoolInfoResponse로 파싱
    public SchoolInfoResponse jsonStrToSchoolInfoResponse(String jsonStr) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonStr, SchoolInfoResponse.class);
    }

    // school DB 삭제
    public int deleteAllNMonthAgo(final int month){
        int cnt = schoolBasicInfoMapper.deleteAllNMonthAgo(month);
        System.out.println(cnt + "개의 데이터가 삭제되었습니다.");
        return cnt;
    }
}
