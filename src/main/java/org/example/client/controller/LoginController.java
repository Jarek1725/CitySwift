package org.example.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.client.util.NetworkClient;
import org.example.client.util.SceneSwitcher;
import org.example.client.util.UserSession;
import org.example.common.dto.ClientRequest;
import org.example.common.dto.UserCredential;
import org.example.common.dto.ServerResponse;
import org.example.common.dto.UserToken;

import java.io.IOException;

public class LoginController {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;

    public void loginButtonOn(ActionEvent event) {
        if (!usernameTextField.getText().isBlank() && !passwordTextField.getText().isBlank()) {
            loginMessageLabel.setText("Próba logowania");
            ServerResponse serverResponse = sendLoginRequest(usernameTextField.getText(), passwordTextField.getText());
            handleUserLoginResponse(serverResponse, event);
        } else {
            loginMessageLabel.setText("Podaj login oraz hasło");
        }
    }

    private void handleUserLoginResponse(ServerResponse serverResponse, ActionEvent event) {
        if(serverResponse.getResultCode()==200) {
            try {
                UserSession.setUserToken((UserToken) serverResponse.getData());
                SceneSwitcher.switchScene(event, "/view/dashboard/dashboardView.fxml");
            } catch (IOException e) {
                loginMessageLabel.setText("Błąd ładowania sceny");
            }
        } else {
            loginMessageLabel.setText("Niepoprawny login lub hasło");
        }
    }

    private ServerResponse sendLoginRequest(String username, String password) {
        NetworkClient networkClient = new NetworkClient();

        try {
            UserCredential credentials = new UserCredential(username, password);
            ClientRequest request = new ClientRequest("login", credentials);

            return networkClient.sendRequest(request);

        } catch (Exception e) {
            loginMessageLabel.setText("Błąd połączenia z serwerem");
        }

        return null;
    }
}
