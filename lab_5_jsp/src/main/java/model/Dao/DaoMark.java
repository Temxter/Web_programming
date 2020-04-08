package model.Dao;

import model.HighLevel.Mark;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

@Stateless
public class DaoMark implements Dao<Mark>, Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(name = "questionUnit", type = PersistenceContextType.EXTENDED)
    EntityManager entityManager;

    @Override
    public Mark get(long id) {
        return entityManager.find(Mark.class, id);
    }

    @Override
    public List<Mark> getAll() {
        Query query = entityManager.createQuery("FROM MARKS");
        return query.getResultList();
    }

    @Override
    public void save(Mark mark) {
        entityManager.persist(mark);
    }


    @Override
    public void update(Mark mark) {
        entityManager.merge(mark);
    }

    @Override
    public void delete(Mark mark) {
        entityManager.remove(mark);
    }
}
