package com.capstone.webserver.auditor.repository;

import com.capstone.webserver.auditor.entity.Auditor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditorRepository extends JpaRepository<Auditor, Long> {
}
