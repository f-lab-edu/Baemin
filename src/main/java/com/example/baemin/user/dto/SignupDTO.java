package com.example.baemin.user.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class SignupDTO {

    @Getter
    @NoArgsConstructor
    public static class Request {
        @NotBlank(message = "아이디는 필수입니다.")
        @Size(min = 4, max = 20, message = "아이디는 4자 이상 20자 이하로 입력해주세요.")
        private String userId;

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
                message = "비밀번호는 8자 이상, 영문자, 숫자, 특수문자를 포함해야 합니다.")
        private String password;

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "유효한 이메일 형식이어야 합니다.")
        private String email;

        @NotBlank(message = "이름은 필수입니다.")
        private String name;

        @NotNull(message = "생년월일은 필수입니다.")
        @Past(message = "생년월일은 과거 날짜여야 합니다.")
        private LocalDate birthDate;

        @NotBlank(message = "전화번호는 필수입니다.")
        @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "유효한 전화번호 형식이어야 합니다.")
        private String phoneNumber;

        @NotBlank(message = "인증 코드는 필수입니다.")
        private String verificationCode;

        @NotEmpty(message = "약관 동의 정보는 필수입니다.")
        private List<TermAgreementInfo> termAgreements;

        private List<ReceiveOptionAgreementInfo> receiveOptionAgreements;

        @Builder
        public Request(String userId, String password, String email, String name,
                       LocalDate birthDate, String phoneNumber, String verificationCode,
                       List<TermAgreementInfo> termAgreements,
                       List<ReceiveOptionAgreementInfo> receiveOptionAgreements) {
            this.userId = userId;
            this.password = password;
            this.email = email;
            this.name = name;
            this.birthDate = birthDate;
            this.phoneNumber = phoneNumber;
            this.verificationCode = verificationCode;
            this.termAgreements = termAgreements;
            this.receiveOptionAgreements = receiveOptionAgreements;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class TermAgreementInfo {
        @NotBlank(message = "약관 ID는 필수입니다.")
        private String termId;

        @NotBlank(message = "약관 버전은 필수입니다.")
        private String version;

        private boolean isAgreed;

        @Builder
        public TermAgreementInfo(String termId, String version, boolean isAgreed) {
            this.termId = termId;
            this.version = version;
            this.isAgreed = isAgreed;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ReceiveOptionAgreementInfo {
        private Long receiveOptionId;
        private boolean isAgreed;

        @Builder
        public ReceiveOptionAgreementInfo(Long receiveOptionId, boolean isAgreed) {
            this.receiveOptionId = receiveOptionId;
            this.isAgreed = isAgreed;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String userId;
        private String email;
        private String name;
        private LocalDate birthDate;
        private String phoneNumber;
        private String message;

        @Builder
        public Response(Long id, String userId, String email, String name,
                        LocalDate birthDate, String phoneNumber, String message) {
            this.id = id;
            this.userId = userId;
            this.email = email;
            this.name = name;
            this.birthDate = birthDate;
            this.phoneNumber = phoneNumber;
            this.message = message;
        }

        public static Response of(Long id, String userId, String email, String name,
                                  LocalDate birthDate, String phoneNumber) {
            return Response.builder()
                    .id(id)
                    .userId(userId)
                    .email(email)
                    .name(name)
                    .birthDate(birthDate)
                    .phoneNumber(phoneNumber)
                    .message("회원가입이 완료되었습니다.")
                    .build();
        }
    }
}