package com.example.project.dto.notice;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Alias("CommentDto")
public class CommentDto {

	private String memberId;
	private String commentContents;
	private Long postId;
	private Long parentCommentId;
	private Long commentId;
	private boolean delCheck;
	private String reply_time;

	public CommentDto(){}

	public CommentDto(String memberId, String commentContents, Long postId) {
		this.memberId = memberId;
		this.commentContents = commentContents;
		this.postId = postId;
		
	}


	public CommentDto(String memberId, String commentContents, Long postId, Long parentCommentId) {
		this.memberId = memberId;
		this.commentContents = commentContents;
		this.postId = postId;
		this.parentCommentId = parentCommentId;
	}


	
	public CommentDto(String memberId, String commentContents, Long postId, Long parentCommentId, Long commentId) {
		this.memberId = memberId;
		this.commentContents = commentContents;
		this.postId = postId;
		this.parentCommentId = parentCommentId;
		this.commentId = commentId;
	}
	
	public CommentDto(String memberId, String commentContents, Long postId, Long parentCommentId, Long commentId, String reply_time) {
		this.memberId = memberId;
		this.commentContents = commentContents;
		this.postId = postId;
		this.parentCommentId = parentCommentId;
		this.commentId = commentId;
		this.reply_time = reply_time;
	}
	
	
	

	@Override
	public String toString() {
		return "CommentDto [memberId=" + memberId + ", commentContents=" + commentContents + ", postId=" + postId
				+ ", parentCommentId=" + parentCommentId + ", commentId=" + commentId + ", delCheck=" + delCheck
				+ ", reply_time=" + reply_time + "]";
	}

	
	

	
}
