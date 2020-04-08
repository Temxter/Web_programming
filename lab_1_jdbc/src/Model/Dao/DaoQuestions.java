package Model.Dao;

import Model.HighLevel.Question;
import Model.Connector.ConnectorException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoQuestions extends Dao {

    final private String TABLE_NAME = "QUESTIONS";
    final private String TABLE_FIELDS = "question, type";
    final private String insertStatement = "INSERT INTO " + TABLE_NAME + " " + TABLE_FIELDS + " VALUES (?, ?)";
    final private String updateStatement = "UPDATE " + TABLE_NAME + " SET question = ?, type = ? WHERE id = ?";

    public DaoQuestions() throws DaoException {
        super();
    }

    public boolean insertQuestion(Question q) throws DaoException {
        boolean isExecuted = false;
        try {
            Connection connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
            preparedStatement.setString(1, q.getQuestion());
            preparedStatement.setInt(2, q.getType());
            isExecuted = preparedStatement.execute();
            connector.closeConnection();
        } catch (ConnectorException e) {
            throw new DaoException("Error get connection to database.", e);
        } catch (SQLException e) {
            throw new DaoException("Query " + insertStatement + " failed.", e);
        }
        return isExecuted;
    }

    public boolean update(Question q) throws DaoException {
        boolean isExecuted = false;
        try {
            Connection connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);
            preparedStatement.setString(1, q.getQuestion());
            preparedStatement.setInt(2, q.getType());
            preparedStatement.setInt(3, q.getId());
            isExecuted = preparedStatement.execute();
        } catch (ConnectorException e) {
            throw new DaoException("Error get connection to database.", e);
        } catch (SQLException e) {
            throw new DaoException("Query " + updateStatement + " failed.", e);
        }
        return isExecuted;
    }

}
