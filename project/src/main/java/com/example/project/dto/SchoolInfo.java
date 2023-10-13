package com.example.project.dto;

public class SchoolInfo {
    String officeOfEducationCode;   // 시도교육청코드
    int schoolCode; // 행정표준코드

    public SchoolInfo() {}

    public SchoolInfo(String officeOfEducationCode, int schoolCode) {
        this.officeOfEducationCode = officeOfEducationCode;
        this.schoolCode = schoolCode;
    }

    public String getOfficeOfEducationCode() {
        return officeOfEducationCode;
    }

    public void setOfficeOfEducationCode(String officeOfEducationCode) {
        this.officeOfEducationCode = officeOfEducationCode;
    }

    public int getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(int schoolCode) {
        this.schoolCode = schoolCode;
    }
}
