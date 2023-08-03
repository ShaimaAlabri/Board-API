package com.BoardAPI.BoardAPI.Controller;

import com.BoardAPI.BoardAPI.Models.Board;
import com.BoardAPI.BoardAPI.RequestObject.GetBoardRequestObject;
import com.BoardAPI.BoardAPI.ResponseObject.GetBoardResponseObject;
import com.BoardAPI.BoardAPI.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/board")
public class BoardController {
    @Autowired
    BoardService boardService;
    private final Map<String, GetBoardResponseObject> boards = new HashMap<>();

    @PostMapping
    public ResponseEntity<GetBoardResponseObject> createBoard(@RequestBody GetBoardRequestObject request) {
        Board board = new Board();
        board.setTitle(request.getTitle());
        Board boardCreate = boardService.createBoard(board);
        GetBoardResponseObject responseObject = new GetBoardResponseObject();
        responseObject.setBoardId(String.valueOf(boardCreate.getId()));
        responseObject.setName(boardCreate.getTitle());
        responseObject.setColumns(getDefaultColumn());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    private String[] getDefaultColumn() {
        return new String[]{"To Do", "In Progress", "Done"};
    }

    //     Get Board
    @GetMapping
    public List<Board> getBoard() {
        return boardService.getBoard();
    }

    //    update
    @PutMapping("/{id}")
    public ResponseEntity<GetBoardResponseObject> updateBoard(@PathVariable Long id, @RequestBody GetBoardRequestObject updateBoard) {
        GetBoardResponseObject responseObject = boardService.updateBoard(id, updateBoard);
        if (responseObject != null) {
            return ResponseEntity.ok(responseObject);

        } else {
            return ResponseEntity.notFound().build();
        }

        }

    @DeleteMapping("/{id]")

    public void deleteBoard (@PathVariable Long id){
        boardService.deleteBoard(id);}

//    get by id
    public GetBoardResponseObject getBoardId (@PathVariable Long id){
        return boardService.getById(id);
    }
}











