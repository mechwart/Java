package ErzelmiIntelligencia.Controllers;

import ErzelmiIntelligencia.Outer_variables.CorrectAnswerCounter;
import ErzelmiIntelligencia.application.Question;
import ErzelmiIntelligencia.application.QuestionCollector;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Quiz extends CorrectAnswerCounter {

    public TextArea valasz_box;
    public Button next_q;
    public RadioButton first_button;
    public RadioButton second_button;
    public RadioButton third_button;
    public RadioButton fourth_button;
    public Button first_ans;
    public  ToggleGroup group = new ToggleGroup();
    public List<Question> questions;
    public TextArea question;
    public Label questionTheme;
    public int correctIndex;
    public int currentIndex;
    public ImageView image;
    public AnchorPane first_q;

    public  void init() throws IOException {
        currentIndex = 0;
        questions = QuestionCollector.getRandomQuestions(10);
        setNewQuestion(questions.get(currentIndex));
        currentIndex++;

    }

    private void setNewQuestion(Question q) {
        List<String> answers = q.getAnswers();
        first_button.setText(answers.get(0));
        second_button.setText(answers.get(1));
        if(answers.get(2) != null) {
            third_button.setText(answers.get(2));
            third_button.setVisible(true);
        } else {
            third_button.setVisible(false);

        }
        if(answers.get(3) != null) {
            fourth_button.setText(answers.get(3));
            fourth_button.setVisible(true);
        } else {
            fourth_button.setVisible(false);

        }
        question.setText(q.getQuestion());
        questionTheme.setText(q.getTheme());
        correctIndex = q.getCorrect_answer();
        valasz_box.setText(q.getDescription());
        valasz_box.setVisible(false);
        next_q.setVisible(false);
        first_ans.setVisible(false);
        first_button.setTextFill(Paint.valueOf("#ea9a96"));
        second_button.setTextFill(Paint.valueOf("#ea9a96"));
        third_button.setTextFill(Paint.valueOf("#ea9a96"));
        fourth_button.setTextFill(Paint.valueOf("#ea9a96"));
        first_button.setDisable(false);
        second_button.setDisable(false);
        third_button.setDisable(false);
        fourth_button.setDisable(false);
        first_button.setSelected(false);
        second_button.setSelected(false);
        third_button.setSelected(false);
        fourth_button.setSelected(false);
        if(q.getImageurl() != null) {
            image.setImage(new Image(getClass().getClassLoader().getResourceAsStream("images/"+q.getImageurl())));
            image.setVisible(true);
            question.setVisible(false);

        } else {
            image.setVisible(false);
            question.setVisible(true);
        }
    }
    public void show_answ1(MouseEvent mouseEvent) {

        first_button.setToggleGroup(group);
        second_button.setToggleGroup(group);
        third_button.setToggleGroup(group);
        fourth_button.setToggleGroup(group);
        first_ans.setVisible(true);
    }

    public void show_answ2(MouseEvent mouseEvent) {

        first_button.setToggleGroup(group);
        second_button.setToggleGroup(group);
        third_button.setToggleGroup(group);
        fourth_button.setToggleGroup(group);
        first_ans.setVisible(true);
    }
    public void show_answ3(MouseEvent mouseEvent) {

        first_button.setToggleGroup(group);
        second_button.setToggleGroup(group);
        third_button.setToggleGroup(group);
        fourth_button.setToggleGroup(group);
        first_ans.setVisible(true);





    }

    public void show_answ4(MouseEvent mouseEvent) {

        first_button.setToggleGroup(group);
        second_button.setToggleGroup(group);
        third_button.setToggleGroup(group);
        fourth_button.setToggleGroup(group);
        first_ans.setVisible(true);





    }



    public void answer(MouseEvent mouseEvent) {
        valasz_box.setVisible(true);
        first_ans.setVisible(false);
        if(first_button.isSelected() && correctIndex == 1) {
            first_button.setTextFill(Paint.valueOf("#7CFC00"));
            second_button.setTextFill(Paint.valueOf("#FF0000"));
            third_button.setTextFill(Paint.valueOf("#FF0000"));
            fourth_button.setTextFill(Paint.valueOf("#FF0000"));
            second_button.setDisable(true);
            third_button.setDisable(true);
            fourth_button.setDisable(true);
            setCounter();

        }
        if(second_button.isSelected()  && correctIndex == 2)
        {
            first_button.setTextFill(Paint.valueOf("#FF0000"));
            second_button.setTextFill(Paint.valueOf("#7CFC00"));
            third_button.setTextFill(Paint.valueOf("#FF0000"));
            fourth_button.setTextFill(Paint.valueOf("#FF0000"));
            first_button.setDisable(true);
            third_button.setDisable(true);
            fourth_button.setDisable(true);
            setCounter();
        }
        if(third_button.isSelected()  && correctIndex == 3 ) {
            first_button.setTextFill(Paint.valueOf("#FF0000"));
            second_button.setTextFill(Paint.valueOf("#FF0000"));
            third_button.setTextFill(Paint.valueOf("#7CFC00"));
            fourth_button.setTextFill(Paint.valueOf("#FF0000"));
            second_button.setDisable(true);
            first_button.setDisable(true);
            fourth_button.setDisable(true);
            setCounter();
        }
        if(fourth_button.isSelected()  && correctIndex == 4)
        {
            first_button.setTextFill(Paint.valueOf("#FF0000"));
            second_button.setTextFill(Paint.valueOf("#FF0000"));
            third_button.setTextFill(Paint.valueOf("#FF0000"));
            fourth_button.setTextFill(Paint.valueOf("#7CFC00"));
            second_button.setDisable(true);
            first_button.setDisable(true);
            third_button.setDisable(true);
            second_button.setDisable(true);
            setCounter();
        }
        next_q.setVisible(true);

    }

    public void nextquestion(MouseEvent mouseEvent) throws IOException {
        if(currentIndex == questions.size()) {
            next_q.getScene().getWindow().hide();
            Stage result_stage = new Stage();
            result_stage.setTitle("Érzelmi intelligencia fejlesztés");
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXMLFiles/resultpage.fxml")));
            Scene scene = new Scene(root);
            result_stage.setScene(scene);
            result_stage.show();

        } else {
            setNewQuestion(questions.get(currentIndex++));
        }
    }
}
