package com.capstone.webserver.subject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class SubjectDto {
    @Data
    @AllArgsConstructor
    @Builder
    public static class SubjectResponseDto {
        private String idSubject;
        private String typeSubject;
        private String nameSubject;
        private String profSubject;
        private String timeSubject;
        private Integer creditSubject;
        private String univSubject;
        private String majorSubject;
    }
}
