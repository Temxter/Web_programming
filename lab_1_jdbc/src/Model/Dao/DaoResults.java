package Model.Dao;

import Model.HighLevel.Mark;
import Model.Connector.ConnectorException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoResults extends Dao {


    final private String TABLE = "results";
    final private String TABLE_FIELDS = "id, mark, question_type, student_id";
    final private String statementString = "SELECT " + TABLE_FIELDS + " FROM " + TABLE + " WHERE student_id = ?";
    final private String insertStatement = "INSERT INTO " + TABLE + " VALUES (?, ?, ?)";


    public DaoResults() throws DaoException {
        super();
    }

    public List<Mark> getResults(int idStudent){
        List<Mark> marks = new ArrayList<>();
        try {
            Connection connection = connector.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(statementString);
            preparedStatement.setInt(1, idStudent);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int questionType = resultSet.getInt("question_type");
                int markInt = resultSet.getInt("mark");
                Mark mark = new Mark(id, questionType, markInt);
                marks.add(mark);
            }
            connector.closeConnection();
        } catch (ConnectorException e) {
            new DaoException("Connect to database failed.", e);
        } catch (SQLException e){
            new DaoException("Failed with statement " + statementString + ".", e);
        }
        return marks;
    }

    public boolean insertMark(Mark m, int studentId) throws DaoException {
        boolean isExecuted = false;
        try {
            Connection connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
            preparedStatement.setInt(1, m.getMark());
            preparedStatement.setInt(2, m.getQuestionType());
            preparedStatement.setInt(3, studentId);
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
