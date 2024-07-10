package controller;

import model.Client;
import repository.ClientRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList; // Add this import

public class ClientManager {
    private ClientRepository clientRepository = new ClientRepository();

    public void addClient(Client client) {
        try {
            clientRepository.createClient(client);
        } catch (SQLException e) {
            System.err.println("Failed to add client: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Client> getClients() {
        try {
            return clientRepository.getAllClients();
        } catch (SQLException e) {
            System.err.println("Failed to get clients: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>(); // Return an empty list if an error occurs
        }
    }
}
