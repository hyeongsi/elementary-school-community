package com.example.project.dto.notice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LikeDto {

	private String memberId;
	private Long postId;
	private Boolean good;
}
