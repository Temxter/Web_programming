package model.Dao;


import model.Entities.Student;

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
public class DaoStudent {

    @PersistenceContext(name = "questionUnit")
    EntityManager entityManager;

    public Student get(int id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> getAll() {
        Query query = entityManager.createQuery("FROM STUDENTS");
        return query.getResultList();
    }

    public void save(Student student) {
        entityManager.persist(student);
    }

    public void update(Student student) {
        entityManager.merge(student);
    }

    public void delete(Student student) {
        entityManager.remove(student);
    }


}
