package com.example.baemin.term;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "terms")  // 테이블명 지정
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Term {
    @Id
    private String termId;

    private String name;

    @Column(name = "is_required")
    private boolean isRequired;

    private String link;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "termId")
    private List<ReceiveOption> receiveOptions;

    @Builder
    public Term(String termId, String name, boolean isRequired, String link) {
        this.termId = termId;
        this.name = name;
        this.isRequired = isRequired;
        this.link = link;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}