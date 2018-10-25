package pl.coderslab.Controller.User;

import pl.coderslab.Dao.SolutionDao;
import pl.coderslab.Dao.UserDao;
import pl.coderslab.Entity.Solution;
import pl.coderslab.Entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserDetails", urlPatterns = "/user/*")
public class UserDetails extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String id = request.getParameter("id");
        User user;
        List<Solution> solutions;

        String limit = request.getParameter("limit");
        Integer intLimit; // jak bedzie ?limit=
        try
        {
            intLimit = Integer.parseInt(limit);
        }
        catch (NumberFormatException e)
        {
            intLimit = null;
        }

        try
        {
            int intId = Integer.parseInt(id);
            UserDao userDao = new UserDao();
            SolutionDao solutionDao = new SolutionDao();

            user = userDao.getById(intId); // tez zwroci null jak takiego ID nie bedzie w bazie
            solutions = solutionDao.findAll(intLimit, null, intId);
        }
        catch (NumberFormatException e) // ID niebędące liczbą
        {
            user = null;
            solutions = null;
        }

        request.setAttribute("user", user);
        request.setAttribute("solutions", solutions);

        getServletContext().getRequestDispatcher("/WEB-INF/views/user/details.jsp").forward(request, response);
    }
}
