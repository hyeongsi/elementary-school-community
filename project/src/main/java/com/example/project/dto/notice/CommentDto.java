package com.example.project.dto.notice;

import org.apache.ibatis.type.Alias;

@Alias("CommentDto")
public class CommentDto {

	private Long memberId;
	private String commentContents;
	private Long postId;
	
	

	public CommentDto(Long memberId, String commentContents, Long postId) {
		this.memberId = memberId;
		this.commentContents = commentContents;
		this.postId = postId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public String getCommentContents() {
		return commentContents;
	}

	public Long getPostId() {
		return postId;
	}

	@Override
	public String toString() {
		return "CommentDto [memberId=" + memberId + ", postId=" + postId + ", commentContents=" + commentContents + "]";
	}

	
	
}
