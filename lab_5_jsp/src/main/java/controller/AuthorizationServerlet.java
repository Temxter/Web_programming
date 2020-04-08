package controller;

import model.Dao.DaoStudent;
import model.Dao.DaoUser;
import model.HighLevel.Student;
import model.HighLevel.User;
import model.HighLevel.UserType;
import org.hibernate.Session;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/auth")
public class AuthorizationServerlet extends HttpServlet {

    private static final long serialVersionUID = -2501883247602696674L;

    @EJB
    private DaoUser daoUser;

    private String errorPage = "errors/notfound.jsp";

    private void requestToPage(HttpServletRequest req, HttpServletResponse resp, String page) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(page);
        requestDispatcher.forward(req, resp);
    }

    private void setSession(HttpServletRequest req, User user){
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String isLogin = req.getParameter("type"); // "login" or "register"
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        String destinationPage = null;

        // if request from login page
        if (isLogin.equals("login")) {
            User user = daoUser.authorizationUser(login, password);

            if (user == null) {
                String message = "Invalidate login/password!";
                req.setAttribute("message", message);
                return;
            }

            setSession(req, user);

            if (UserType.Student.equals(user.getUserType()))
                destinationPage = "student.jsp";
            else
                destinationPage = "teacher.jsp";

            requestToPage(req, resp, destinationPage);
        }
        // registration
        else if (isLogin.equals("register")) {
            // 1. if login already exist
            User user = daoUser.getUserByLogin(login);
            if (user != null) {
                String message = "The user with login " + user.getLogin() + " exist! Please, write another login.";
                req.setAttribute("message", message);
            }
            // 2. if password not equals to repeat password
            String repeatPassword = req.getParameter("repeat-password");
            if (!password.equals(repeatPassword)) {
                String message = "Invalidate repeat password!";
                req.setAttribute("message", message);
                return;
            }
            // default registration for student only
            Student newStudent = new Student(login);
            User newUser = new User(login, password, UserType.Student, newStudent);
            // write to DB
            daoUser.save(newUser);

            setSession(req, newUser);

            // login
            destinationPage = "student.jsp";
            requestToPage(req, resp, destinationPage);
        } else
            getServletContext().getRequestDispatcher(errorPage);
    }
}
