package controller;

import model.Account;
import service.AccountService;

import java.util.List;

public class AccountController {
    private AccountService accountService = new AccountService();

    public void createAccount(Account account) {
        accountService.createAccount(account);
    }

    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    public void transferMoney(int fromAccountId, int toAccountId, double amount) {
        accountService.transferMoney(fromAccountId, toAccountId, amount);
    }

    public void addMoneyToAccount(int accountId, double amount) {
        accountService.addMoneyToAccount(accountId, amount);
    }

    public void convertCurrency(int accountId, double amount, String fromCurrency, String toCurrency) {
        accountService.convertCurrency(accountId, amount, fromCurrency, toCurrency);
    }
}
