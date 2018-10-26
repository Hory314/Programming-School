package pl.coderslab.Filter;

import pl.coderslab.Entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.StringTokenizer;

@WebFilter(filterName = "UserSessionFilter", urlPatterns = {"/*"}, initParams = {
        @WebInitParam(name = "avoid-urls", value = "/login,/login/,/adminpanel/*,/css/*,/js/*,/vendor/*") // na tych adresach nie chce sprawdzac logowania, bo mi sie zapetli redirect!
})                                                                                                        // unikam tez sprawdzania statycznych tresci
public class UserSessionFilter implements Filter
{
    private ArrayList<String> urlList;

    public void destroy()
    {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException
    {
        System.err.println(LocalDateTime.now() + ": " + this.getClass().getSimpleName() + " executed.");

        HttpServletRequest request = (HttpServletRequest) req; // full request
        HttpServletResponse response = (HttpServletResponse) resp; // full response

        // sprawdz czy url z * nie jest wykluczony
        String currentPath = request.getServletPath();
        for (String uri : urlList) // do /adminpanel/*
        {
            if (uri.contains("*"))
            {
                uri = uri.substring(0, uri.indexOf("*") - 1);
                //System.out.println("+++++++++++uri: " + uri);

                if (currentPath.toLowerCase().startsWith(uri))
                {
                    System.out.println(currentPath + " zaczyna się na " + uri);
                    // zaczyna się na /adminpanel
                    chain.doFilter(req, resp); // wiec kontynuuj
                    return;
                }
            }
        }
        // sprawdz wykluczenia bez gwiazdek
        if (this.urlList.contains(request.getServletPath()))
        {
            System.err.println(request.getServletPath());
            chain.doFilter(req, resp); // jak wykluczony to nie sprawdzaj
            return; // nie wykonuj dalszej części kodu bo doFiler/forward/sendRedirect musi byc ostatni! (nie mozna przekierowac wiecej niz raz)
        }

        //////////////////////////////////////////////////////////////

        HttpSession session = request.getSession(false);

        if (session == null)
        {
            System.err.println("Pusta sesja - redirect do logowania");
            response.sendRedirect("/login"); // do logowania
            return;
        }
        else
        {
            User sessionUser = (User) session.getAttribute("session_user");
            if (sessionUser != null) // jesli cos jest w session_user to ktos jest zalogowany (jego dane sa w session_user)
            {
                System.err.println("OK zapraszmy");
                chain.doFilter(req, resp); // kontynuuj do strony
                return;
            }
        }

        System.err.println("Puste session_user - nikt nie jest zalogowany");
        response.sendRedirect("/login"); // do logowania
    }

    public void init(FilterConfig config) throws ServletException
    {
        String urls = config.getInitParameter("avoid-urls");
        StringTokenizer token = new StringTokenizer(urls, ",");

        urlList = new ArrayList<>();

        while (token.hasMoreTokens())
        {
            urlList.add(token.nextToken());
        }
    }
}
