package pl.coderslab.Controller.UserGroup;

import pl.coderslab.Dao.UserDao;
import pl.coderslab.Dao.UserGroupDao;
import pl.coderslab.Entity.User;
import pl.coderslab.Entity.UserGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserGroupDetails", urlPatterns = {"/group/*"})
public class UserGroupDetails extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String id = request.getParameter("id");
        List<User> users;
        UserGroup userGroup;

        try
        {
            Integer intId = Integer.parseInt(id);
            UserDao userDao = new UserDao();
            UserGroupDao userGroupDao = new UserGroupDao();

            users = userDao.findAll(null, intId);
            userGroup = userGroupDao.getById(intId);
        }
        catch (NumberFormatException e)
        {
            users = null;
            userGroup = null;
        }

        request.setAttribute("users", users);
        request.setAttribute("userGroup", userGroup);
        getServletContext().getRequestDispatcher("/WEB-INF/views/usergroup/details.jsp").forward(request, response);
    }
}
