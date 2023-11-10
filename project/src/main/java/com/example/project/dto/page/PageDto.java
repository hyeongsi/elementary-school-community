package com.example.project.dto.page;


import org.apache.ibatis.type.Alias;

@Alias("PageDto")
public class PageDto {

    final int start;
    final int end;
    
    private String keyword;
    private String searchType;
    private int categoryId;
    private String write_Time;

	public PageDto(int start, int end, String keyword, String searchType, int categoryId, String write_Time) {
		super();
		this.start = start;
		this.end = end;
		this.keyword = keyword;
		this.searchType = searchType;
		this.categoryId = categoryId;
		this.write_Time = write_Time;
	}

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

	public String getKeyword() {
		return keyword;
	}

	public String getSearchType() {
		return searchType;
	}

	public int getCategoryId() {
		return categoryId;
	}   
}
