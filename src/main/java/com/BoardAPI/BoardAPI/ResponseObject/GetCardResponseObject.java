package com.BoardAPI.BoardAPI.ResponseObject;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCardResponseObject {
    private Long cardId; // Update the field name to cardId
    private String title;
    private String description;
    private String section;
}


