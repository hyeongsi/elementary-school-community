package com.example.project.neisApi.schoolBasicInfo;

import com.example.project.neisApi.schoolBasicInfo.dto.schoolInfo.SchoolInfoRow;
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
}
