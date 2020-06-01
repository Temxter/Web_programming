package controller.servlets;


import model.Entities.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.List;

@WebServlet(urlPatterns = {"/tests"})
public class TestServlet extends HttpServlet {

//    @EJB
//    TestService testService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


//        String path = "http://localhost:8080/deploy/api/" + "test";
//        ResteasyClient client = new ResteasyClientBuilder().build();
//        ResteasyWebTarget target = client.target(UriBuilder.fromPath(path));
//        TestService proxy = target.proxy(TestService.class);
////Todo need to create testService interface
//
//
//        List<Test> tests = proxy.getAll();


//        TestClient testClient = new TestClient();
//
//        List<Test> tests = testClient.getAll();
//
//        req.setAttribute("tests", tests);
//        req.getRequestDispatcher("/tests.jsp").forward(req, resp);
    }
}
