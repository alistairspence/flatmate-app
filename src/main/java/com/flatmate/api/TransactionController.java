package com.flatmate.api;

import com.flatmate.persistence.Transaction;
import com.flatmate.registry.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/transactions", produces = "application/json")
public class TransactionController {

	private final TransactionService transactionService;

	@Autowired
	public TransactionController(final TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@ResponseBody
	@RequestMapping(value = "/{transactionId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Transaction getTransactionById(@PathVariable final Long transactionId) { return transactionService.getTransactionById(transactionId); }

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Transaction> getAllTransactions() {
		return transactionService.getAllTransactions();
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Transaction createTransaction(@RequestBody final Transaction transaction) {
		return transactionService.createTransaction(transaction);
	}

	@ResponseBody
	@RequestMapping(value = "/{transactionId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public Transaction updateTransaction(@PathVariable final Long transactionId, @RequestBody final Transaction transaction) {
		return transactionService.updateTransaction(transactionId, transaction);
	}

	@RequestMapping(value = "/{transactionId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTransaction(@PathVariable final Long transactionId) { transactionService.deleteTransaction(transactionId); }

}