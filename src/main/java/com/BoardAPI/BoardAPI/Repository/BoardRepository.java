package com.BoardAPI.BoardAPI.Repository;

import com.BoardAPI.BoardAPI.Models.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
}
