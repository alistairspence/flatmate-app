package com.flatmate.flatmateapp;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/transactions")
public class TransactionController {

	private final AccountService accountService;
	private final TransactionService transactionService;

	@Autowired
	public TransactionController(final AccountService accountService, TransactionService transactionService) {
		this.accountService = accountService;
		this.transactionService = transactionService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Transaction> getTransactions() {
		return transactionService.getTransactions();
	}

	@RequestMapping(method = RequestMethod.POST)
	public Transaction postTransaction(@RequestBody Transaction transaction) {
		return this.transactionService.saveTransaction(transaction);
	}

}