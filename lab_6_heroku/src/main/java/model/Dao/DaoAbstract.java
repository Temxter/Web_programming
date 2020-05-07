package main.java.model.Dao;

import main.java.model.Connector.JpaManager;
import org.eclipse.persistence.sessions.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.function.Consumer;

public abstract class DaoAbstract<T> {

    @PersistenceContext(unitName = "questionUnit")
    private EntityManager entityManager;

    private Class<T> entityBeanType;
    private String entityName;

    public DaoAbstract(Class<T> entityBeanType) {
        this.entityBeanType = entityBeanType;
        entityName = entityManager.getMetamodel().entity(entityBeanType).getName();
    }

    protected Class<T> getEntityBeanType() {
        return entityBeanType;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public T get(int id) {
        return entityManager.find(getEntityBeanType(), id);
    }

    public List<T> getAll() {
        Query query = entityManager.createQuery("FROM " + entityName);
        return query.getResultList();
    }

    public void save(T obj) {
        entityManager.persist(obj);
    }

    public void update(T obj) {
        entityManager.merge(obj);
    }

    public void delete(T obj) {
        entityManager.remove(obj);
    }
}
