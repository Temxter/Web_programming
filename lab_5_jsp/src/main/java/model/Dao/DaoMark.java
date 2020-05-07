package model.Dao;

import model.Entities.Mark;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Stateless
@Local
public class DaoMark  {

    @PersistenceContext(name = "questionUnit")
    EntityManager entityManager;

    public Mark get(int id) {
        return entityManager.find(Mark.class, id);
    }

    public List<Mark> getAll() {
        Query query = entityManager.createQuery("FROM MARKS");
        return query.getResultList();
    }

    public void save(Mark mark) {
        entityManager.persist(mark);
    }

    public void update(Mark mark) {
        entityManager.merge(mark);
    }

    public void delete(Mark mark) {
        entityManager.remove(mark);
    }
}
