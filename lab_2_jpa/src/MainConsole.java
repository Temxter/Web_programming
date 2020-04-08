import Model.Dao.DaoMark;
import Model.Dao.DaoStudent;
import Model.Dao.DaoTest;
import Model.HighLevel.Mark;
import Model.HighLevel.Question;
import Model.HighLevel.Student;
import Model.HighLevel.Test;

import java.util.*;

public class MainConsole {

    static List<Test> tests;
    static List<Student> students;

    static DaoTest daoTest = null;
    static DaoMark daoMark = null;
    static DaoStudent daoStudent = null;

    static Scanner scanner;

    public static void main(String[] args) {
        init();
        mainMenu();
    }

    static void init() {
        scanner = new Scanner(System.in);

        daoTest = new DaoTest();
        daoStudent = new DaoStudent();
        daoMark = new DaoMark();

        tests = daoTest.getAll();
        students = daoStudent.getAll();
    }

    static int getIntConsole() {
        int choose = -1;
        try {
            choose = scanner.nextInt();
        } catch (Exception e) {
            //Just continue
        }
        return choose;
    }

    static void mainMenu() {
        while (true) {
            System.out.println("\n||MAIN MENU||");
            System.out.println("Choose action:\n" +
                    "1. List of tests (set test)\n" +
                    "2. List of students (testing, marks)\n" +
                    "0. Exit");
            int choose = getIntConsole(); //TODO: check

            switch (choose) {
                case 1:
                    menuTestsList();
                    break;
                case 2:
                    menuStudentsList();
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }

    static void menuTestsList() {
        for (Test t : tests) {
            System.out.println(t);
        }
        System.out.println("0. Back");

        int choose = getIntConsole();
        //TODO: check
        if (choose != 0) {
            menuSetTest(choose);
        } else return;
    }

    static void menuSetTest(int testId) {
        Test testChoosen = null;
        for (Test t : tests){
            if (t.getId() == testId){
                testChoosen = t;
                break;
            }
        }
        if (testChoosen == null){
            System.out.println("Test with id = " + testId + " not founded!");
            return;
        }

        int counter = 1;
        System.out.println("List of students (to set test choose student ID:");
        for (Student s : students) {
            System.out.println(counter + ")" + s.toString());
            counter++;
        }

        int studentId = getIntConsole();

        if (studentId == 0) //check (if student_id == 0)?
            return; //return back

        Student studentChoosen = null;
        for (Student s : students) {
            if (s.getId() == studentId) {
                studentChoosen = s;
                break;
            }
        }

        if (studentChoosen == null) {
            System.out.println("Student with id " + studentId + " not founded!");
            return;
        }

//        if all correct set test to student
        Date date = new Date();
        Mark newMark = new Mark(Mark.NEW_TEST, testChoosen, studentChoosen, date);
        studentChoosen.setMark(newMark);
        daoMark.save(newMark);
    }

    static void menuStudentsList() {
        int counter = 1;
        System.out.println("List of students (to get test choose student ID):");
        for (Student s : students) {
            System.out.println(counter + ")" + s.toString());
            counter++;
        }

        int choose = getIntConsole();

        if (choose != 0) {
            menuPersonal(choose);
        } else return;
    }

    static void menuPersonal(int studentId) {
        Student student = null;
        for (Student s : students) {
            if (s.getId() == studentId) {
                student = s;
                break;
            }
        }
        if (student.getMarks().size() == 0){
            System.out.println("Student '" + student.getName() + "' don't have marks.");
        } else {
            System.out.println("Marks of student '" + student.getName() + "' :");
            for (Mark m : student.getMarks()) {
                if (m.getMark() != Mark.NEW_TEST)
                    System.out.println(m);
            }
        }



        System.out.println("List of available tests for '" + student.getName() + "' (choose): ");
        int counter = 1;
        for (Mark m : student.getUnfilledMarks()) {
            Test t = m.getTest();
            System.out.println("#" + t.getId() + ": " + t.getName());
        }


        int choose = getIntConsole();
        //TODO: check correct test No
        if (choose != 0) {
            for (Mark mark: student.getUnfilledMarks()) {
                if (mark.getTest().getId() == choose) {
                    menuTesting(student, mark);
                    break;
                }
            }
        } else return;
    }

    static void menuTesting(Student student, Mark mark) {
        Test t = mark.getTest();
        int rightAnswers = 0;
        int questionsNumber = t.getQuestions().size();
        for (Question q : t.getQuestions()) {
            System.out.println(q);
            String userAnswer = scanner.next();
            if (userAnswer.equals(q.getRightAnswer()))
                rightAnswers++;
        }
        mark.setRightAnswers(rightAnswers);
        student.setMark(mark);
        daoMark.update(mark);
    }

}
