package model.Dao;


import model.HighLevel.Answer;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Stateless
public class DaoAnswer implements Dao<Answer>, Serializable {

    private static final long serialVersionUID = 1L;

    // TRANSACTION - when method called then container add new entityManager
    // when method finished (and transactions finished too) entityManager will be closed
    @PersistenceContext(name = "questionUnit", type = PersistenceContextType.TRANSACTION)
    EntityManager entityManager;

    @Override
    public Answer get(long id) {
        return entityManager.find(Answer.class, id);
    }

    @Override
    public List<Answer> getAll() {
        Query query = entityManager.createQuery("FROM ANSWERS");
        return query.getResultList();
    }

    @Override
    public void save(Answer answer) {
        entityManager.persist(answer);
    }

    @Override
    public void update(Answer answer) {
        entityManager.merge(answer);
    }

    @Override
    public void delete(Answer answer) {
        entityManager.remove(answer);
    }
}
