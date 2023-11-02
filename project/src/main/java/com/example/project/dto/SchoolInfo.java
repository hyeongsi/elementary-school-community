package com.example.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SchoolInfo {

    String officeOfEducationCode;   // 시도교육청코드
    String schoolCode; // 행정표준코드
    String usergrade;
    String userclass;

    public SchoolInfo() { }

    public SchoolInfo(String officeOfEducationCode, String schoolCode) {
        this.officeOfEducationCode = officeOfEducationCode;
        this.schoolCode = schoolCode;
    }
}
