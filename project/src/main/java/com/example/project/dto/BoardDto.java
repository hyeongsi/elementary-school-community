package com.example.project.dto;

import org.apache.ibatis.type.Alias;

@Alias("BoardDto")
public class BoardDto {

    private Long boardId;
    private String boardName;
    private String boardCategory;
    private String categoryName;

    public BoardDto(Long boardId, String boardName, String boardCategory, String categoryName) {
        this.boardId = boardId;
        this.boardName = boardName;
        this.boardCategory = boardCategory;
        this.categoryName = categoryName;
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public String getBoardCategory() {
        return boardCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "boardId=" + boardId +
                ", boardName='" + boardName + '\'' +
                ", boardCategory='" + boardCategory + '\'' +
                ", parentBoardId=" + categoryName +
                '}';
    }
}
