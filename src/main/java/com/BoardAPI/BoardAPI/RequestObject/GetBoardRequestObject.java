package com.BoardAPI.BoardAPI.RequestObject;

import com.BoardAPI.BoardAPI.Models.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetBoardRequestObject {
    Board board;
     String title;

    public String getTitle(){return title;}
    public void setTitle(String title){this.title=title;}

}
