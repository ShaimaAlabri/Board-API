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




}
