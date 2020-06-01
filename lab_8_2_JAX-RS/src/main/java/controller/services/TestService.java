package controller.services;

import model.Entities.Test;

import javax.ws.rs.Path;

@Path("test")
public class TestService extends ServiceAbstract<Test> {
    public TestService() {
        super(Test.class);
    }
}
