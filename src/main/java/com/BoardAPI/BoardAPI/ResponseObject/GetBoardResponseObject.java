package com.BoardAPI.BoardAPI.ResponseObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetBoardResponseObject {
    private Long boardId;
    private String name;
    private Map<Integer, String> columns;
}