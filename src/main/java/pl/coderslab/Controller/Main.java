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
        response.sendRedirect(response.encodeRedirectURL("/login")); // przekierowanie do loginu, a login juz se sprawdzi co trzeba
    }
}
