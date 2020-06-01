package controller.client;

import model.Entities.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public abstract class ClientAbstract<T> {

    private String target;

    private Class<T> entityBeanType;

    /**
     *
     * @param target - example = "test"
     */
    public ClientAbstract(Class<T> entityBeanType, String target) {
        this.target = "http://localhost:8080/deploy/api/" + target;
        this.entityBeanType = entityBeanType;
    }


    public T get(int id) {
        Client client = ClientBuilder.newClient();
        T item = client.target(target)
                .path(Integer.toString(id))
                .request(MediaType.TEXT_XML)
                .get(entityBeanType);
        return item;
    }

    public List<T> getAll() {
        Client client = ClientBuilder.newClient();
        List<T> items = client.target(target)
                .path("all")
                .request(MediaType.TEXT_XML)
                .get(new GenericType<List<T>>() {});
        return items;
    }

    public void create(T item) {
        Client client = ClientBuilder.newClient();
        Response response = client.target(target)
                .request(MediaType.TEXT_XML)
                .put(Entity.entity(item, MediaType.TEXT_XML),
                        Response.class);
        if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
            //created
        } else {
            //error
        }
    }

    public void update(T item) {
        Client client = ClientBuilder.newClient();
        Response response = client.target(target)
                .request(MediaType.TEXT_XML)
                .post(Entity.entity(item, MediaType.TEXT_XML),
                        Response.class);
    }

    public void delete(int id) {
        Client client = ClientBuilder.newClient();
        Response response = client.target(target)
                .path(Integer.toString(id))
                .request(MediaType.TEXT_XML)
                .delete();
    }

}
