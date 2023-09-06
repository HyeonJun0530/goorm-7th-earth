package com.example.repository;

import com.example.entity.selling.Selling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellingRepository extends JpaRepository<Selling, Long> {
}