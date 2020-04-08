package Model.Dao;

import Model.HighLevel.Student;
import Model.Connector.ConnectorException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DaoStudent extends Dao {

    final private Logger logger;

    final private String TABLE_NAME = "students";
    final private String TABLE_FIELDS = "id, name";
    final private String selectStatementString = "SELECT * FROM " + TABLE_NAME;
    final private String insertStatement = "INSERT INTO " + TABLE_NAME + " VALUES (?)";

    public DaoStudent() throws DaoException {
        super();

        logger = Logger.getLogger(getClass().getName());
    }

    private void log(String s){
        //logger.log(Level.INFO, s);
    }


    public List<Student> getStudents(){
        log("Method [getStudents] called");
        List<Student> students = new ArrayList<>();
        try {
            Connection connection = connector.getConnection();
            log("Connection is right");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectStatementString);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Student student = new Student(id, name);
                students.add(student);
                log("Add to list student: " + student);
            }
        } catch (ConnectorException e) {
            new DaoException("Connect to database failed.", e);
        } catch (SQLException e){
            new DaoException("Failed with statement " + selectStatementString + ".", e);
        }
        return students;
    }

    public boolean insertStudent(Student s) throws DaoException {
        boolean isExecuted = false;
        try {
            Connection connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
            preparedStatement.setString(1, s.getName());
            isExecuted = preparedStatement.execute();
            connector.closeConnection();
        } catch (ConnectorException e) {
            throw new DaoException("Error get connection to database.", e);
        } catch (SQLException e) {
            throw new DaoException("Query " + insertStatement + " failed.", e);
        }
        return isExecuted;
    }

}
