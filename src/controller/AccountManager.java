//package controller;
//
//
//
//import model.Account;
//import repository.AccountRepository;
//
//import java.util.List;
//
//public class AccountManager {
//    private final AccountRepository accountRepository = new AccountRepository();
//
//    public void addAccount(Account account) {
//        accountRepository.createAccount(account);
//    }
//
//    public List<Account> getAccounts() {
//        return accountRepository.getAllAccounts();
//    }
//
//    public Account getAccountById(int id) {
//        return accountRepository.getAccount(id);
//    }
//
//    public void updateAccount(Account account) {
//        accountRepository.updateAccount(account);
//    }
//
//    public void deleteAccount(int id) {
//        accountRepository.deleteAccount(id);
//    }
//}
