package com.example.baemin.term;

import com.example.baemin.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "term_versions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(TermVersion.TermVersionId.class)
public class TermVersion extends BaseTimeEntity {
    @Id
    @Column(name = "term_id")
    private String termId;

    @Id
    private String version;

    @Column(name = "is_required")
    private boolean isRequired;

    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id", insertable = false, updatable = false)
    private Term term;

    @Builder
    public TermVersion(String termId, String version, boolean isRequired, String link, Term term) {
        this.termId = termId;
        this.version = version;
        this.isRequired = isRequired;
        this.link = link;
        this.term = term;
    }

    // 복합키 클래스
    @Getter
    @NoArgsConstructor
    public static class TermVersionId implements Serializable {
        private String termId;
        private String version;

        public TermVersionId(String termId, String version) {
            this.termId = termId;
            this.version = version;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TermVersionId that = (TermVersionId) o;
            return Objects.equals(termId, that.termId) &&
                    Objects.equals(version, that.version);
        }

        @Override
        public int hashCode() {
            return Objects.hash(termId, version);
        }
    }
}