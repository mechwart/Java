package ErzelmiIntelligencia.Controllers;

import ErzelmiIntelligencia.Outer_variables.CorrectAnswerCounter;
import ErzelmiIntelligencia.connectivity.ConnectionClass;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class ResultController extends CorrectAnswerCounter {
    public TextArea feedback_text;
    public Button new_game;
    public Button quit_button;
    public TextField result_field ;
    public Button showButton;


    public void new_game(MouseEvent mouseEvent) throws IOException {
        new_game.getScene().getWindow().hide();
        Stage new_game_stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXMLFiles/startpage.fxml")));
        Scene scene = new Scene(root);
        new_game_stage.setScene(scene);
        new_game_stage.show();
    }

    public void quit(MouseEvent mouseEvent) {
      Stage stage = (Stage) quit_button.getScene().getWindow();
      stage.close();
    }



    public void show_result(MouseEvent mouseEvent) throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        LoginController user = new LoginController();
        String query = "UPDATE `user` SET `point` = '"+getCounter()+"' WHERE `name` = '"+user.getUser()+"'";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.executeUpdate(query);

        result_field.setText(""+getCounter());
        result_field.setVisible(true);
        showButton.setVisible(false);
        if(getCounter()<3)
        {
            feedback_text.setText("Elég gyenge végeredmény! Javaslom, hogy próbáld újra és olvasd el a magyarázatot is mindig a kérdések után!");
            feedback_text.setVisible(true);
        }
        if(getCounter()>=3 && getCounter()<7)
        {
            feedback_text.setText("Kezdetnek nem rossz! Próbáld újra a szebb eredményért!");
            feedback_text.setVisible(true);
        }
        if(getCounter()>=7 && getCounter()<=10)
        {
            feedback_text.setText("Ügyes vagy! Remek szituáció kezelő és szituáció felismerő ember vagy! Csak így tovább!");
            feedback_text.setVisible(true);
        }

        new_game.setVisible(true);
        quit_button.setVisible(true);
    }
}
