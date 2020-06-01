package controller.services;

import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


public abstract class ServiceAbstract<T> {

    @PersistenceContext(unitName = "questionUnit")
    private EntityManager entityManager;

    private Class<T> entityBeanType;
    private String entityName;

    public ServiceAbstract(Class<T> entityBeanType) {
        this.entityBeanType = entityBeanType;
    }

    public ServiceAbstract() {
    }

    protected Class<T> getEntityBeanType() {
        return entityBeanType;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected void closeEntityManager(){
        entityManager.close();
    }

    protected String getEntityName() {
        if (entityName == null)
            entityName = entityManager.getMetamodel().entity(entityBeanType).getName();
        return entityName;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_XML)
    public T get(@PathParam("id") int id) {
        return entityManager.find(getEntityBeanType(), id);
    }

    @GET
    @Path("all")
    @Produces(MediaType.TEXT_XML)
    public List<T> getAll() {
        Query query = entityManager.createQuery("FROM " + getEntityName());
        return query.getResultList();
    }

    @PUT
    @Consumes(MediaType.TEXT_XML)
    public void save(T obj) {
        entityManager.persist(obj);
    }

    @POST
    @Consumes(MediaType.TEXT_XML)
    public void update(T obj) {
        entityManager.merge(obj);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") int id) {
        entityManager.createQuery("DELETE FROM " + getEntityName() + " WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

}

