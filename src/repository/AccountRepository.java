package repository;

import model.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private Connection connection;

    public AccountRepository() {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }

    public void createAccount(Account account) throws SQLException {
        String query = "INSERT INTO accounts (client_id, balance, currency) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, account.getClientId());
            preparedStatement.setDouble(2, account.getBalance());
            preparedStatement.setString(3, account.getCurrency());
            preparedStatement.executeUpdate();
        }
    }

    public List<Account> getAllAccounts() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM accounts";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setClientId(resultSet.getInt("client_id"));
                account.setBalance(resultSet.getDouble("balance"));
                account.setCurrency(resultSet.getString("currency"));
                accounts.add(account);
            }
        }
        return accounts;
    }

    public Account getAccountById(int id) throws SQLException {
        String query = "SELECT * FROM accounts WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Account account = new Account();
                    account.setId(resultSet.getInt("id"));
                    account.setClientId(resultSet.getInt("client_id"));
                    account.setBalance(resultSet.getDouble("balance"));
                    account.setCurrency(resultSet.getString("currency"));
                    return account;
                }
            }
        }
        return null;
    }

    public void updateAccount(Account account) throws SQLException {
        String query = "UPDATE accounts SET balance = ?, currency = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setString(2, account.getCurrency());
            preparedStatement.setInt(3, account.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteAccount(int id) throws SQLException {
        String query = "DELETE FROM accounts WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public void addMoneyToAccount(int accountId, double amount) throws SQLException {
        Account account = getAccountById(accountId);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            updateAccount(account);
        }
    }

    public void transferMoney(int fromAccountId, int toAccountId, double amount) throws SQLException {
        connection.setAutoCommit(false);
        try {
            Account fromAccount = getAccountById(fromAccountId);
            Account toAccount = getAccountById(toAccountId);

            if (fromAccount != null && toAccount != null && fromAccount.getBalance() >= amount) {
                fromAccount.setBalance(fromAccount.getBalance() - amount);
                toAccount.setBalance(toAccount.getBalance() + amount);

                updateAccount(fromAccount);
                updateAccount(toAccount);

                String insertTransactionQuery = "INSERT INTO transactions (account_id, type, amount) VALUES (?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertTransactionQuery)) {
                    preparedStatement.setInt(1, fromAccountId);
                    preparedStatement.setString(2, "TRANSFER OUT");
                    preparedStatement.setDouble(3, amount);
                    preparedStatement.executeUpdate();

                    preparedStatement.setInt(1, toAccountId);
                    preparedStatement.setString(2, "TRANSFER IN");
                    preparedStatement.setDouble(3, amount);
                    preparedStatement.executeUpdate();
                }

                connection.commit();
            } else {
                connection.rollback();
            }
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
