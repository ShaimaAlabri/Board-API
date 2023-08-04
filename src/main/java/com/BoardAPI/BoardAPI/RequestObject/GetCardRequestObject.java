package com.BoardAPI.BoardAPI.RequestObject;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCardRequestObject {
    Long card_id;
    String title;
    String description;
    String section;
}
