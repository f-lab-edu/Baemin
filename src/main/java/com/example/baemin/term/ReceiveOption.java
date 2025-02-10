package com.example.baemin.term;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "receive_options")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReceiveOption {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "term_id")
    private String termId;

    @Column(name = "option_type")
    private String optionType;

    @Column(name = "is_required")
    private boolean isRequired;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public ReceiveOption(String termId, String optionType, boolean isRequired) {
        this.termId = termId;
        this.optionType = optionType;
        this.isRequired = isRequired;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}