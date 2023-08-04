package com.BoardAPI.BoardAPI.RequestObject;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Data
public class GetCardRequestObject {
    Long card_id;
    String title;
    String description;
    String section;
}
