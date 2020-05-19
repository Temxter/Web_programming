package controller;

import model.Dao.DaoUser;
import model.Entities.User;
import model.Entities.UserType;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class TagGetUser extends TagSupport {
    public int doStartTag() throws JspException {

        DaoUser daoUser = new DaoUser();
        List<User> users = daoUser.getAll();
        StringBuilder str = new StringBuilder();

        Locale locale = pageContext.getRequest().getLocale();
        if (locale.getLanguage().equals("ru")) {
            str.append("<table>" +
                    "<caption>" +
                    "Пользователи" +
                    "</caption>" +
                    "<tr>" +
                    "<th>Имя</th>" +
                    "<th>Тип</th>" +
                    "<th>Всего посещений страниц</th>" +
                    "<th>Последнее посещение</th>" +
                    "<th>Редактировать</th>" +
                    "</tr>");
            for (User u: users) {
                str.append( "<tr>" +
                        "<td>" +
                        u.getLogin() +
                        "</td>" +
                        "<td>" +
                        (u.getUserType().equals(UserType.Teacher) ? "Преподаватель" : "Студент") +
                        "</td>" +
                        "<td>" +
                        u.getVisitCounter() +
                        "</td>" +
                        "<td>" +
                        u.getLastVisitDate() +
                        "<td>" +
                        "<a href='" + pageContext.getRequest().getServletContext().getContextPath() +
                        "/teacher/editUser.jsp?id=" + u.getId() + "'>Редактировать</a>" +
                        "</td>" +
                        "</td>" +
                        "</tr>");
            }
        }
        else {
            str.append("<table>" +
                    "<caption>" +
                    "Users" +
                    "</caption>" +
                    "<tr>" +
                    "<th>Name</th>" +
                    "<th>Type</th>" +
                    "<th>Total Visits</th>" +
                    "<th>Last Visit</th>" +
                    "<th>Edit</th>" +
                    "</tr>");
            for (User u: users) {
                str.append( "<tr>" +
                        "<td>" +
                        u.getLogin() +
                        "</td>" +
                        "<td>" +
                        u.getUserType() +
                        "</td>" +
                        "<td>" +
                        u.getVisitCounter() +
                        "</td>" +
                        "<td>" +
                        u.getLastVisitDate() +
                        "</td>" +
                        "<td>" +
                        "<a href='" + pageContext.getRequest().getServletContext().getContextPath() +
                        "/teacher/editUser.jsp?id=" + u.getId() + "'>Edit</a>" +
                        "</td>" +
                        "</tr>");
            }
        }

        str.append("</table>");

        try {

            JspWriter out = pageContext.getOut();
            out.write(str.toString());

        } catch (IOException e) {

            throw new JspException(e.getMessage());

        }
        return SKIP_BODY;
    }
}
