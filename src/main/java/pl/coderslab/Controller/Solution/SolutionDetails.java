package pl.coderslab.Controller.Solution;

import pl.coderslab.Dao.SolutionDao;
import pl.coderslab.Entity.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SolutionDetails", urlPatterns = {"/solution", "/solution/"})
public class SolutionDetails extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        SolutionDao solutionDao = new SolutionDao();
        Solution solution;

        String id = request.getParameter("id");

        try
        {
            Integer intId = Integer.parseInt(id);
            solution = solutionDao.getById(intId);
        }
        catch (NumberFormatException e)
        {
            solution = null;
        }

        request.setAttribute("solution", solution);
        getServletContext().getRequestDispatcher("/WEB-INF/views/solution/details.jsp").forward(request, response);
    }
}
