package com.example.project.dto.notice;

import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import lombok.Getter;

@Getter
@NoArgsConstructor
@Alias("CommentDto")
public class CommentDto {

	private String memberId;
	private String commentContents;
	private Long postId;
	private Long parentCommentId;
	private Long commentId;
	

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


	@Override
	public String toString() {
		return "CommentDto [memberId=" + memberId + ", commentContents=" + commentContents + ", postId=" + postId
				+ ", parentCommentId=" + parentCommentId + ", commentId=" + commentId + "]";
	}
	
}
