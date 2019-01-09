package pl.coderslab.Controller.User;

import pl.coderslab.Dao.UserDao;
import pl.coderslab.Entity.User;
import pl.coderslab.Service.ServletService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "UserLogin", value = {"/login", "/login/"})
public class UserLogin extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String postEmail = request.getParameter("email");
        String postPassword = request.getParameter("password");

        User postUser = new User();
        postUser.setEmail(postEmail);
        postUser.setPasswordNoHashing(postPassword);

        UserDao userDao = new UserDao();
        User dbUser = userDao.getByEmail(postEmail);

        System.out.println(dbUser);
        System.out.println(postUser);
        if (postUser.checkPwAndEmail(dbUser)) // haslo i mail ok
        {
            System.out.println("haslo i mail ok");
            HttpSession oldSession = request.getSession(false); // wez starą sesje (nie tworznowej jak nie ma)
            if (oldSession != null)
            {
                oldSession.invalidate(); // skasuj starą jak była
            }

            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("session_user", dbUser); //dbUser bo on ma pełne dane uzupełnone
            if (request.getParameter("remember") == null)
            {
                System.out.println("jest null - krotka sesja");
                newSession.setMaxInactiveInterval(60*30); // 30 min lub do zamkniecia przegladarki // 5 sekund do testow
                //ustawiam tylko czas "nieaktywnosci" sesji - ciasteczko zyje do zamkniecia przegladarki czyli sesja tez
            }
            else
            {
                System.out.println("nie jest null, dluga sesja");

                Cookie oldSessionCookie = ServletService.getCookie(request, "session_id");
                if (oldSessionCookie != null) // jak istanialo jakies stare ciastko session_id
                {
                    oldSessionCookie.setMaxAge(0); // to je wywal
                    response.addCookie(oldSessionCookie);
                }

                int sessMaxAge = 60 * 60 * 24 * 7; // 7 dni
                Cookie sessionCookie = new Cookie("session_id", newSession.getId());
                sessionCookie.setMaxAge(sessMaxAge);
                response.addCookie(sessionCookie);

                newSession.setMaxInactiveInterval(sessMaxAge);

                newSession.setAttribute("remember", "true"); // do wykorzystania w filtrze odswiezania sesji
            }
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/solutions")); // logowanie ok, wiec przekierowujemy na cos
        }
        else // nie ok
        {
            request.setAttribute("login_info", "<p style='color: red;'>Zły login/hasło.</p>"); // mozna potem zmienic na template

            doGet(request, response); // jak haslo sie nie zgadza to wyswietl logowanie

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // jako iz filtr omija ten servlet (bo inaczej zapetli sie redirect)
        // musze obsluzyc recznie co zrobic jesli ktos jest zalogowany
        HttpSession session = request.getSession(false);

        if (session != null)
        {
            User sessionUser = (User) session.getAttribute("session_user");
            if (sessionUser != null) // jak cos jest, to ktos zalogowany
            {
                System.err.println("Ktos ma sesje OK");
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/solutions"));
                return;
            }
        }

        request.setAttribute("page_title", "Logowanie");
        getServletContext().getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
    }
}
