package Model.Connector;

public class ConnectorException extends Exception {
    public ConnectorException(String exception, Exception e) {
        super(exception + " | " + e.getMessage() + " (" + e.getClass().getSimpleName() + ").");

    }
}
