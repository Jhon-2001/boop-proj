package service;

import model.Account;
import repository.AccountRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private AccountRepository accountRepository = new AccountRepository();

    public void createAccount(Account account) {
        try {
            accountRepository.createAccount(account);
        } catch (SQLException e) {
            System.err.println("Failed to create account: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Account> getAllAccounts() {
        try {
            return accountRepository.getAllAccounts();
        } catch (SQLException e) {
            System.err.println("Failed to get accounts: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void transferMoney(int fromAccountId, int toAccountId, double amount) {
        try {
            accountRepository.transferMoney(fromAccountId, toAccountId, amount);
        } catch (SQLException e) {
            System.err.println("Failed to transfer money: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addMoneyToAccount(int accountId, double amount) {
        try {
            accountRepository.addMoneyToAccount(accountId, amount);
        } catch (SQLException e) {
            System.err.println("Failed to add money to account: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void convertCurrency(int accountId, double amount, String fromCurrency, String toCurrency) {
        // Implement conversion logic here
    }
}
