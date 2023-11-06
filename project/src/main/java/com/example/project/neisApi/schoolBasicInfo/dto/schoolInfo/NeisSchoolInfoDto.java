package com.example.project.neisApi.schoolBasicInfo.dto.schoolInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("NeisSchoolInfoDto")
@RequiredArgsConstructor
@Getter
public class NeisSchoolInfoDto {

    private Long sdSchulCode;       // 행정표준코드
    private String atptOfcdcScCode; // 시도교육청코드
    private String schulNm;         // 학교명
    private String orgRdnma;        // 도로명주소
    private String orgTelno;        // 전화번호
    private String hmpgAdres;       // 홈페이지주소
    private String foasMemrd;       // 개교기념일

    public NeisSchoolInfoDto(SchoolInfoRow schoolInfoRow) {
        this.sdSchulCode = Long.parseLong(schoolInfoRow.getSdSchulCode());
        this.atptOfcdcScCode = schoolInfoRow.getAtptOfcdcScCode();
        this.schulNm = schoolInfoRow.getSchulNm();
        this.orgRdnma = schoolInfoRow.getOrgRdnma();
        this.orgTelno = schoolInfoRow.getOrgTelno();
        this.hmpgAdres = schoolInfoRow.getHmpgAdres();
        this.foasMemrd = schoolInfoRow.getFoasMemrd();
    }
}
