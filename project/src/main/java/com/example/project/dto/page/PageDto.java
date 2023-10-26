package com.example.project.dto.page;

import org.apache.ibatis.type.Alias;

@Alias("PageDto")
public class PageDto {

    private final int start;
    private final int end;

    public PageDto(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
