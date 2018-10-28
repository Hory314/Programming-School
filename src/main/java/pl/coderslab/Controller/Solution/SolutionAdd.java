package pl.coderslab.Controller.Solution;

import pl.coderslab.Dao.ExerciseDao;
import pl.coderslab.Dao.SolutionDao;
import pl.coderslab.Entity.Exercise;
import pl.coderslab.Entity.Solution;
import pl.coderslab.Entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "SolutionAdd", urlPatterns = {"/solutions/add", "/solutions/add/"})
public class SolutionAdd extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if (session != null)
        {
            User user = (User) session.getAttribute("session_user");
            if (user == null) // cos jest nie tak
            {
                session.invalidate();
                response.sendRedirect("/login");
                return;
            }
            else
            {
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                String exercise_id = request.getParameter("exercise_id");
                Integer intExercise_id;
                try
                {
                    intExercise_id = Integer.parseInt(exercise_id);
                    ExerciseDao exerciseDao = new ExerciseDao();

                    if (exerciseDao.getById(intExercise_id) == null)
                    {
                        addFailed(request, response);
                        return;
                    }
                }
                catch (NumberFormatException e)
                {
                    addFailed(request, response);
                    return;
                }

                Exercise exercise = new Exercise();
                exercise.setId(intExercise_id);

                Solution solution = new Solution();
                solution.setCreated(now.format(formatter));
                solution.setDescription(request.getParameter("solution"));
                solution.setExercise(exercise);
                solution.setUser(user);

                SolutionDao solutionDao = new SolutionDao();
                solutionDao.save(solution);

                request.setAttribute("form_info", "<p style='color: green;'>Dodano rowiązanie.</p>");
            }
        }
        else
        {
            addFailed(request, response);
            return;
        }

        doGet(request, response);
    }

    private void addFailed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("form_info", "<p style='color: red;'>Dodanie rozwiązania nie powiodło się.</p>");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ExerciseDao exerciseDao = new ExerciseDao();
        List<Exercise> exercises = exerciseDao.findAll();

        request.setAttribute("exercises", exercises);
        getServletContext().getRequestDispatcher("/WEB-INF/views/solution/add.jsp").forward(request, response);
    }
}
