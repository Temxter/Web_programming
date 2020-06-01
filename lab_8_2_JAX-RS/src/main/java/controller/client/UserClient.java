package controller.client;

import model.Entities.User;

public class UserClient extends ClientAbstract<User> {

    public UserClient() {
        super(User.class, "user");
    }
}
