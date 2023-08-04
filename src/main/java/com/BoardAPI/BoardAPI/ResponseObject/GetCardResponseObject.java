package com.BoardAPI.BoardAPI.ResponseObject;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCardResponseObject {
    Long card_id;
    String title;
    String description;
    String section;
}
