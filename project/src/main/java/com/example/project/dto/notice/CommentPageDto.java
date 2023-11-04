package com.example.project.dto.notice;

import com.example.project.dto.page.Page;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentPageDto {

    private Page page;
    private List<CommentAllJoinDto> commentDtoList;
}

