package com.flatmate.flatmateapi;

import com.flatmate.flatmatepersistence.Transaction;
import com.flatmate.flatmateregistry.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	private final TransactionService transactionService;

	@Autowired
	public TransactionController(final TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@ResponseBody
	@RequestMapping(value = "/{transactionId}", method = RequestMethod.GET)
	public Transaction getTransaction(@PathVariable final Long transactionId) { return transactionService.getTransactionById(transactionId); }

	@RequestMapping(method = RequestMethod.GET)
	public List<Transaction> getTransactions() {
		return transactionService.getTransactions();
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Transaction postTransaction(@RequestBody final Transaction transaction) {
		return transactionService.createTransaction(transaction);
	}

	@RequestMapping(value = "/{transactionId}", method = RequestMethod.DELETE)
	public void deleteTransaction(@PathVariable final Long transactionId) { transactionService.deleteTransaction(transactionId); }

}