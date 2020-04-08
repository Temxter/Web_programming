package Model.HighLevel;

import javax.persistence.*;

@Entity(name = "ANSWERS")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String answer;
    private boolean isRight;
    @ManyToOne(cascade = CascadeType.ALL)
    private Question question;

    public Answer() {
    }

    public Answer(int id, String answer, boolean isRight) {
        this.id = id;
        this.answer = answer;
        this.isRight = isRight;
    }

    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isRight() {
        return isRight;
    }

    @Override
    public String toString() {
        return answer;
    }
}
