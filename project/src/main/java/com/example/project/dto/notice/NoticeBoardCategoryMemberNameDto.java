package com.example.project.dto.notice;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Alias("NoticeBoardCategoryMemberNameDto")
public class NoticeBoardCategoryMemberNameDto {

    private Long postId;
    private Long boardId;
    private String title;
    private String content;
    private Long viewCnt;
    private String boardName;
    private String categoryName;
    private String name;    // memberName

}
