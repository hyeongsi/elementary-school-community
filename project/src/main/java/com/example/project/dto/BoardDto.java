package com.example.project.dto;

import org.apache.ibatis.type.Alias;

@Alias("BoardDto")
public class BoardDto {

    Long boardId;
    String boardName;
    String boardCategory;
    Long parentBoardId;

    public BoardDto(Long boardId, String boardName, String boardCategory, Long parentBoardId) {
        this.boardId = boardId;
        this.boardName = boardName;
        this.boardCategory = boardCategory;
        this.parentBoardId = parentBoardId;
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

    public Long getParentBoardId() {
        return parentBoardId;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "boardId=" + boardId +
                ", boardName='" + boardName + '\'' +
                ", boardCategory='" + boardCategory + '\'' +
                ", parentBoardId=" + parentBoardId +
                '}';
    }
}
