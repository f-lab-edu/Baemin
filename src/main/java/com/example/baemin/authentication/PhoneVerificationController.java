package com.example.baemin.authentication;

import com.example.baemin.authentication.dto.MessageResponse;
import com.example.baemin.authentication.dto.PhoneNumberRequest;
import com.example.baemin.authentication.dto.VerificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/phone")
@RequiredArgsConstructor
public class PhoneVerificationController {

    private final PhoneVerificationService phoneVerificationService;

    @PostMapping("/send")
    public ResponseEntity<MessageResponse> sendVerificationCode(@RequestBody PhoneNumberRequest request) {
        phoneVerificationService.sendVerificationCode(request.getPhoneNumber());
        return ResponseEntity.ok(new MessageResponse("인증번호가 발송되었습니다."));
    }

    @PostMapping("/verify")
    public ResponseEntity<MessageResponse> verifyCode(@RequestBody VerificationRequest request) {
        phoneVerificationService.verifyCode(request.getPhoneNumber(), request.getVerificationCode());
        return ResponseEntity.ok(new MessageResponse("인증이 완료되었습니다"));
    }
}