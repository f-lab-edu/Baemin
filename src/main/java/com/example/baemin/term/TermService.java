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
    private final TermVersionRepository termVersionRepository;

    public TermResponse getTermsList() {
        List<TermVersion> latestVersions = termVersionRepository.findAllLatestVersions();
        return TermResponse.builder()
                .terms(latestVersions.stream()
                        .map(this::convertToDto)
                        .collect(Collectors.toList()))
                .build();
    }

    private TermResponse.TermDto convertToDto(TermVersion termVersion) {
        return TermResponse.TermDto.builder()
                .termId(termVersion.getTermId())
                .name(termVersion.getTerm().getName())
                .isRequired(termVersion.isRequired())
                .version(termVersion.getVersion())
                .link(termVersion.getLink())
                .build();
    }
}