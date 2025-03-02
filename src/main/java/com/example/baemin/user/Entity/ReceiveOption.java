package com.example.baemin.user.Entity;

import com.example.baemin.common.BaseTimeEntity;
import com.example.baemin.term.TermVersion;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "receive_options")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReceiveOption extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "term_id", nullable = false)
    private String termId;

    @Column(nullable = false)
    private String version;

    @Column(name = "option_type", nullable = false)
    private String optionType;

    @Column(name = "is_required", nullable = false)
    private boolean isRequired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "term_id", referencedColumnName = "term_id", insertable = false, updatable = false),
            @JoinColumn(name = "version", referencedColumnName = "version", insertable = false, updatable = false)
    })
    private TermVersion termVersion;

    @Builder
    public ReceiveOption(String termId, String version, String optionType, boolean isRequired) {
        this.termId = termId;
        this.version = version;
        this.optionType = optionType;
        this.isRequired = isRequired;
    }
}