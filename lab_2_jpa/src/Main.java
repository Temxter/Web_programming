import Model.Dao.DaoMark;
import Model.Dao.DaoStudent;
import Model.Dao.DaoTest;
import Model.Dao.JpaManager;
import Model.HighLevel.Mark;
import Model.HighLevel.Student;
import Model.HighLevel.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transaction;
import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("questionUnit");
        DaoStudent daoStudent = new DaoStudent();
        List<Student> studentList = daoStudent.getAll();

        DaoTest daoTest = new DaoTest();
        List<Test> tests = daoTest.getAll();

        DaoMark daoMark = new DaoMark();

        for (Test t: tests){
            System.out.println(t);
        }

        List<Mark> unpassedTestsMarks = daoMark.getNotPassedTests();

        for (Mark m : unpassedTestsMarks){
            System.out.println(m);
        }

        for (Student s: studentList){
            System.out.println(s);
            System.out.println("marks: ");
            for (Mark m : s.getMarks())
                System.out.println(m);
//            Date date = new Date();
//            Mark newMark = new Mark(-1, tests.get(0), s, date);
//            s.setMark(newMark);
//            daoMark.save(newMark);
        }
    }
}
