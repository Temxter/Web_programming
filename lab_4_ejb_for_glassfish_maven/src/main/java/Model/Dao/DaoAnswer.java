package model;

import Model.Entities.Answer;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Stateless
public class DaoAnswer implements model.Dao<Answer>, Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(name = "questionUnit", type = PersistenceContextType.EXTENDED)
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
