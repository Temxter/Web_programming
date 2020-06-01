package model.Entities;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@Entity(name = "TESTS")
public class Test implements Serializable {

    @XmlTransient
    @Transient
    private static final long serialVersionUID = 1L;

    @XmlAttribute

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @XmlAttribute

    private String name;
    @XmlElement(name = "question")

    @OneToMany(mappedBy = "test")
    @LazyCollection(LazyCollectionOption.FALSE)
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

    public Question getQuestion(int questionNum){
        try {
            return questions.get(questionNum);
        } catch (IndexOutOfBoundsException e){
            return null;
        }
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
