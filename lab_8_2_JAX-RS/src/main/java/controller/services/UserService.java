package controller.services;

import model.Entities.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("user")
public class UserService {
    @PersistenceContext(unitName = "questionUnit")
    private EntityManager em;

    @GET
    @Path("all")
    @Produces(MediaType.TEXT_XML)
    public List<User> getAll() {
        String table = em.getMetamodel().entity(User.class).getName();
        List<User> users  = em.createQuery("FROM " + table).getResultList();
        return users;
    }

    @GET
    @Path("{id}")
    @Consumes(MediaType.TEXT_XML)
    public User get(@PathParam("id") int id) {
        return em.find(User.class, id);
    }

    @POST
    @Consumes(MediaType.TEXT_XML)
    public void update(User user) {
        em.merge(user);
    }

    @PUT
    @Path("{newId}")
    @Consumes(MediaType.TEXT_XML)
    public void create(User user, @PathParam("newId") int newId) {
        // if newId not in DB then create new record
        em.persist(user);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") int id) {
        em.createQuery("DELETE FROM " + "USERS" + " WHERE id = :id")
        .setParameter("id", id)
        .executeUpdate();
    }

}
