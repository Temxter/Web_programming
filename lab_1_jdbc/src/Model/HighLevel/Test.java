package Model.HighLevel;

import java.util.ArrayList;
import java.util.List;

public class Test {

    private int id;
    private List<Question> questions;

    public Test(int id) {
        this.id = id;
        questions = new ArrayList<>();
    }

    public void addQuestion(Question question){
        questions.add(question);
    }

    public int getId() {
        return id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    private String getQuestionsString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Question q : questions){
            stringBuilder.append(q.toString() + "\n");
        }
        return stringBuilder.toString();
    }

    public String toString(){
        return "Test: #" + id + "\n" +
                getQuestionsString();
    }
}
