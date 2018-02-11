package com.flatmate.flatmateapp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	Set<Transaction> findByAccountUsername(String username);
} 