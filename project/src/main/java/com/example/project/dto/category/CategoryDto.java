package com.example.project.dto.category;

import org.apache.ibatis.type.Alias;

@Alias("CategoryDto")
public class CategoryDto {

    private Long categoryId;
    private String categoryName;
    private String boardName;

    public CategoryDto(Long categoryId, String categoryName, String boardName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.boardName = boardName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getBoardName() {
        return boardName;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", boardName='" + boardName + '\'' +
                '}';
    }
}
