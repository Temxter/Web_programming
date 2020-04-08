package Model.HighLevel;

public class Mark {

    private int id;
    //private Test test;
    //private int rightAnswers;
    //private int total;
    private int mark;
    private int questionType;
    public final static int NEW_TEST = -1;

    public Mark(int questionType, int mark) {
        this.id = -1;
        this.questionType = questionType;
        this.mark = mark;
    }

    public Mark(int id, int questionType, int mark) {
        this.id = id;
        this.questionType = questionType;
        this.mark = mark;
    }

    //TODO
//    public void calculateAndSetMark(rightAnswers*10 / questionsNumber){
//
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    @Override
    public String toString() {
        return "questionType=" + questionType + ", mark=" + mark;
    }
    //    public Mark(Test test, int rightAnswers, int total) {
//        this.test = test;
//        this.rightAnswers = rightAnswers;
//        this.total = total;
//    }
//
//    public int getRightAnswers() {
//        return rightAnswers;
//    }
//
//    public void setRightAnswers(int rightAnswers) {
//        this.rightAnswers = rightAnswers;
//    }
//
//    public int getTotal() {
//        return total;
//    }
//
//    public void setTotal(int total) {
//        this.total = total;
//    }
}
