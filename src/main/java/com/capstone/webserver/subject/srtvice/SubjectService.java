package com.capstone.webserver.subject.srtvice;

import com.capstone.webserver.common.response.exception.CustomException;
import com.capstone.webserver.subject.dto.SubjectDto;
import com.capstone.webserver.subject.entity.Subject;
import com.capstone.webserver.subject.repository.SubjectRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.capstone.webserver.common.response.exception.ExceptionCode.FILE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public void insert() {
        try {
            ClassPathResource resource = new ClassPathResource("json/subject.json");
            Path path = Paths.get(resource.getURI());
            List<String> content = Files.readAllLines(path);

            StringJoiner sj = new StringJoiner("\n");
            content.forEach(sj::add);

            ObjectMapper obm = new ObjectMapper();
            List<Subject> subjects = List.of(obm.readValue(sj.toString(), Subject[].class));

            subjectRepository.saveAll(subjects);
        } catch (IOException e) {
            throw new CustomException(FILE_NOT_FOUND);
        }
    }

    public List<SubjectDto.SubjectResponseDto> findAll() {
        List<Subject> subjects = subjectRepository.findAll();
        return subjects.stream()
                .map(this::toSubjectDto)
                .collect(Collectors.toList());
    }

    private SubjectDto.SubjectResponseDto toSubjectDto(Subject subject) {
        return SubjectDto.SubjectResponseDto.builder()
                .creditSubject(subject.getCreditSubject())
                .idSubject(subject.getIdSubject())
                .majorSubject(subject.getMajorSubject())
                .nameSubject(subject.getNameSubject())
                .profSubject(subject.getProfSubject())
                .timeSubject(subject.getTimeSubject())
                .typeSubject(subject.getTypeSubject())
                .univSubject(subject.getUnivSubject())
                .build();
    }
}
