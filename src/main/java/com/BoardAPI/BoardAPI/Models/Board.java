package com.BoardAPI.BoardAPI.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String title;

    @ElementCollection
    @CollectionTable(name = "board_columns",joinColumns = @JoinColumn(name="board_id"))
    @MapKeyColumn(name = "column_index")
    private Map<Integer,String> columns;



}
