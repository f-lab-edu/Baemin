package com.example.baemin.term;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TermResponse {
    private List<TermDto> terms;

    @Builder
    public TermResponse(List<TermDto> terms) {
        this.terms = terms;
    }

    @Getter
    @NoArgsConstructor
    public static class TermDto {
        private String termId;
        private String name;
        private boolean isRequired;
        private ReceiveOptionsDto receiveOptions;
        private String link;

        @Builder
        public TermDto(String termId, String name, boolean isRequired,
                       ReceiveOptionsDto receiveOptions, String link) {
            this.termId = termId;
            this.name = name;
            this.isRequired = isRequired;
            this.receiveOptions = receiveOptions;
            this.link = link;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ReceiveOptionsDto {
        private ReceiveOptionDto sms;
        private ReceiveOptionDto email;
        private ReceiveOptionDto phone;

        @Builder
        public ReceiveOptionsDto(ReceiveOptionDto sms, ReceiveOptionDto email,
                                 ReceiveOptionDto phone) {
            this.sms = sms;
            this.email = email;
            this.phone = phone;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ReceiveOptionDto {
        private boolean isAgreed;
        private boolean isRequired;

        @Builder
        public ReceiveOptionDto(boolean isAgreed, boolean isRequired) {
            this.isAgreed = isAgreed;
            this.isRequired = isRequired;
        }
    }
}