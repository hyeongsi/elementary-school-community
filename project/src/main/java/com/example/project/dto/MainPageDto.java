package com.example.project.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@Alias("MainPageDto")
public class MainPageDto {
	private Long postId;
	private String title;
	private String content;
	private Long viewCnt;
	private String memberName;
	private String categoryName;
	private String boardName;
	private Long likeCount;
	
	
	public MainPageDto(Long postId, String title, String content, Long viewCnt, String memberName, Long likeCount) {
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.viewCnt = viewCnt;
		this.memberName = memberName;
		this.likeCount = likeCount;
	}

	public MainPageDto(Long postId, String title, String content, Long viewCnt, String memberName, String categoryName,
			String boardName, Long likeCount) {
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.viewCnt = viewCnt;
		this.memberName = memberName;
		this.categoryName = categoryName;
		this.boardName = boardName;
		this.likeCount = likeCount;
	}
	
	@Override
	public String toString() {
		return "MainPageDto [postId=" + postId + ", title=" + title + ", content=" + content + ", viewCnt=" + viewCnt
				+ ", memberName=" + memberName + ", likeCount=" + likeCount + ", categoryName=" + categoryName
				+ ", boardName=" + boardName + "]";
	}
	
	
	
	
	
}
