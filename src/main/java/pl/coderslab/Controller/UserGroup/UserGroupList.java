package pl.coderslab.Controller.UserGroup;

import pl.coderslab.Dao.UserGroupDao;
import pl.coderslab.Entity.UserGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserGroupList", urlPatterns = {"/groups", "/groups/", "/group/list", "/group/list/"})
public class UserGroupList extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        UserGroupDao userGroupDao = new UserGroupDao();
        List<UserGroup> userGroups = userGroupDao.findAll();

        /*
        // todo użyć potem
        //select user_group_id,count(*) as members_count from users group by user_group_id
         */

        request.setAttribute("userGroups", userGroups);
        getServletContext().getRequestDispatcher("/WEB-INF/views/usergroup/list.jsp").forward(request, response);
    }
}
