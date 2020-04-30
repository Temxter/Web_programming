package mypackage;

import Model.Entities.Student;

import javax.ejb.EJB;
import java.util.List;

public class Main {

    @EJB
    static model.DaoStudent daoStudent;

    public static void main(String[] args) {
        List<Student> students = daoStudent.getAll();
        for (Student s : students)
            System.out.println(s);
    }

}
