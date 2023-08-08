package com.BoardAPI.BoardAPI.Models;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass

public class BaseEntity {
    Long id;
    Data createDate;
    Data updateDate;
}
