package pl.coderslab.Controller.AdminPanel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "AdminLogout", value = {"/adminpanel/logout/", "/adminpanel/logout"})
public class AdminLogout extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);

        if (session != null)
        {
            session.invalidate();
        }
        request.setAttribute("login_info", "<p style='color: green;'>Zostałeś wylogowany.</p>"); // mozna potem zmienic na template
        System.out.println("Wylogowałem");

        //response.setHeader("Refresh","3");
        // uruchomi /adminpanel ale przekaze mu swoj request i response (bede mogl uzyc atrybutu login_info) ale adres sie nie zmieni
        getServletContext().getRequestDispatcher("/adminpanel").forward(request, response);

        //response.sendRedirect("/adminpanel"); // powyzsze nam zalatwia sprawe
    }
}
