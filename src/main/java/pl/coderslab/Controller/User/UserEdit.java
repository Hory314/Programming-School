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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserEdit", urlPatterns = {"/settings/edit", "/settings/edit/"})
public class UserEdit extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("session_user");

        String color = "green";
        String userInfo = "Zmiany";

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String newPassword = request.getParameter("new-password");
        String newPassword2 = request.getParameter("new-password2");
        String currentPassword = request.getParameter("current-password");
        User tempUser = new User();
        tempUser.setPasswordNoHashing(currentPassword);

        SolutionDao solutionDao = new SolutionDao();// do powrotu do user settings
        List<Solution> solutions = solutionDao.findAll(null, null, user.getId()); // jw
        request.setAttribute("solutions", solutions); // jw

        if (tempUser.checkPw(user)) // jak obecne haslo jest ok
        {
            System.out.println("hasla ok");
            user.setUsername(username);
            user.setEmail(email);
            if (!newPassword.equals("")) // jak nowe haslo niepuste, to user chce zmienic haslo
            {
                if (newPassword.equals(newPassword2)) // jak 2 hasla sa takie same
                {
                    user.setHashedPassword(newPassword);
                }
                else
                {
                    // tylko nowe haslo nieustawione
                    System.out.println("Hasła nie zgadzają się. Hasło nie zostało zmienione. Inne dane zostały zmienione.");
                    //request.setAttribute("user_edit_info", "<p style='color: yellow;'>Hasło nie zostało zmienione - hasła nie zgadzają się. Obecne hasło nie zgadza się z podanym.</p>");
                    userInfo = "Hasło nie zostało zmienione - hasła nie zgadzają się. Pozostałe zmiany";
                    color = "#ff6c00";
                }
            } // nie musze dawac else bo user ma juz zahashowane haslo w obiekcie

            UserDao userDao = new UserDao();
            userDao.save(user); // update
            //session.getAttribute("session_user"); //nie trzeba

            // ok
            userInfo += " zapisano pomyślnie.";
            request.setAttribute("user_edit_info", "<p style='color: " + color + ";'>" + userInfo + "</p>");
            getServletContext().getRequestDispatcher("/WEB-INF/views/user/settings.jsp").forward(request, response);
        }
        else
        {
            // zle obecne haslo
            request.setAttribute("user_edit_info", "<p style='color: red;'>Nie dokonano żanych zmian. Obecne hasło nie zgadza się z podanym.</p>");
            System.out.println("Obecne hasło nieprawidłowe");
            getServletContext().getRequestDispatcher("/WEB-INF/views/user/settings.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // tylko widok formularza
        getServletContext().getRequestDispatcher("/WEB-INF/views/user/edit.jsp").forward(request, response);
    }
}
