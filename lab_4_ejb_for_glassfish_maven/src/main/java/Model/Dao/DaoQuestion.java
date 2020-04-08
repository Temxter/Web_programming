package model;

import Model.HighLevel.Question;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

@Stateless
public class DaoQuestion implements Dao<Question>, Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(name = "questionUnit", type = PersistenceContextType.EXTENDED)
    EntityManager entityManager;

    @Override
    public Question get(long id) {
        return entityManager.find(Question.class, id);
    }

    @Override
    public List<Question> getAll() {
        Query query = entityManager.createQuery("FROM QUESTIONS");
        return query.getResultList();
    }

    @Override
    public void save(Question question) {
        entityManager.persist(question);
    }

    @Override
    public void update(Question question) {
        entityManager.merge(question);
    }

    @Override
    public void delete(Question question) {
        entityManager.remove(question);
    }
}
