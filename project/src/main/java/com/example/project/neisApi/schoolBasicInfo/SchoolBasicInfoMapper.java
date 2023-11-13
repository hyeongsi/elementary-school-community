package com.example.project.neisApi.schoolBasicInfo;

import com.example.project.dto.searchSchool.SearchSchoolPageDto;
import com.example.project.dto.searchSchool.SchoolInfoRow;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SchoolBasicInfoMapper {

    // 모든 데이터 삽입
    public int insertRowList(List<SchoolInfoRow> rowList);
    // n개월 전 데이터 삭제
    public int deleteAllNMonthAgo(int month);
    // 검색, 페이징 후 학교 반환
    public List<SchoolInfoRow> selectSchool(SearchSchoolPageDto searchSchoolPageDto);
    // 검색, 데이터 총 크기 반환
    public int selectSchoolTotalCnt(SearchSchoolPageDto searchSchoolPageDto);
}
