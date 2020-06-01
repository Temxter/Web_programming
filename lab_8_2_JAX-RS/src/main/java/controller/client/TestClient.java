package controller.client;

import model.Entities.Test;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestClient {

    private void log(String s) {
        System.out.println("[TestClient]: " + s);
    }

    private String target = "http://localhost:8080/deploy/api/" + "test";

    public Test get(int id) {
        Client client = ClientBuilder.newClient();
        Test test = client.target(target)
                .path(Integer.toString(id))
                .request(MediaType.TEXT_XML)
                .get(Test.class);
        return test;
    }

    public List<Test> getAll() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(target);
        webTarget.request(MediaType.APPLICATION_XML);

        List<Test> tests = new ArrayList<>();

        Response response = webTarget.path("all")
                .request(MediaType.APPLICATION_XML)
                .get();
        response.ok(new GenericEntity<List<Test>>(tests) {}).build();

        log("response status = " + response.getStatus());

        log(tests.toString());
        return tests;
    }

    public void create(Test test) {
        Client client = ClientBuilder.newClient();
        Response response = client.target(target)
                .request(MediaType.TEXT_XML)
                .put(Entity.entity(test, MediaType.TEXT_XML),
                        Response.class);
        if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
            //created
        } else {
            //error
        }
    }

    public void update(Test test) {
        Client client = ClientBuilder.newClient();
        Response response = client.target(target)
                .request(MediaType.TEXT_XML)
                .post(Entity.entity(test, MediaType.TEXT_XML),
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
