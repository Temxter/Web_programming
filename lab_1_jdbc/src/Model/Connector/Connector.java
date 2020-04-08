package Model.Connector;

import java.sql.Connection;

public interface Connector {
    Connection getConnection() throws ConnectorException;
    void closeConnection() throws ConnectorException;
}
