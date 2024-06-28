package com.capstone.webserver.common.response.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum ExceptionCode {

    /**
     * 400 BAD_REQUEST: 잘못된 요청
     */
    FIELD_REQUIRED(BAD_REQUEST, "입력은 필수입니다."),

    /**
     * 400 BAD_REQUEST: 형식 에러
     */
    ID_INVALID(BAD_REQUEST, "올바른 형식의 아이디가 아닙니다."),
    PASSWORD_INVALID(BAD_REQUEST, "올바른 형식의 비밀번호가 아닙니다."),
    DATE_INVALID(BAD_REQUEST, "올바른 형식의 날짜가 아닙니다."),

//    /**
//     * 400 BAD_REQUEST: 길이 오류
//     */
//    ID_LENGTH_INVALID(BAD_REQUEST, "아이디는 2~8자만 가능합니다."),
//    PASSWORD_LENGTH_INVALID(BAD_REQUEST, "비밀번호는 8~15자만 가능합니다."),

    /**
     * 401 UNAUTHORIZED: 인증되지 않은 사용자
     */
    INVALID_TOKEN(UNAUTHORIZED, "손상된 토큰입니다."),
    EXPIRED_TOKEN(UNAUTHORIZED, "만료된 토큰입니다."),
    UNAUTHORIZED_USER(UNAUTHORIZED, "로그인이 필요합니다."),

    /**
     * 403 FORBIDDEN: 권한이 없는 사용자
     */
    FORBIDDEN_AUTHORIZATION(FORBIDDEN, "접근 권한이 없습니다."),

    /**
     * 404 NOT_FOUND: Resource 를 찾을 수 없음
     */
    USER_NOT_FOUND(NOT_FOUND, "일치하는 유저 정보가 없습니다."),
    SUBJECT_NOT_FOUND(NOT_FOUND, "일치하는 강의 정보가 없습니다."),
    AUDITOR_NOT_FOUND(NOT_FOUND, "일치하는 강의 수강생 정보가 없습니다."),
    ATTENDANCE_NOT_FOUND(NOT_FOUND, "일치하는 출석 정보가 없습니다."),
    TYPE_NOT_FOUND(NOT_FOUND, "일치하는 타입 정보가 없습니다."),


    /**
     * 409 CONFLICT: Resource 의 현재 상태와 충돌, 중복된 데이터
     */
    DUPLICATE_ID(CONFLICT, "이미 사용 중인 학번입니다."),

    /**
     * 500 SERVER_ERROR : 서버 에러
     */
    SERVER_ERROR(INTERNAL_SERVER_ERROR, "서버 에러가 발생했습니다."),
    FILE_NOT_FOUND(INTERNAL_SERVER_ERROR, "해당 파일을 찾지 못하였습니다."),;


    private final HttpStatus httpStatus;
    private final String message;

    ExceptionCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
