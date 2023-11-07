package com.example.project.dto.searchSchool;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Getter
@NoArgsConstructor
@Alias("SearchSchoolPageDto")
public class SearchSchoolPageDto {

    private int start;
    private int end;
    private SearchSchoolDto searchSchoolDto;

    public SearchSchoolPageDto(int start, int end, SearchSchoolDto searchSchoolDto) {
        this.start = start;
        this.end = end;
        this.searchSchoolDto = searchSchoolDto;
    }
}
