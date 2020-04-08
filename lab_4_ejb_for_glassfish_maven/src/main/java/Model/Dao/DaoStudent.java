package model;

import Model.HighLevel.Student;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

@Stateless
public class DaoStudent implements Dao<Student>, Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(name = "questionUnit", type = PersistenceContextType.EXTENDED)
    EntityManager entityManager;

    @Override
    public Student get(long id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> getAll() {
        Query query = entityManager.createQuery("FROM STUDENTS");
        return query.getResultList();
    }

    @Override
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    public void delete(Student student) {
        entityManager.remove(student);
    }


}
