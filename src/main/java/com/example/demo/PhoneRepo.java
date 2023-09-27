package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PhoneRepo extends JpaRepository<PhoneNumber, Integer> {
    @Modifying
    @Query(value = """
      DELETE FROM phone_number
      WHERE users_id = :userId""", nativeQuery = true)
    void deletedOldPhone(int userId);
}
