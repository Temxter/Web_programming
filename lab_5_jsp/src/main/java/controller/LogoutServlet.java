package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 8186636133117255570L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<Integer> a = new ArrayList<>();

        HttpSession session = req.getSession(false); //false - if session hadn't created, then return null
        if (session != null){
            session.removeAttribute("user");
            //go to login page
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
