package model.HighLevel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "MARKS")
public class Mark implements Serializable {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int rightAnswers;
    //    private int total;
    @ManyToOne
    private Test test;
    @ManyToOne
    private Student student;
    private Date date;
    public final static int NEW_TEST = -1;

    public Mark() {
    }

    public Mark(int rightAnswers, Test test, Student student, Date date) {
        this.rightAnswers = rightAnswers;
        this.test = test;
        this.student = student;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(int rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public int getMark() {
        double total = getTotalQuestions();
        return (int) ((double) rightAnswers / total * 10.0);
    }

    public Test getTest() {
        return test;
    }

    public Student getStudent() {
        return student;
    }

    public Date getDate() {
        return date;
    }

    // testName for JSP
    public String getTestName() {
        return test.getName();
    }

    private int getTotalQuestions() {
        return test.getQuestionsSize();
    }


    @Override
    public String toString() {
        return "Student=" + student.getName() + ", test=" + test.getName() + ", date=" + date + ", rightAnswers=" + rightAnswers;
    }

//    public Mark(Test test, int rightAnswers, int total) {
//        this.test = test;
//        this.rightAnswers = rightAnswers;
//        this.total = total;
//    }

}
