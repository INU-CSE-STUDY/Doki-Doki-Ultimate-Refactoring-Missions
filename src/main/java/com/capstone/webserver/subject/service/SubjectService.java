package com.capstone.webserver.subject.service;

import com.capstone.webserver.common.dto.PageRequestDTO;
import com.capstone.webserver.common.dto.PageResponseDTO;
import com.capstone.webserver.common.response.exception.CustomException;
import com.capstone.webserver.subject.dto.SubjectDto;
import com.capstone.webserver.subject.entity.College;
import com.capstone.webserver.subject.entity.Major;
import com.capstone.webserver.subject.entity.Subject;
import com.capstone.webserver.subject.repository.SubjectQueryDSLRepository;
import com.capstone.webserver.subject.repository.SubjectRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.capstone.webserver.common.response.exception.ExceptionCode.FILE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectQueryDSLRepository subjectQueryDSLRepository;

    public void insert() {
        try {
            ClassPathResource resource = new ClassPathResource("json/subject.json");
            Path path = Paths.get(resource.getURI());
            List<String> content = Files.readAllLines(path);

            StringJoiner sj = new StringJoiner("\n");
            content.forEach(sj::add);

            ObjectMapper obm = new ObjectMapper();
            List<SubjectDto.SubjectMapperDto> dtos = List.of(obm.readValue(sj.toString(), SubjectDto.SubjectMapperDto[].class));

            List<Subject> subjects = dtos.stream()
                    .map(this::toEntity)
                    .collect(Collectors.toList());

            subjectRepository.saveAll(subjects);
        } catch (IOException e) {
            throw new CustomException(FILE_NOT_FOUND);
        }
    }

    public List<SubjectDto.SubjectResponseDto> findAll() {
        List<Subject> subjects = subjectQueryDSLRepository.findAll();
        return subjects.stream()
                .map(this::toSubjectDto)
                .collect(Collectors.toList());
    }


    public List<String> getColleges() {
        return Arrays.stream(College.values())
                .map(College::getKrName)
                .toList();
    }

    public List<String> getMajors(String college) {
        return Major.getByCollege(college);
    }

    public PageResponseDTO<SubjectDto.SubjectResponseDto, Subject> getSubjectsByMajor(PageRequestDTO dto, String majorName) {
        Major major = Major.getByName(majorName);
        Page<Subject> subjects = subjectQueryDSLRepository.findSubjectsByMajor(PageRequest.of(dto.getPage(), dto.getSize()), major);
        Function<Subject, SubjectDto.SubjectResponseDto> mapper = (this::toSubjectDto);

        return new PageResponseDTO<>(subjects, mapper);
    }

    private Subject toEntity(SubjectDto.SubjectMapperDto dto) {
        return Subject.builder()
                .idSubject(dto.getIdSubject())
                .semesterSubject(dto.getSemesterSubject())
                .creditSubject(dto.getCreditSubject())
                .majorSubject(Major.getByName(dto.getMajorSubject()))
                .nameSubject(dto.getNameSubject())
                .univSubject(College.getByName(dto.getUnivSubject()))
                .typeSubject(dto.getTypeSubject())
                .yearSubject(dto.getYearSubject())
                .timeSubject(dto.getTimeSubject())
                .profSubject(dto.getProfSubject())
                .build();
    }

    private SubjectDto.SubjectResponseDto toSubjectDto(Subject subject) {
        return SubjectDto.SubjectResponseDto.builder()
                .creditSubject(subject.getCreditSubject())
                .idSubject(subject.getIdSubject())
                .majorSubject(subject.getMajorSubject().getKrName())
                .nameSubject(subject.getNameSubject())
                .profSubject(subject.getProfSubject())
                .timeSubject(subject.getTimeSubject())
                .typeSubject(subject.getTypeSubject())
                .univSubject(subject.getUnivSubject().getKrName())
                .build();
    }
}
