package com.example.baemin.term;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/terms")
public class TermController {

    private final TermService termService;

    @GetMapping("/list")
    public ResponseEntity<TermResponse> getTermsList() {
        return ResponseEntity.ok(termService.getTermsList());
    }
}