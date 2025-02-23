package com.example.baemin.term;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "terms")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Term {
    @Id
    private String termId;
    private String name;
    private boolean isRequired;
    private String version;
    private String link;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp  // 자동 업데이트를 위해 추가
    private LocalDateTime updatedAt;
    @Builder
    public Term(String termId, String name, boolean isRequired, String version, String link) {
        this.termId = termId;
        this.name = name;
        this.isRequired = isRequired;
        this.version = version;
        this.link = link;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}