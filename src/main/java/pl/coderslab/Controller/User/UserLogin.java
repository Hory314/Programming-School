package pl.coderslab.Controller.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserLogin", value = {"/login", "/login/"})
public class UserLogin extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //todo
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("page_title", "Logowanie");
        getServletContext().getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
    }
}
