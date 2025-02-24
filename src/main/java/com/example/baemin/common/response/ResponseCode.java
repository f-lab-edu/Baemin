package com.example.baemin.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    // 성공 코드
    SUCCESS("200", "성공적으로 처리되었습니다."),

    // 인증 관련 코드
    VERIFICATION_CODE_SENT("200", "인증번호가 발송되었습니다."),
    VERIFICATION_SUCCESS("200", "인증이 완료되었습니다."),

    // 오류 코드
    INVALID_INPUT("400", "잘못된 입력값입니다."),
    EXPIRED_CODE("400", "만료된 인증번호입니다."),
    INVALID_CODE("400", "잘못된 인증번호입니다."),
    INVALID_PHONE_NUMBER("400", "잘못된 전화번호 형식입니다."),
    SERVER_ERROR("500", "서버 오류가 발생했습니다.");

    private final String code;
    private final String message;
}