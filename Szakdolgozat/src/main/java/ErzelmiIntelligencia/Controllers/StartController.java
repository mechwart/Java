package ErzelmiIntelligencia.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StartController implements Initializable {

    public Button toLogin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void tologinpage(MouseEvent event) throws IOException {
        toLogin.getScene().getWindow().hide();
        Stage login = new Stage();
        login.setTitle("Érzelmi intelligencia fejlesztés");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXMLFiles/loginpage.fxml")));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.show();
    }

}
