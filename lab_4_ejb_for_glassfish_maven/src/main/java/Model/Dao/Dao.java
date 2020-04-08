package model;


import javax.ejb.Local;
import javax.ejb.Remote;
import java.util.List;

@Local
public interface Dao<T> {

    T get(long id);

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);

}
