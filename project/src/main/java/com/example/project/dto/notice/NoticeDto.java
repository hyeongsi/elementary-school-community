package com.example.project.dto.notice;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Alias("NoticeDto")
public class NoticeDto {

	private Long postId;
	private String title;
	private String content;
	private Long good;
	private Long viewCnt;
	private String memberId;
	private Long categoryId;
	private Long commentCount;
  
	public NoticeDto(){}

	@Override
	public String toString() {
		return "NoticeDto [postId=" + postId + ", title=" + title + ", content=" + content + ", good=" + good
				+ ", viewCnt=" + viewCnt + ", memberId=" + memberId + ", categoryId=" + categoryId + "]";
	}

}
