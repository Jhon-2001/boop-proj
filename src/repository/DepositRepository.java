package repository;

import model.Deposit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepositRepository {
    private Connection connection;

    public DepositRepository() {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }

    public void createDeposit(Deposit deposit) throws SQLException {
        String query = "INSERT INTO deposits (account_id, amount, start_date, end_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, deposit.getAccountId());
            preparedStatement.setDouble(2, deposit.getAmount());
            preparedStatement.setDate(3, deposit.getStartDate());
            preparedStatement.setDate(4, deposit.getEndDate());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteDeposit(int id) throws SQLException {
        String query = "DELETE FROM deposits WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public List<Deposit> getAllDeposits() throws SQLException {
        List<Deposit> deposits = new ArrayList<>();
        String query = "SELECT * FROM deposits";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Deposit deposit = new Deposit();
                deposit.setId(resultSet.getInt("id"));
                deposit.setAccountId(resultSet.getInt("account_id"));
                deposit.setAmount(resultSet.getDouble("amount"));
                deposit.setStartDate(resultSet.getDate("start_date"));
                deposit.setEndDate(resultSet.getDate("end_date"));
                deposits.add(deposit);
            }
        }
        return deposits;
    }
}
