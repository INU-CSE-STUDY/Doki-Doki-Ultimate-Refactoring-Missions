package com.capstone.webserver.subject.api;

import com.capstone.webserver.common.dto.PageRequestDTO;
import com.capstone.webserver.common.dto.PageResponseDTO;
import com.capstone.webserver.subject.dto.SubjectDto;
import com.capstone.webserver.subject.entity.Subject;
import com.capstone.webserver.subject.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/api/subjects")
    public ResponseEntity<List<SubjectDto.SubjectResponseDto>> getSubjects() {
        HttpStatus status = HttpStatus.OK;
        List<SubjectDto.SubjectResponseDto> subjects = subjectService.findAll();

        return new ResponseEntity<>(subjects, status);
    }

    @GetMapping("/api/subjects/colleges")
    public ResponseEntity<List<String>> getColleges() {
        HttpStatus status = HttpStatus.OK;
        List<String> colleges = subjectService.getColleges();

        return new ResponseEntity<>(colleges, status);
    }

    @GetMapping("/api/subjects/colleges/majors")
    public ResponseEntity<List<String>> getMajors(@RequestParam(value = "college") String college) {
        HttpStatus status = HttpStatus.OK;
        List<String> majors = subjectService.getMajors(college);

        return new ResponseEntity<>(majors, status);
    }

    @GetMapping("/api/subjects/majors")
    public ResponseEntity<PageResponseDTO<SubjectDto.SubjectResponseDto, Subject>> getSubjectsByMajor(@ModelAttribute PageRequestDTO dto, @RequestParam(value = "major") String major) {
        HttpStatus status = HttpStatus.OK;
        PageResponseDTO<SubjectDto.SubjectResponseDto, Subject> subjects = subjectService.getSubjectsByMajor(dto, major);

        return new ResponseEntity<>(subjects, status);
    }
}
