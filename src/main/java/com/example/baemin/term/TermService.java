package com.example.baemin.term;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TermService {
    private final TermRepository termRepository;

    public TermResponse getTermsList() {
        List<Term> terms = termRepository.findAll();
        return TermResponse.builder()
                .terms(terms.stream()
                        .map(this::convertToDto)
                        .collect(Collectors.toList()))
                .build();
    }

    private TermResponse.TermDto convertToDto(Term term) {
        return TermResponse.TermDto.builder()
                .termId(term.getTermId())
                .name(term.getName())
                .isRequired(term.isRequired())
                .link(term.getLink())
                .build();
    }
}