package com.BoardAPI.BoardAPI.ResponseObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetBoardResponseObject {
    String boardId;
    String name;
    String[]columns;

    public String getBoardId(){return boardId;}
    public void setBoardId(String boardId){this.boardId=boardId;}
    public void setTitle (String title){this.name=title;}




}
