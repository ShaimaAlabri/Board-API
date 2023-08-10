package com.BoardAPI.BoardAPI.Service;

import com.BoardAPI.BoardAPI.Models.Board;
import com.BoardAPI.BoardAPI.Repository.BoardRepository;
import com.BoardAPI.BoardAPI.RequestObject.GetBoardRequestObject;
import com.BoardAPI.BoardAPI.ResponseObject.GetBoardResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;

    // Create board
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    // Get all boards
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    // Get board by ID
    public Board getBoardById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    // Update
    public GetBoardResponseObject updateBoard(Long boardId, GetBoardRequestObject updateBoard) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            board.setName(updateBoard.getName());
            boardRepository.save(board);
            return getBoardResponseObject(boardId);  // Use the appropriate method
        }
        return null;
    }

    // Delete
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    public GetBoardResponseObject getBoardResponseObject(Long boardId) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            GetBoardResponseObject response = new GetBoardResponseObject();
            response.setName(board.getName()); // Change setTitle to setName
            return response;
        }
        return null;
    }
}