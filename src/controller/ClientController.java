package controller;

import model.Client;
import service.ClientService;

import java.util.List;

public class ClientController {
    private ClientService clientService = new ClientService();

    public void addClient(Client client) {
        clientService.addClient(client);
    }

    public List<Client> getClients() {
        return clientService.getClients();
    }

    public Client getClientById(int clientId) {
        return clientService.getClientById(clientId);
    }
}
