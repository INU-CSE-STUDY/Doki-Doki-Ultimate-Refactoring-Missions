package com.capstone.webserver.subject.api;

import com.capstone.webserver.subject.srtvice.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping("/api/subjects")
    public ResponseEntity<Void> addSubject() {
        HttpStatus status = HttpStatus.CREATED;
        subjectService.insert();
        return new ResponseEntity<>(status);
    }
}
