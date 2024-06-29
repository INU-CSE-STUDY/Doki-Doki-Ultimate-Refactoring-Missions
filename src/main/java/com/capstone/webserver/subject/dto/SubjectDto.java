package com.capstone.webserver.subject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class SubjectDto {
    @Data
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class SubjectMapperDto {
        private String idSubject;
        private String typeSubject;
        private String nameSubject;
        private String profSubject;
        private String timeSubject;
        private Integer creditSubject;
        private String univSubject;
        private String majorSubject;
        private String yearSubject;
        private String semesterSubject;
    }

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
