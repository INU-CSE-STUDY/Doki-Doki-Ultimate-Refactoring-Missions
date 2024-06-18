package com.capstone.webserver.subject.repository;

import com.capstone.webserver.subject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
