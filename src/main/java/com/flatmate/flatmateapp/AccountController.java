package com.flatmate.flatmateapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Account getAccount(@PathVariable final Long userId) {
        return accountService.getAccountById(userId);
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public List<Account> getAccounts() {
//        return accountService.getAccounts();
//    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    // TODO: fix this
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public void deleteAccount(@RequestParam final Long userId) {
        accountService.deleteAccount(userId);
    }

}
