import Model.Dao.DaoException;
import Model.Dao.DaoResults;
import Model.Dao.DaoStudent;
import Model.Dao.DaoTest;
import Model.HighLevel.Mark;
import Model.HighLevel.Question;
import Model.HighLevel.Student;
import Model.HighLevel.Test;

import java.util.*;

public class Main {

    static Map<Integer, Test> testMap = null;
    static List<Student> studentList = null;

    static DaoTest daoTest = null;
    static DaoResults daoResults = null;
    static DaoStudent daoStudent = null;

    static Scanner scanner;

    public static void main(String[] args) {
        init();
        mainMenu();
    }

    static void init() {
        scanner = new Scanner(System.in);

        try {
            daoTest = new DaoTest();
            daoStudent = new DaoStudent();
            daoResults = new DaoResults();

            testMap = daoTest.getTests();
            studentList = daoStudent.getStudents();
            for (Student s : studentList) {
                List<Mark> markList = daoResults.getResults(s.getId());
                s.setMarks(markList);
            }
        } catch (DaoException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
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
        for (Test t : testMap.values()) {
            System.out.println(t);
        }
        System.out.println("0. Back");

        int choose = getIntConsole();
        //TODO: check
        if (choose != 0) {
            menuSetTest(choose);
        } else return;
    }

    static void menuSetTest(int questionType) {
        Student student = null;
        int counter = 1;
        System.out.println("List of students (to set test choose student ID:");
        for (Student s : studentList) {
            System.out.println(counter + ")" + s.toString());
            counter++;
        }

        int studentId = getIntConsole();

        if (studentId == 0) //check (if student_id == 0)?
            return; //return back

        for (Student s : studentList) {
            if (s.getId() == studentId) {
                student = s;
                break;
            }
        }

        if (student == null) {
            System.out.println("Student with id " + studentId + " not founded!");
            return;
        }

        //if all correct set test to student
        Mark mark = new Mark(questionType, Mark.NEW_TEST);
        student.setMark(mark);
        try {
            daoResults.insertMark(mark, student.getId());
        } catch (DaoException e) {
            System.out.println("Result not wrote to DB! | daoResults.insertMark(mark, student.getId())");
            e.getStackTrace();
        }


    }

    static void menuStudentsList() {
        int counter = 1;
        System.out.println("List of students (to get test choose student ID):");
        for (Student s : studentList) {
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
        for (Student s : studentList) {
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
        Set<Integer> availableTypeOfQuestionsSet = new HashSet<>();
        for (Mark m : student.getUnfilledMarks()) {
            int questionType = m.getQuestionType();
            for (Test t : testMap.values()) {
                if (questionType == t.getId()) {
                    availableTypeOfQuestionsSet.add(questionType);
                    System.out.println("Test #: " + t.getId());
                }
            }
        }

        int choose = getIntConsole();
        //TODO: check correct test No
        if (choose != 0) {
            for (Test t : testMap.values()) {
                if (t.getId() == choose) {
                    menuTesting(student, t);
                    break;
                }
            }
        } else return;
    }

    static void menuTesting(Student student, Test t) {
        int rightAnswers = 0;
        int questionsNumber = t.getQuestions().size();
        for (Question q : t.getQuestions()) {
            System.out.println(q);
            String userAnswer = scanner.next();
            if (userAnswer.equals(q.getRightAnswer()))
                rightAnswers++;
        }
        Mark mark = new Mark(t.getId(), rightAnswers*10 / questionsNumber);
        student.setMark(mark);
        try {
            daoResults.insertMark(mark, student.getId());
        } catch (DaoException e) {
            System.out.println("Result not wrote to DB! | daoResults.insertMark(mark, student.getId())");
            e.getStackTrace();
        }
    }

}
