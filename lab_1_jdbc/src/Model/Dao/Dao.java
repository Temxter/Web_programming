package Model.Dao;

import Model.Connector.JDBCConnection;
import Model.Connector.ConnectorException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Dao {
    protected JDBCConnection connector;
    final private String deleteStatementString = "DELETE FROM ? WHERE id = ?";
    final private String updateStatement = "UPDATE ? SET ? = ? WHERE id = ?";

    public Dao () throws DaoException {
        try {
            connector = new JDBCConnection();
        } catch (ConnectorException e){
            throw new DaoException("Fail to create JDBCConnection().", e);
        }
    }

    protected boolean updateString(String tableName, String field, String value, int id) throws DaoException {
        boolean isUpdate = false;
        try {
            Connection connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);
            preparedStatement.setString(1, tableName);
            preparedStatement.setString(2, field);
            preparedStatement.setString(3, value);
            preparedStatement.setInt(4, id);
            isUpdate = preparedStatement.execute();
            connector.closeConnection();
        } catch (ConnectorException e) {
            throw new DaoException("Error get connection to database.", e);
        } catch (SQLException e) {
            throw new DaoException("Query " + updateStatement + " failed.", e);
        }

        return isUpdate;
    }

    protected void delete(String tableName, int id) throws DaoException {
        try {
            Connection connection = connector.getConnection();
            PreparedStatement deleteSQL = connection.prepareStatement(deleteStatementString);
            deleteSQL.setString(1, tableName);
            deleteSQL.setInt(2, id);
            deleteSQL.execute();
            connector.closeConnection();
        } catch (ConnectorException e) {
            throw new DaoException("Error get connection to database.", e);
        }
        catch (SQLException e) {
            throw new DaoException("Error delete in table" + tableName + " with id = " + id + ".",
                    e);
        }
    }

}
