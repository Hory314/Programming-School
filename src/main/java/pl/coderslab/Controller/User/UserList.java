package pl.coderslab.Controller.User;

import pl.coderslab.Dao.UserDao;
import pl.coderslab.Entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserList", urlPatterns = {"/user/list", "/user/list/", "/users", "/users/"})
public class UserList extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        UserDao userDao = new UserDao();
        List<User> users = userDao.findAll();

        request.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/WEB-INF/views/user/list.jsp").forward(request, response);
    }
}
