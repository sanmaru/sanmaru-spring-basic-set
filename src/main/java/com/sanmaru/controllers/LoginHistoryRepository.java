package com.sanmaru.controllers;

import com.sanmaru.entities.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory,Long> {
}
