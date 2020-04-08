package Model.HighLevel;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private int id;
    private List<Answer> answers;
    private String question;
    private int type;
    final private String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public Question(int id, String question, int type) {
        this.id = id;
        this.question = question;
        this.type = type;
        answers = new ArrayList<>();
    }

    public String getRightAnswer(){
        int counter = 0;
        for (Answer a: answers){
            if(a.isRight())
                return String.valueOf(ALPHABET.charAt(counter));
            counter++;
        }
        return String.valueOf(ALPHABET.charAt(counter));
    }

    public void addAnswer(Answer answer){
        answers.add(answer);
    }

    public List<Answer> getAnswers(){
        return answers;
    }

    public String getQuestion() {
        return question;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getAnswersString(){
        StringBuilder stringBuilder = new StringBuilder();
        int counter = 0;
        for (Answer a : answers){
            stringBuilder.append(ALPHABET.charAt(counter) + ") " + a.toString() + "; ");
            counter++;
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return id + ") " + question + "\n" + "\t" +
                getAnswersString();
    }
}
