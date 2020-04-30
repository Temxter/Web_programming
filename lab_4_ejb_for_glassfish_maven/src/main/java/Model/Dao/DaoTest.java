package model;

import Model.Entities.Test;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

@Stateless
public class DaoTest implements model.Dao<Test>, Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(name = "questionUnit", type = PersistenceContextType.EXTENDED)
    EntityManager entityManager;

    @Override
    public Test get(long id) {
        return entityManager.find(Test.class, id);
    }

    public List<Test> getAll() {
        Query query = entityManager.createQuery("FROM TESTS");
        return query.getResultList();
    }

    @Override
    public void save(Test test) {
        entityManager.persist(test);
    }

    @Override
    public void update(Test test) {
        entityManager.merge(test);
    }

    @Override
    public void delete(Test test) {
        entityManager.remove(test);
    }
}
