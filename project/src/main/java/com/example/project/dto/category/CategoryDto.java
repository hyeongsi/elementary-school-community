package com.example.project.dto.category;

import org.apache.ibatis.type.Alias;

@Alias("CategoryDto")
public class CategoryDto {

    private Long categoryId;
    private String categoryName;
    private String boardName;
    private Long boardId;

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getBoardName() {
        return boardName;
    }

    public Long getBoardId() {
        return boardId;
    }
}
