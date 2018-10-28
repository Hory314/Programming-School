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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "SolutionEdit", urlPatterns = {"/solutions/edit", "/solutions/edit/"})
public class SolutionEdit extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        Solution solution = (Solution) session.getAttribute("solution");
        session.removeAttribute("solution"); // usuwam w razie W bo juz niepotrzebne

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        solution.setDescription(request.getParameter("solution")); // udpdate treści z formualarza
        solution.setUpdated(now.format(formatter));

        SolutionDao solutionDao = new SolutionDao();
        solutionDao.save(solution); // update do bazy

        User user = (User) session.getAttribute("session_user");
        List<Solution> solutions = solutionDao.findAll(null, null, user.getId()); // do powrotu do user settings
        request.setAttribute("solutions", solutions); // jw
        request.setAttribute("table_info", "<span style='color: green;'>Pomyślnie zmodyfikowano.</span>");
        getServletContext().getRequestDispatcher("/WEB-INF/views/user/settings.jsp").forward(request, response); // daj widok ustawień
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);

        String id = request.getParameter("id");
        Solution solution;
        try
        {
            Integer intId = Integer.parseInt(id);
            SolutionDao solutionDao = new SolutionDao();
            solution = solutionDao.getById(intId);

            //sprawdz czy user ma uprawnienia do edycji (czyli do jego rozwiazanie)
            User user = (User) session.getAttribute("session_user");
            if (!(solution.getUser().getId().equals(user.getId())))
            {
                solution = null; // nie daję obiektu rozwiązania , bo ten user co jest w sesji nie dodał tego rozwiązania
            }
        }
        catch (NumberFormatException | NullPointerException e) // jak błędne ?id podane
        {
            // złapie jak ?id nie jest liczbą (NFE) lub id nie istnieje (przy porównaniu if będzie NPE)
            solution = null;
        }

        request.setAttribute("solution", solution);
        session.setAttribute("solution", solution);
        getServletContext().getRequestDispatcher("/WEB-INF/views/solution/edit.jsp").forward(request, response);
    }
}
