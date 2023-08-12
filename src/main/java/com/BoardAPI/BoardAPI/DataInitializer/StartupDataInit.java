package com.BoardAPI.BoardAPI.DataInitializer;

import com.BoardAPI.BoardAPI.Models.Board;
import com.BoardAPI.BoardAPI.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartupDataInit implements ApplicationListener<ApplicationReadyEvent> {
    /**
     * Things to do when the application startup successfully.
     * This class can help us add initial records in the DB in case there are none.
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if(boardRepository.count() == 0){
            Board initialBoard = new Board();
            initialBoard.setId(1L);
            initialBoard.setName("General Sprint Board");
            boardRepository.save(initialBoard);
        }
    }
    @Autowired
    private BoardRepository boardRepository;
}
