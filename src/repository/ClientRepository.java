package repository;

import model.Client;
import model.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {
    private Connection connection;

    public ClientRepository() {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }

    public void createClient(Client client) throws SQLException {
        String query = "INSERT INTO clients (name, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getEmail());
            preparedStatement.setString(3, client.getPassword());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int clientId = generatedKeys.getInt(1);
                    createAccountForClient(clientId);
                }
            }
        }
    }

    private void createAccountForClient(int clientId) throws SQLException {
        String query = "INSERT INTO accounts (client_id, balance, currency) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, clientId);
            preparedStatement.setDouble(2, 0.0);
            preparedStatement.setString(3, "EUR");
            preparedStatement.executeUpdate();
        }
    }

    public List<Client> getAllClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM clients";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setEmail(resultSet.getString("email"));
                client.setPassword(resultSet.getString("password"));
                clients.add(client);
            }
        }
        return clients;
    }

    public Client getClientById(int clientId) throws SQLException {
        String query = "SELECT * FROM clients WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, clientId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Client client = new Client();
                    client.setId(resultSet.getInt("id"));
                    client.setName(resultSet.getString("name"));
                    client.setEmail(resultSet.getString("email"));
                    client.setPassword(resultSet.getString("password"));
                    return client;
                }
            }
        }
        return null;
    }
}
