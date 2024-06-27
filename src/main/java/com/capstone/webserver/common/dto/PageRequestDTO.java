package com.capstone.webserver.common.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PageRequestDTO {
    private int page;
    private int size;

    public PageRequestDTO() {
        this(0, 10);
    }

    public PageRequestDTO(int page, int size) {
        this.page = page;
        this.size = size;
    }
}