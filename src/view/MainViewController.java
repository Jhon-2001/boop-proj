package view;

import controller.AccountController;
import controller.ClientController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Account;
import model.Client;

public class MainViewController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TableView<Client> clientsTable;
    @FXML
    private TableColumn<Client, String> nameColumn;
    @FXML
    private TableColumn<Client, String> emailColumn;

    @FXML
    private TextField fromAccountField;
    @FXML
    private TextField toAccountField;
    @FXML
    private TextField amountField;
    @FXML
    private TextField addAccountIdField;
    @FXML
    private TextField addAmountField;

    @FXML
    private TableView<Account> accountsTable;
    @FXML
    private TableColumn<Account, Integer> accountIdColumn;
    @FXML
    private TableColumn<Account, Double> balanceColumn;
    @FXML
    private TableColumn<Account, String> currencyColumn;

    private ClientController clientController = new ClientController();
    private AccountController accountController = new AccountController();

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        accountIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
        currencyColumn.setCellValueFactory(new PropertyValueFactory<>("currency"));

        listClients();
        listAccounts();
    }

    @FXML
    private void addClient() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        Client client = new Client();
        client.setName(name);
        client.setEmail(email);
        client.setPassword(password);
        clientController.addClient(client);

        System.out.println("Client added: " + client);
        listClients();
    }

    @FXML
    private void listClients() {
        ObservableList<Client> data = FXCollections.observableArrayList(clientController.getClients());
        clientsTable.setItems(data);
    }

    @FXML
    private void listAccounts() {
        ObservableList<Account> data = FXCollections.observableArrayList(accountController.getAllAccounts());
        accountsTable.setItems(data);
    }

    @FXML
    private void transferMoney() {
        int fromAccountId = Integer.parseInt(fromAccountField.getText());
        int toAccountId = Integer.parseInt(toAccountField.getText());
        double amount = Double.parseDouble(amountField.getText());

        accountController.transferMoney(fromAccountId, toAccountId, amount);

        System.out.println("Money transferred from account " + fromAccountId + " to account " + toAccountId);
        listAccounts();
    }

    @FXML
    private void addMoneyToAccount() {
        int accountId = Integer.parseInt(addAccountIdField.getText());
        double amount = Double.parseDouble(addAmountField.getText());

        accountController.addMoneyToAccount(accountId, amount);

        System.out.println("Money added to account " + accountId);
        listAccounts();
    }
}
