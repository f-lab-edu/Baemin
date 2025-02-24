package com.example.baemin.authentication.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VerificationRequest {
    private String phoneNumber;
    private String verificationCode;
}