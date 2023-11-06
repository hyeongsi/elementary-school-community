package com.example.project.neisApi.schoolBasicInfo.dto.schoolInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SchoolInfoRow {

    @JsonProperty("ATPT_OFCDC_SC_CODE")     // 시도교육청코드
    private String atptOfcdcScCode;
    @JsonProperty("ATPT_OFCDC_SC_NM")       // 시도교육청명
    private String atptOfcdcScNm;
    @JsonProperty("SD_SCHUL_CODE")          // 행정표준코드
    private String sdSchulCode;
    @JsonProperty("SCHUL_NM")               // 학교명
    private String schulNm;
    @JsonProperty("ENG_SCHUL_NM")           // 영문학교명
    private String engSchulNm;
    @JsonProperty("SCHUL_KND_SC_NM")        // 학교종류명
    private String schulKndScNm;
    @JsonProperty("LCTN_SC_NM")             // 시도명
    private String lctnScNm;
    @JsonProperty("JU_ORG_NM")              // 관할조직명
    private String juOrgNm;
    @JsonProperty("FOND_SC_NM")             // 설립명
    private String fondScNm;
    @JsonProperty("ORG_RDNZC")              // 도로명우편번호
    private String orgRdnzc;
    @JsonProperty("ORG_RDNMA")              // 도로명주소
    private String orgRdnma;
    @JsonProperty("ORG_RDNDA")              // 도로명상세주소
    private String orgRdnda;
    @JsonProperty("ORG_TELNO")              // 전화번호
    private String orgTelno;
    @JsonProperty("HMPG_ADRES")             // 홈페이지주소
    private String hmpgAdres;
    @JsonProperty("COEDU_SC_NM")            // 남녀공학구분명
    private String coeduScNm;
    @JsonProperty("ORG_FAXNO")              // 팩스번호
    private String orgFaxno;
    @JsonProperty("HS_SC_NM")               // 고등학교구분명
    private String hsScNm;
    @JsonProperty("INDST_SPECL_CCCCL_EXST_YN")  // 산업체특별학급존재여부
    private String indstSpeclCccclExstYn;
    @JsonProperty("HS_GNRL_BUSNS_SC_NM")        // 고등학교일반전문구분명
    private String hsGnrlBusnsScNm;
    @JsonProperty("SPCLY_PURPS_HS_ORD_NM")      // 특수목적고등학교계열명
    private String spclyPurpsHsOrdNm;
    @JsonProperty("ENE_BFE_SEHF_SC_NM")         // 입시전후기구분명
    private String eneBfeSehfScNm;
    @JsonProperty("DGHT_SC_NM")             // 주야구분명
    private String dghtScNm;
    @JsonProperty("FOND_YMD")               // 설립일자
    private String fondYmd;
    @JsonProperty("FOAS_MEMRD")             // 개교기념일
    private String foasMemrd;
    @JsonProperty("LOAD_DTM")               // 수정일자
    private String loadDtm;

}
