package Model.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TESTS")
public class Test implements Serializable {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "test")
    private List<Question> questions;

    public Test() {
    }

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

    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public int getQuestionsSize(){
        return questions.size();
    };

    private String getQuestionsString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Question q : questions){
            stringBuilder.append(q.toString() + "\n");
        }
        return stringBuilder.toString();
    }

    public String toString(){
        return "Test: #" + id + ") " + name + "\n" +
                getQuestionsString();
    }
}
