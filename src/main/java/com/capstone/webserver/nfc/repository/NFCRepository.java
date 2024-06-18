package com.capstone.webserver.nfc.repository;

import com.capstone.webserver.nfc.entity.NFC;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NFCRepository extends JpaRepository<NFC, Integer> {
}
