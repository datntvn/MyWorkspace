package com.coffeeshop.app.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coffeeshop.app.model.*;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}