package Model.HighLevel;

public class Answer {

    private int id;
    private String answer;
    private boolean isRight;

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
