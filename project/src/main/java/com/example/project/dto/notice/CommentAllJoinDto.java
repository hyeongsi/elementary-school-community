package com.example.project.dto.notice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Alias("CommentAllJoinDto")
public class CommentAllJoinDto {

    private Long commentId;
    private String memberId;
    private Long postId;
    private String commentContents;
    private String boardName;
    private String categoryName;
    private String name;

}
