package pl.coderslab.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Main", value = {""})
// "" puste czyli domena.com i domena.com/ bedzie kierowac do tego servletu
// jak dopisze jeszcze "/" to tak samo, ale wszystkie strony, które nie mają servletu zamiast wyswietlic 404 tez przekierują
// do tego servletu
// nie wiem czy ma znacznie ale w web.xml welcome-file ustawilem na `/`
public class Main extends HttpServlet
{
    private final String adminName = "admin";
    private final String adminPass = "coderslab";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
       /* String loginInfo = (String) request.getAttribute("login_info");
        System.out.println("loginInfo: " + loginInfo + ".");
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
                    response.sendRedirect(response.encodeRedirectURL("/adminpanel/manage/groups")); // to user zalogowany juz - przekieruj
                    return; // return zeby sie forward() nie wykonal bo bedzie error
                }
            }
        } // musze tak zrobic, bo filter nie obejmuje tego servletu (wykluczylem go)*/

        // request.setAttribute("page_title", "Logowanie admina");
        //getServletContext().getRequestDispatcher("/WEB-INF/views/adminpanel/login.jsp").forward(request, response);

        //powyzej zrobic zeby sprawdzalo czy user juz zalogowany i cos mu wyswietlac
        response.sendRedirect("/login");
    }
}
