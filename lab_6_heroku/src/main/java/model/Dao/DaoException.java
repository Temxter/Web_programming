package model.Dao;

public class DaoException extends Exception {
    public DaoException(String message) {
        super("[DaoException] " + message);
    }
}
