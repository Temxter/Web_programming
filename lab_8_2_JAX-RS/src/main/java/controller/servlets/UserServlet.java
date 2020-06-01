package controller.servlets;

import model.Entities.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/users"})
public class UserServlet extends HttpServlet {

//    @EJB
//    UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        UserClient userClient = new UserClient();
//        List<User> users = userClient.getAll();
//        req.setAttribute("users", users);
//        req.getRequestDispatcher("/users.jsp").forward(req, resp);
    }
}
