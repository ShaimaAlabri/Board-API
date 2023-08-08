package com.BoardAPI.BoardAPI.Repository;

import com.BoardAPI.BoardAPI.Models.Board;
import com.BoardAPI.BoardAPI.Models.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Cards,Long> {
}
