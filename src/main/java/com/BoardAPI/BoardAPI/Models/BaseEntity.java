package com.BoardAPI.BoardAPI.Models;

import jakarta.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass

public class BaseEntity {
//    Long id;
    Date createDate;
    Date updateDate;
    Boolean isActive;

    public BaseEntity() {
        this.createDate = new Date();
        this.updateDate = new Date();
        this.isActive = true;
    }
}
