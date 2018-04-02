package com.flatmate.flatmateapi;

import com.flatmate.flatmatepersistence.Account;
import com.flatmate.flatmateregistry.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/accounts", produces = "application/json")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @ResponseBody
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Account getAccount(@PathVariable final Long userId) {
        return accountService.getAccountById(userId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Account createAccount(@RequestBody final Account account) {
        return accountService.createAccount(account);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public void deleteAccount(@PathVariable final Long userId) {
        accountService.deleteAccount(userId);
    }

}
