package pl.coderslab.Controller.Solution;

import pl.coderslab.Dao.SolutionDao;
import pl.coderslab.Entity.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SolutionList", value = {"/solutions/", "/solutions", "/solution/list", "/solution/list/"})
public class SolutionList extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        SolutionDao solutionDao = new SolutionDao();
        List<Solution> solutions;

        String limit = request.getParameter("limit");

        try
        {
            int intLimit = Integer.parseInt(limit);
            solutions = solutionDao.findAll(intLimit, null, null);
        }
        catch (NumberFormatException | NullPointerException e)
        {
            solutions = solutionDao.findAll();
        }

        request.setAttribute("solutions", solutions);
        getServletContext().getRequestDispatcher("/WEB-INF/views/solution/list.jsp").forward(request, response);
    }
}
