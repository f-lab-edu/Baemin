package com.example.baemin.user.Entity;

import com.example.baemin.common.BaseTimeEntity;
import com.example.baemin.term.TermVersion;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_terms_agreements")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTermsAgreement extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "term_id", nullable = false)
    private String termId;

    @Column(nullable = false)
    private String version;

    @Column(name = "is_agreed", nullable = false)
    private boolean isAgreed;

    @Column(name = "agreed_at")
    private LocalDateTime agreedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "term_id", referencedColumnName = "term_id", insertable = false, updatable = false),
            @JoinColumn(name = "version", referencedColumnName = "version", insertable = false, updatable = false)
    })
    private TermVersion termVersion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @Builder
    public UserTermsAgreement(Long userId, String termId, String version, boolean isAgreed) {
        this.userId = userId;
        this.termId = termId;
        this.version = version;
        this.isAgreed = isAgreed;
        if (isAgreed) {
            this.agreedAt = LocalDateTime.now();
        }
    }
}