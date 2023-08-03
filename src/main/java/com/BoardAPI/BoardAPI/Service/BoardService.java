package com.BoardAPI.BoardAPI.Service;

import com.BoardAPI.BoardAPI.Models.Board;
import com.BoardAPI.BoardAPI.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
  @Autowired
    BoardRepository boardRepository;
//Create board
    public Board createBoard (Board board){
        return boardRepository.save(board);
    }
//    get all boards
    public List<Board> getBoard(){
        return boardRepository.findAll();
    }
}
