package com.flatmate.flatmateapi;

import com.flatmate.flatmatepersistence.Account;
import com.flatmate.flatmateregistry.AccountService;
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
@RequestMapping(value = "/api/accounts", produces = "application/json")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @ResponseBody
    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Account getAccountById(@PathVariable final Long accountId) {
        return accountService.getAccountById(accountId);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@RequestBody final Account account) {
        return accountService.createAccount(account);
    }

    @ResponseBody
    @RequestMapping(value = "/{accountId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Account updateAccount(@PathVariable final Long accountId, @RequestBody final Account account) {
        return accountService.updateAccount(accountId, account);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{accountId}", method = RequestMethod.DELETE)
    public void deleteAccount(@PathVariable final Long accountId) {
        accountService.deleteAccount(accountId);
    }

}
