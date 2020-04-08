package Model.Connector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection implements Connector{

    private Connection connection;
    private ConfigurationManager cn;

    public JDBCConnection () throws ConnectorException {
        try {
            cn = new ConfigurationManager();
        } catch (IOException e) {
            throw new ConnectorException("Read config file failed.", e);
        }
        try {
            Class.forName(cn.getDriver());
        } catch (ClassNotFoundException e) {
            throw new ConnectorException("Load driver failed", e);
        }
    }

    public Connection getConnection() throws ConnectorException {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(cn.getUrl(), cn.getName(), cn.getPassword());
            } catch (SQLException e) {
                throw new ConnectorException("Connection to database failed.", e);
            }
        }
        return connection;
    }

    public void closeConnection() throws ConnectorException {
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new ConnectorException("Close database connection failed.", e);
            }
        }
        connection = null;
    }

}
