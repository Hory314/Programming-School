package pl.coderslab.Controller.Exercise;

import pl.coderslab.Dao.ExerciseDao;
import pl.coderslab.Dao.SolutionDao;
import pl.coderslab.Entity.Exercise;
import pl.coderslab.Entity.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ExerciseDetails", urlPatterns = {"/exercise/*"})
public class ExerciseDetails extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String id = request.getParameter("id");
        Exercise exercise;
        List<Solution> solutions;

        try
        {
            Integer intId = Integer.parseInt(id);
            ExerciseDao exerciseDao = new ExerciseDao();
            SolutionDao solutionDao = new SolutionDao();

            exercise = exerciseDao.getById(intId);
            solutions = solutionDao.findAll(null, intId, null);
        }
        catch (NumberFormatException e)
        {
            exercise = null;
            solutions = null;
        }

        request.setAttribute("exercise", exercise);
        request.setAttribute("solutions", solutions);
        getServletContext().getRequestDispatcher("/WEB-INF/views/exercise/details.jsp").forward(request, response);
    }
}
