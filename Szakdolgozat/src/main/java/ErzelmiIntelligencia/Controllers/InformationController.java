package ErzelmiIntelligencia.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class InformationController {

    public Button start_button;
    public CheckBox checkbox_infos;


    public void klikk(MouseEvent mouseEvent) {
       if(checkbox_infos.isSelected())
        start_button.setDisable(false);
       else
        start_button.setDisable(true);
    }


    public void start(MouseEvent mouseEvent) throws IOException {


        start_button.getScene().getWindow().hide();
        Stage quiz = new Stage();
        quiz.setResizable(false);
        quiz.setTitle("Érzelmi intelligencia fejlesztés");
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("FXMLFiles/quiz.fxml"));
        Parent root = loader.load();
        loader.<Quiz>getController().init();
        Scene scene = new Scene(root);
        quiz.setScene(scene);
        quiz.show();
    }

}
