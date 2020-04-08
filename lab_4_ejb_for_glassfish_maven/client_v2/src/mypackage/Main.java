package mypackage;

import Model.Dao.DaoStudent;
import Model.HighLevel.Answer;
import Model.HighLevel.Student;

import javax.ejb.EJB;
import java.util.List;

public class Main {
    @EJB
    static DaoStudent daoStudent;

    public static void main(String[] args) {
        List<Student> students = daoStudent.getAll();
        for (Student s : students)
            System.out.println(s);
    }

}
