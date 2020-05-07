package model.Dao;


import model.Entities.Test;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

@Stateless
@Local
public class DaoTest {

    @PersistenceContext(name = "questionUnit")
    EntityManager entityManager;

    public Test get(int id) {
        return entityManager.find(Test.class, id);
    }

    public List<Test> getAll() {
        Query query = entityManager.createQuery("FROM TESTS");
        return query.getResultList();
    }

    public void save(Test test) {
        entityManager.persist(test);
    }

    public void update(Test test) {
        entityManager.merge(test);
    }

    public void delete(Test test) {
        entityManager.remove(test);
    }
}
