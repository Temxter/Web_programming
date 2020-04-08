package Model.Dao;

import Model.HighLevel.Answer;
import Model.Connector.ConnectorException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoAnswer extends Dao{

    final private String TABLE = "answers";
    final private String TABLE_FIELDS = "id, answer, is_right, question_id";
    final private String statementString = "SELECT " + TABLE_FIELDS + " FROM " + TABLE + " WHERE question_id = ?";
    final private String insertStatement = "INSERT INTO " + TABLE + " VALUES (?, ?, ?)";

    public DaoAnswer() throws DaoException {
        super();
    }

    public List<Answer> getAnswers(int idQuestion){
        List<Answer> answers = new ArrayList<>();

        Connection connection = null;
        try {
            connection = connector.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(statementString);
            preparedStatement.setInt(1, idQuestion);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String answerString = resultSet.getString("answer");
                boolean isRight = resultSet.getBoolean("is_right");
                Answer answer = new Answer(id, answerString, isRight);
                answers.add(answer);
            }
            connector.closeConnection();
        } catch (ConnectorException e) {
            new DaoException("Connect to database failed.", e);
        } catch (SQLException e){
            new DaoException("Failed with statement " + statementString + ".", e);
        }


        return answers;
    }

    public boolean insertAnswer(Answer answer, int questionId) throws DaoException {
        boolean isExecuted = false;
        try {
            Connection connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
            preparedStatement.setString(1, answer.getAnswer());
            preparedStatement.setBoolean(2, answer.isRight());
            preparedStatement.setInt(3, questionId);
            isExecuted = preparedStatement.execute();
            connector.closeConnection();
        } catch (ConnectorException e) {
            throw new DaoException("Error get connection to database.", e);
        } catch (SQLException e) {
            throw new DaoException("Query " + insertStatement + " failed.", e);
        }
        return isExecuted;
        //set id of new record
        //answer.setId(newId);
    }
}
