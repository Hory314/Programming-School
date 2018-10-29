package pl.coderslab.Controller.Solution;

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

@WebServlet(name = "SolutionDelete", urlPatterns = {"/solutions/delete", "/solutions/delete/"})
public class SolutionDelete extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        SolutionDao solutionDao = new SolutionDao();

        String id = request.getParameter("id");
        Solution solution;
        try
        {
            Integer intId = Integer.parseInt(id);
            solution = solutionDao.getById(intId);

            //sprawdz czy user ma uprawnienia do edycji (czyli do jego rozwiazanie)
            User user = (User) session.getAttribute("session_user");
            if (!(solution.getUser().getId().equals(user.getId())))
            {
                solution = null; // nie daję obiektu rozwiązania , bo ten user co jest w sesji nie dodał tego rozwiązania
            }
            solutionDao.delete(solution.getId()); // usuwam
            request.setAttribute("table_info", "<span style='color: green;'>Pomyślnie usunięto.</span>");
        }
        catch (NumberFormatException | NullPointerException e) // jak błędne ?id podane
        {
            // złapie jak ?id nie jest liczbą (NFE) lub id nie istnieje (przy porównaniu if będzie NPE)
            //solution = null;
            request.setAttribute("table_info", "<span style='color: red;'>Rozwiązanie nie istnieje lub nie masz uprawnień do jego usunięcia.</span>");
        }

        User user = (User) session.getAttribute("session_user");
        List<Solution> solutions = solutionDao.findAll(null, null, user.getId()); // do powrotu do user settings
        request.setAttribute("solutions", solutions); // jw

        getServletContext().getRequestDispatcher("/WEB-INF/views/user/settings.jsp").forward(request, response); // daj widok ustawień
    }
}
