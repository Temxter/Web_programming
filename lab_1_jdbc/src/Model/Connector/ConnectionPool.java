package Model.Connector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/***
 * Singleton class with private constructor
 */
public class ConnectionPool {

    private BlockingQueue<Connection> connectionQueue;
    private HashSet<Connection> inUsingConnectionsSet;

    private ConfigurationManager cn;

    final private int POOL_SIZE;
    static final private int POOL_SIZE_DEFAULT = 10;

    static private ConnectionPool connectionPool;


    private ConnectionPool(int poolSize) throws ConnectorException {
        POOL_SIZE = poolSize;
        inUsingConnectionsSet = new HashSet<>(POOL_SIZE);
        connectionQueue = new LinkedBlockingQueue<>(POOL_SIZE);
        try {
            cn = new ConfigurationManager();
            Class.forName(cn.getDriver());
        } catch (IOException e) {
            throw new ConnectorException("Configuration manager doesn't loads.", e);
        } catch (ClassNotFoundException e) {
            throw new ConnectorException("Driver doesn't loads", e);
        }

        for (int i = 0; i < POOL_SIZE; i++){
            Connection c = null;
            try {
                c = DriverManager.getConnection(cn.getUrl(), cn.getName(), cn.getPassword());
            } catch (SQLException e) {
                throw new ConnectorException("Create connection to database failed.", e);
            }
            connectionQueue.add(c);
        }
    }

    static public ConnectionPool getInstance() throws ConnectorException {
        if (connectionPool == null){
            connectionPool = new ConnectionPool(POOL_SIZE_DEFAULT);
        }
        return connectionPool;
    }

    static public ConnectionPool getInstance(int poolSize) throws ConnectorException {
        if (connectionPool == null){
            connectionPool = new ConnectionPool(poolSize);
        }
        return connectionPool;
    }

    public Connection getConnection() throws ConnectorException {
        Connection c = connectionQueue.poll();
        inUsingConnectionsSet.add(c);
        return c;
    }

    public void returnBackConnection(Connection connection) throws ConnectorException {
        if (inUsingConnectionsSet.contains(connection)){
            inUsingConnectionsSet.remove(connection);
            connectionQueue.add(connection);
        }
    }
}
