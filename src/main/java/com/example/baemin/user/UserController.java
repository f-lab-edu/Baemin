package com.example.baemin.user;

import com.example.baemin.common.response.ApiResponse;
import com.example.baemin.common.response.ResponseCode;
import com.example.baemin.user.dto.SignupDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignupDTO.Response>> signup(
            @Valid @RequestBody SignupDTO.Request request) {
        SignupDTO.Response response = userService.signup(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(ResponseCode.SIGNUP_SUCCESS, response));
    }
}