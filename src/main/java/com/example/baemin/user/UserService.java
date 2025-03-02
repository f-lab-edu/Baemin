package com.example.baemin.user;

import com.example.baemin.authentication.PhoneVerificationService;
import com.example.baemin.common.response.CustomException;
import com.example.baemin.common.response.ResponseCode;
import com.example.baemin.term.TermVersion;
import com.example.baemin.term.TermVersionRepository;
import com.example.baemin.user.Entity.User;
import com.example.baemin.user.Entity.UserReceiveOption;
import com.example.baemin.user.Entity.UserTermsAgreement;
import com.example.baemin.user.dto.SignupDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserTermsAgreementRepository userTermsAgreementRepository;
    private final UserReceiveOptionRepository userReceiveOptionRepository;
    private final TermVersionRepository termVersionRepository;
    private final PhoneVerificationService phoneVerificationService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignupDTO.Response signup(SignupDTO.Request request) {
        // 사용자 존재 여부 확인
        validateUserInfo(request);

        // 전화번호 인증 확인
        try {
            phoneVerificationService.verifyCode(request.getPhoneNumber(), request.getVerificationCode());
        } catch (CustomException e) {
            if (e.getResponseCode() != ResponseCode.VERIFICATION_SUCCESS) {
                throw e;
            }
        }

        // 필수 약관 동의 확인
        validateRequiredTerms(request.getTermAgreements());

        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(request.getPassword());

        // 사용자 저장
        User user = User.builder()
                .userId(request.getUserId())
                .password(encryptedPassword)
                .email(request.getEmail())
                .name(request.getName())
                .birthDate(request.getBirthDate())
                .phoneNumber(request.getPhoneNumber())
                .build();

        User savedUser = userRepository.save(user);

        // 약관 동의 정보 저장
        saveTermAgreements(savedUser.getId(), request.getTermAgreements());

        // 수신 옵션 동의 정보 저장
        if (request.getReceiveOptionAgreements() != null && !request.getReceiveOptionAgreements().isEmpty()) {
            saveReceiveOptionAgreements(savedUser.getId(), request.getReceiveOptionAgreements());
        }

        return SignupDTO.Response.of(
                savedUser.getId(),
                savedUser.getUserId(),
                savedUser.getEmail(),
                savedUser.getName(),
                savedUser.getBirthDate(),
                savedUser.getPhoneNumber()
        );
    }

    private void validateUserInfo(SignupDTO.Request request) {
        if (userRepository.existsByUserId(request.getUserId())) {
            throw new CustomException(ResponseCode.DUPLICATE_USER_ID);
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(ResponseCode.DUPLICATE_EMAIL);
        }

        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new CustomException(ResponseCode.DUPLICATE_PHONE_NUMBER);
        }
    }

    private void validateRequiredTerms(List<SignupDTO.TermAgreementInfo> termAgreements) {
        // 최신 버전의 필수 약관 목록 조회
        List<TermVersion> latestVersions = termVersionRepository.findAllLatestVersions();
        List<TermVersion> requiredTerms = latestVersions.stream()
                .filter(TermVersion::isRequired)
                .toList();

        // 필수 약관에 대한 동의 여부 확인
        for (var requiredTerm : requiredTerms) {
            boolean agreed = termAgreements.stream()
                    .anyMatch(agreement ->
                            agreement.getTermId().equals(requiredTerm.getTermId()) &&
                                    agreement.getVersion().equals(requiredTerm.getVersion()) &&
                                    agreement.isAgreed());

            if (!agreed) {
                throw new CustomException(ResponseCode.REQUIRED_TERM_AGREEMENT);
            }
        }
    }

    private void saveTermAgreements(Long userId, List<SignupDTO.TermAgreementInfo> termAgreements) {
        termAgreements.forEach(agreement -> {
            UserTermsAgreement userTermsAgreement = UserTermsAgreement.builder()
                    .userId(userId)
                    .termId(agreement.getTermId())
                    .version(agreement.getVersion())
                    .isAgreed(agreement.isAgreed())
                    .build();

            userTermsAgreementRepository.save(userTermsAgreement);
        });
    }

    private void saveReceiveOptionAgreements(Long userId, List<SignupDTO.ReceiveOptionAgreementInfo> optionAgreements) {
        optionAgreements.forEach(agreement -> {
            UserReceiveOption userReceiveOption = UserReceiveOption.builder()
                    .userId(userId)
                    .receiveOptionId(agreement.getReceiveOptionId())
                    .isAgreed(agreement.isAgreed())
                    .build();

            userReceiveOptionRepository.save(userReceiveOption);
        });
    }
}