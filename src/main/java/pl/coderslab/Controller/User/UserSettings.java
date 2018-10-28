package pl.coderslab.Controller.User;

import pl.coderslab.Dao.SolutionDao;
import pl.coderslab.Entity.Solution;
import pl.coderslab.Entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserSettings", urlPatterns = {"/settings", "/settings/"})
public class UserSettings extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("session_user");

        SolutionDao solutionDao = new SolutionDao();
        List<Solution> solutions = solutionDao.findAll(null, null, user.getId());


        request.setAttribute("solutions", solutions);
        getServletContext().getRequestDispatcher("/WEB-INF/views/user/settings.jsp").forward(request, response);
    }
}
