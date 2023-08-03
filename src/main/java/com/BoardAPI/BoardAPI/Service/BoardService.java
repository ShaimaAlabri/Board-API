package com.BoardAPI.BoardAPI.Service;

import com.BoardAPI.BoardAPI.Models.Board;
import com.BoardAPI.BoardAPI.Repository.BoardRepository;
import com.BoardAPI.BoardAPI.RequstObject.GetBoardRequstObject;
import com.BoardAPI.BoardAPI.ResponseObject.GetBoardResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

//    get by ID
    public GetBoardResponseObject getById(Long boardId){
        Optional<Board> optionalBoard=boardRepository.findById(boardId);
        if (optionalBoard.isPresent()){
            Board board=optionalBoard.get();
            GetBoardResponseObject response=new GetBoardResponseObject();
            response.setBoardId(String.valueOf(board.getId()));
            response.setTitle(board.getTitle());
            return response;
        }
        return null;

    }
    //update
    public GetBoardResponseObject updateBoard(Long boardId, GetBoardRequstObject updateBoard){
        Optional<Board> optionalBoard=boardRepository.findById(boardId);
        if (optionalBoard.isPresent()){
            Board board=optionalBoard.get();
            board.setTitle(updateBoard.getTitle());
            boardRepository.save(board);
            return getById(boardId);
        }
        return null;

    }
//    delete
    public  void deleteBoard(Long id){boardRepository.deleteById(id);}

}
