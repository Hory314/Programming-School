package pl.coderslab.Controller.AdminPanel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AdminLogin", urlPatterns = {"/adminpanel", "/adminpanel/"})
public class AdminLogin extends HttpServlet
{
    private final String adminName = "admin";
    private final String adminPass = "coderslab";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login.equals(this.adminName) && password.equals(this.adminPass))
        {
            // jak sie zgadza to uniewaznij stara sesje
            HttpSession oldSession = request.getSession(false); // false - jak nie ma zadnej sesji to nie tworz tylko zwroc null
            if (oldSession != null) // jak byla jakas stara sesja
            {
                oldSession.invalidate(); // to ją unieważnij
            }

            HttpSession newSession = request.getSession(true); // true - stworz jak nie ma (a nie ma)
            newSession.setAttribute("admin_name", this.adminName);
            newSession.setAttribute("admin_pass", this.adminPass);
            if(request.getParameter("remember") == null)
            {
                System.out.println("jest null - krotka sesja");
                newSession.setMaxInactiveInterval(5); // 5 sekund do testow
            }
            else
            {
                System.out.println("nie jest null - dluga sesja");
                newSession.setMaxInactiveInterval(60 * 60 * 24 * 7); // 1 tydzień
            }


            response.sendRedirect("/adminpanel/manage/groups"); // logowanie ok, wiec przekierowujemy na cos
        }
        else
        {
            request.setAttribute("bad_pass","<p style='color: red;'>Zły login/hasło</p>"); // mozna potem zmienic na template

            doGet(request, response); // jak haslo sie nie zgadza to wyswietl logowanie
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // mozna zrobic ze jesli ma sesje ok to przekieruje gdzies indziej
        HttpSession session = request.getSession(false); // false - nie twórz nowej jak nie istniteje sesja (zwroci null jak nie instnieje)

        if (session != null) // jak niepusta sesja...
        {
            String sessionAdminName = (String) session.getAttribute("admin_name");
            String sessionAdminPass = (String) session.getAttribute("admin_pass");
            if (sessionAdminName != null && sessionAdminPass != null) // i user/haslo ustawione...
            {
                if (sessionAdminName.equals(this.adminName) && sessionAdminPass.equals(this.adminPass)) // i sie zgadza...
                { // jak hasla sa w sesji
                    System.err.println("OK zapraszamy");
                    response.sendRedirect("/adminpanel/manage/groups"); // to user zalogowany juz - przekieruj
                    return; // return zeby sie forward() nie wykonal bo bedzie error
                }
            }
        } // musze tak zrobic, bo filter nie obejmuje tego servletu (wykluczylem go)
        request.setAttribute("page_title", "Logowanie admina");
        getServletContext().getRequestDispatcher("/WEB-INF/views/adminpanel/login.jsp").forward(request, response);
    }
}
