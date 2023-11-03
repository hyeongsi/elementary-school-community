package com.example.project.dto.page;


import org.apache.ibatis.type.Alias;

@Alias("MemberPageDto")
public class MemberPageDto {

    final int start;
    final int end;
    
    private String keyword;
    private String searchType;
    private int categoryId;
    private String memberId;
    
    
    
    public MemberPageDto(int start, int end, String keyword, String searchType,int categoryId, String memberId) {
		this.start = start;
		this.end = end;
		this.keyword = keyword;
		this.searchType = searchType;
		this.categoryId = categoryId;
		this.memberId = memberId;
	}

	public MemberPageDto(int start, int end) {
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

	public String getMemberId() {
		return memberId;
	}

	
	
    
}
