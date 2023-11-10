package com.example.project.dto.notice;

import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
	private String writeTime;
	private Long rm;
	
	public NoticeDto(Long postId, String title, String content, Long good, Long viewCnt, String memberId, Long categoryId, Long commentCount,String writeTime) {
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.good = good;
		this.viewCnt = viewCnt;
		this.memberId = memberId;
		this.categoryId = categoryId;
		this.commentCount = commentCount;
		this.writeTime = writeTime;
	}
	
	public NoticeDto(Long postId, String title, String content, Long good, Long viewCnt, String memberId, Long categoryId, String writeTime) {
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.good = good;
		this.viewCnt = viewCnt;
		this.memberId = memberId;
		this.categoryId = categoryId;
		this.writeTime = writeTime;
	}
	
	public NoticeDto(Long postId, String title, String content, Long good, Long viewCnt, String memberId, Long categoryId) {
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.good = good;
		this.viewCnt = viewCnt;
		this.memberId = memberId;
		this.categoryId = categoryId;
	}
	
	

	@Override
	public String toString() {
		return "NoticeDto [postId=" + postId + ", title=" + title + ", content=" + content + ", good=" + good
				+ ", viewCnt=" + viewCnt + ", memberId=" + memberId + ", categoryId=" + categoryId + ", writeTime="
				+ writeTime + "]";
	}

	public NoticeDto(Long postId, String title, String content, Long good, Long viewCnt, String memberId,
			Long categoryId, Long commentCount, String writeTime, Long rm) {
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.good = good;
		this.viewCnt = viewCnt;
		this.memberId = memberId;
		this.categoryId = categoryId;
		this.commentCount = commentCount;
		this.writeTime = writeTime;
		this.rm = rm;
	}

	

	

	

}
