package ErzelmiIntelligencia.application;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class QuestionCollector {
    private static List<Question> questions;

    private QuestionCollector() {
    }
    private static List<Question> collectQuestion() throws IOException {
        if(questions != null) {
            return questions;
        }
        questions = new ArrayList<Question>();
        InputStream is = QuestionCollector.class.getResourceAsStream("/questions/questions.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = reader.readLine();
        while(!line.equals("vége")) {
            Question current = new Question();
            if(line.equals("null")) {
                current.setTheme(null);
            } else {
                current.setTheme(line);
            }
            current.setQuestion(reader.readLine());
            String[] answers = reader.readLine().split(";");
            for (int i = 0; i < answers.length; i++) {
                if(answers[i].equals("null")) {
                    answers[i] = null;
                }
            }
            current.setAnswers(Arrays.asList(answers));
            current.setCorrect_answer(Integer.parseInt(reader.readLine()));
            current.setDescription(reader.readLine());
            String imageUrl = reader.readLine();
            if(imageUrl.equals("null")) {
                current.setImageurl(null);
            } else {
                current.setImageurl(imageUrl);
            }
            line = reader.readLine();
            questions.add(current);
        }
        is.close();
        return  questions;
    }
    public static List<Question> getRandomQuestions(int numquestions) throws IOException {
        List<Question> questions = QuestionCollector.collectQuestion();
        List<Question> randomQuestions = new ArrayList<>();
        List<Integer> questionIndex = new ArrayList<>();
        Random rnd = new Random();
        while (numquestions > 0) {
            int index = rnd.nextInt(questions.size());
            if(!questionIndex.contains(index)) {
                questionIndex.add(index);
                randomQuestions.add(questions.get(index));
                numquestions--;
            }
        }
        return randomQuestions;
    }
}
