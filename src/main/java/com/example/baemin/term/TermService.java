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
                .receiveOptions(convertToReceiveOptionsDto(term.getReceiveOptions()))
                .build();
    }

    private TermResponse.ReceiveOptionsDto convertToReceiveOptionsDto(List<ReceiveOption> receiveOptions) {
        TermResponse.ReceiveOptionDto sms = null;
        TermResponse.ReceiveOptionDto email = null;
        TermResponse.ReceiveOptionDto phone = null;

        for (ReceiveOption option : receiveOptions) {
            TermResponse.ReceiveOptionDto optionDto = TermResponse.ReceiveOptionDto.builder()
                    .isRequired(option.isRequired())
                    .isAgreed(false) // 기본값으로 false 설정
                    .build();

            switch (option.getOptionType().toLowerCase()) {
                case "sms":
                    sms = optionDto;
                    break;
                case "email":
                    email = optionDto;
                    break;
                case "phone":
                    phone = optionDto;
                    break;
            }
        }

        return TermResponse.ReceiveOptionsDto.builder()
                .sms(sms)
                .email(email)
                .phone(phone)
                .build();
    }
}