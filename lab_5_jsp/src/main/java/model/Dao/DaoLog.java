package model.Dao;

import model.Entities.Log;
import model.Entities.Mark;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local
public class DaoLog {
    @PersistenceContext(name = "questionUnit")
    EntityManager entityManager;

    public Log get(int id) {
        return entityManager.find(Log.class, id);
    }

    public List<Log> getAll() {
        Query query = entityManager.createQuery("FROM LOGS");
        return query.getResultList();
    }

    public void save(Log log) {
        entityManager.persist(log);
    }

    public void update(Log log) {
        entityManager.merge(log);
    }

    public void delete(Log log) {
        entityManager.remove(log);
    }
}
