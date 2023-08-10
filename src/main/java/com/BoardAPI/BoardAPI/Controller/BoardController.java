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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/boards")
@CrossOrigin("*")
public class BoardController {
    @Autowired
    BoardService boardService;

    @PostMapping
    public ResponseEntity<GetBoardResponseObject> createBoard(@RequestBody GetBoardRequestObject request) {
        Board board = new Board();
        //board.setTitle(request.getTitle());
        board.setName(request.getName()); // Set the name field
        Board boardCreate = boardService.createBoard(board);
        GetBoardResponseObject responseObject = new GetBoardResponseObject();
        responseObject.setBoardId(boardCreate.getId());  // No need to convert to Long
        responseObject.setName(boardCreate.getName());  // Set the name field
        responseObject.setColumns(getDefaultColumn());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    private Map<Integer, String> getDefaultColumn() {
        Map<Integer, String> columns = new HashMap<>();
        columns.put(1, "To Do");
        columns.put(2, "In Progress");
        columns.put(3, "Done");
        return columns;
    }

    // Get Board
    @GetMapping
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetBoardResponseObject> getBoardById(@PathVariable Long id) {
        Board board = boardService.getBoardById(id);
        if (board == null) {
            // If the board with the given ID is not found, return a 404 response
            return ResponseEntity.notFound().build();
        }

        GetBoardResponseObject response = new GetBoardResponseObject(
                board.getId(),
                board.getName(),
                board.getColumns()  // Use the getter method for columns
        );
        return ResponseEntity.ok(response);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<GetBoardResponseObject> updateBoard(@PathVariable Long id, @RequestBody GetBoardRequestObject updateBoard) {
        GetBoardResponseObject responseObject = boardService.updateBoard(id, updateBoard);
        if (responseObject != null) {
            return ResponseEntity.ok(responseObject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
    }
}