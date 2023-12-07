package org.example.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.common.dao.ClientRequest;
import org.example.common.dao.UserCredential;
import org.example.common.dto.ServerResponse;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LoginController {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    private final String serverAddress = "127.0.0.1"; // Server IP
    private final int serverPort = 8081; // Server Port

    public void loginButtonOn(ActionEvent on) {
        if (!usernameTextField.getText().isBlank() && !passwordTextField.getText().isBlank()) {
            loginMessageLabel.setText("Próba logowania");
            sendLoginRequest(usernameTextField.getText(), passwordTextField.getText());
        } else {
            loginMessageLabel.setText("Podaj login oraz hasło");
        }
    }

    private void sendLoginRequest(String username, String password) {
        try (Socket socket = new Socket(serverAddress, serverPort);
             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {

            UserCredential credentials = new UserCredential(username, password);
            ClientRequest request = new ClientRequest("login", credentials);

            outputStream.writeObject(request);

            Object response = inputStream.readObject();
            handleServerResponse(response);

        } catch (Exception e) {
            e.printStackTrace();
            loginMessageLabel.setText("Błąd połączenia z serwerem");
        }
    }

    private void handleServerResponse(Object response) {
        if (response instanceof ServerResponse) {
            ServerResponse serverResponse = (ServerResponse) response;
            System.out.println(serverResponse.getResultMessage());
        }
    }
}
