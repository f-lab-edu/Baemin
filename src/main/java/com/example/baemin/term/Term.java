package com.example.baemin.term;

import com.example.baemin.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "terms")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Term extends BaseTimeEntity {
    @Id
    @Column(name = "term_id")
    private String termId;

    private String name;

    @OneToMany(mappedBy = "term")
    private List<TermVersion> versions = new ArrayList<>();

    @Builder
    public Term(String termId, String name) {
        this.termId = termId;
        this.name = name;
    }
}