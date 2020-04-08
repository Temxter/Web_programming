package Model.Dao;

import Model.HighLevel.Mark;
import Model.HighLevel.Student;

import javax.persistence.NamedQuery;
import javax.persistence.Query;
import java.util.List;

public class DaoMark extends DaoAbstract implements Dao<Mark> {

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
        executeInsideTransaction(entityManager -> entityManager.persist(mark));
    }

    public List<Mark> getNotPassedTests(){
        return entityManager.createNamedQuery("getNotPassedTests").getResultList();
    }

    @Override
    public void update(Mark mark) {
        executeInsideTransaction(entityManager -> entityManager.merge(mark));
    }

    @Override
    public void delete(Mark mark) {
        executeInsideTransaction(entityManager -> entityManager.remove(mark));
    }
}
