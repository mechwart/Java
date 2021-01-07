package ErzelmiIntelligencia.Controllers;

import ErzelmiIntelligencia.connectivity.ConnectionClass;
import ErzelmiIntelligencia.passwordEncrypt.PasswordUtils;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Objects;

public class SignupController {
    public Button signupButton;
    public TextField usernameF;
    public PasswordField pwF;
    public Label registlabel;
    public Button signIn;

    Statement statement;

    public void signup(MouseEvent mouseEvent) throws IOException {
        if(usernameF.getText().equals("") || pwF.getText().equals("")) {
            registlabel.setText("Regisztráció sikertelen! Próbálja újra!");
            registlabel.setVisible(true);
            registlabel.setTextFill(Paint.valueOf("#FF0000"));
            throw new IOException();
        }

        try{
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            String salt = PasswordUtils.getSalt(30);
            String securedPw = PasswordUtils.generateSecurePassword(pwF.getText(), salt);
            String insertquery = "INSERT INTO `user`(`name`, `password`, `salt`) VALUES ('"+usernameF.getText()+"', '"+securedPw+"', '"+salt+"')";
            statement = connection.createStatement();
            statement.executeUpdate(insertquery);
            registlabel.setText("Sikeres regisztráció!");
            registlabel.setAlignment(Pos.CENTER);
            registlabel.setVisible(true);
            registlabel.setTextFill(Paint.valueOf("#7CFC00"));
            signupButton.setVisible(false);
            signIn.setVisible(true);
            
        } catch(Exception e){
            registlabel.setText("Regisztráció sikertelen! Próbálja újra!");
            registlabel.setVisible(true);
            registlabel.setAlignment(Pos.CENTER);
            registlabel.setTextFill(Paint.valueOf("#FF0000"));
        }


    }

    public void backSignIn(MouseEvent mouseEvent) throws IOException {
        signIn.getScene().getWindow().hide();
        Stage login = new Stage();
        login.setTitle("Érzelmi intelligencia fejlesztés");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXMLFiles/loginpage.fxml")));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.show();
    }
}
