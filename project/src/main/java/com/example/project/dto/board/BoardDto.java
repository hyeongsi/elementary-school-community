package com.example.project.dto.board;

import org.apache.ibatis.type.Alias;

@Alias("BoardDto")
public class BoardDto {

    Long boardId;
    String boardName;

    public BoardDto(Long boardId, String boardName) {
        this.boardId = boardId;
        this.boardName = boardName;
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "boardId=" + boardId +
                ", boardName='" + boardName + '\'' +
                '}';
    }
}
