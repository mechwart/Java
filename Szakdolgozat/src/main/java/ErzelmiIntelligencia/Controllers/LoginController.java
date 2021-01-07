package ErzelmiIntelligencia.Controllers;

import ErzelmiIntelligencia.connectivity.ConnectionClass;
import ErzelmiIntelligencia.passwordEncrypt.PasswordUtils;
import javafx.fxml.FXMLLoader;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class LoginController {
    public  TextField usernameF;
    public PasswordField pwF;
    public Button loginButton;
    public Button signupButton;
    public Label successLogin;
    String sql;
    Statement statement;
    private static String user;
    public void login(MouseEvent mouseEvent) throws IOException, SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        boolean login = false;
        sql = "SELECT  * FROM `user`";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            String username = resultSet.getString(1);
            String password = resultSet.getString(2);
            String salt = resultSet.getString(3);
            if(username.equals(usernameF.getText())) {
                boolean passwordMatch = PasswordUtils.verifyUserPassword(pwF.getText(),password,salt);
                if(passwordMatch) {
                    login = true;
                    break;
                }
            }
        }
        if(login) {
            setUser(usernameF.getText());
            loginButton.getScene().getWindow().hide();
            Stage informations = new Stage();
            informations.setTitle("Érzelmi intelligencia fejlesztés");
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXMLFiles/informations.fxml")));
            Scene scene = new Scene(root);
            informations.setScene(scene);
            informations.show();
        } else {
            successLogin.setVisible(true);
            successLogin.setTextFill(Paint.valueOf("#FF0000"));
            successLogin.setText("Sikertelen bejelentkezés!!");
        }



    }

    public void signup(MouseEvent mouseEvent) throws IOException {
        signupButton.getScene().getWindow().hide();
        Stage signup = new Stage();
        signup.setTitle("Érzelmi intelligencia fejlesztés");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXMLFiles/signuppage.fxml")));
        Scene scene = new Scene(root);
        signup.setScene(scene);
        signup.show();

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
