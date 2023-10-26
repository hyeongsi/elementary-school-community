package com.example.project.dto.notice;

import org.apache.ibatis.type.Alias;

@Alias("NoticeDto")
public class NoticeDto {

	private Long postId;
	private String title;
	private String content;
	private Long good;
	private Long viewCnt;
	private Long memberId;
	private Long categoryId;
	
	public NoticeDto(Long postId, String title, String content, Long good, Long viewCnt, Long memberId, Long categoryId) {
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.good = good;
		this.viewCnt = viewCnt;
		this.memberId = memberId;
		this.categoryId = categoryId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getGood() {
		return good;
	}

	public void setGood(Long good) {
		this.good = good;
	}

	public Long getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(Long viewCnt) {
		this.viewCnt = viewCnt;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "NoticeDto [postId=" + postId + ", title=" + title + ", content=" + content + ", good=" + good
				+ ", viewCnt=" + viewCnt + ", memberId=" + memberId + ", categoryId=" + categoryId + "]";
	}

}
