package com.example.baemin.term;

import com.example.baemin.common.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "terms")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(Term.TermId.class)
public class Term extends BaseTimeEntity {
    @Id
    private String termId;

    @Id
    private String version;

    private String name;
    private boolean isRequired;
    private String link;

    @Builder
    public Term(String termId, String name, boolean isRequired, String version, String link) {
        this.termId = termId;
        this.name = name;
        this.isRequired = isRequired;
        this.version = version;
        this.link = link;
    }

    // 복합키 클래스
    @Getter
    @NoArgsConstructor
    public static class TermId implements Serializable {
        private String termId;
        private String version;

        public TermId(String termId, String version) {
            this.termId = termId;
            this.version = version;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TermId termId1 = (TermId) o;
            return Objects.equals(termId, termId1.termId) &&
                    Objects.equals(version, termId1.version);
        }

        @Override
        public int hashCode() {
            return Objects.hash(termId, version);
        }
    }
}