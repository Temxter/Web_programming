package Model.Dao;

public class DaoException extends Exception{
    public DaoException(String message, Exception e){
        super(message + " | " + e.getMessage() );
    }
}
