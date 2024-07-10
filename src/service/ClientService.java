package service;

import model.Client;
import repository.ClientRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
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
            return new ArrayList<>();
        }
    }

    public Client getClientById(int clientId) {
        try {
            return clientRepository.getClientById(clientId);
        } catch (SQLException e) {
            System.err.println("Failed to get client: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
