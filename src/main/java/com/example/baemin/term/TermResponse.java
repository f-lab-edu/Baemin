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
        private String version;
        private String link;

        @Builder
        public TermDto(String termId, String name, boolean isRequired,
                       String version, String link) {
            this.termId = termId;
            this.name = name;
            this.isRequired = isRequired;
            this.version = version;
            this.link = link;
        }
    }
}