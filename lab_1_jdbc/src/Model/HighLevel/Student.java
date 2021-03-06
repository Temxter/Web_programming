package Model.HighLevel;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private int id;
    private String name;
    private List<Mark> marks;

    private String surname;
    private int course;


    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setMark(Mark m){
        if (marks == null)
            marks = new ArrayList<>();
        else
            marks.add(m);
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + "'";
    }

    public List<Mark> getUnfilledMarks() {
        List<Mark> negativeMarks = new ArrayList<>();
        for (Mark m: marks){
            if(m.getMark() == -1){
                negativeMarks.add(m);
            }
        }
        return negativeMarks;
    }
}
