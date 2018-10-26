package pl.coderslab.Filter;

import pl.coderslab.Service.ServletService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.StringTokenizer;

@WebFilter(filterName = "UserSessionRefreshFillter", urlPatterns = {"/*"}, initParams = {
        @WebInitParam(name = "avoid-urls", value = "/adminpanel/*") // na tych adresach nie chce sprawdzac bo adminowi tak nie odswiezamy
})
public class UserSessionRefreshFillter implements Filter
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

       /* // sprawdz czy url z * nie jest wykluczony
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
        }*/

        //////////////////////////////////////////////////////////////
        HttpSession session = request.getSession(false);
        if (session != null)
        {
            String remember = (String) session.getAttribute("remember");
            System.out.println(session.getMaxInactiveInterval());
            if (remember != null) // jak user kazal sie zapamietac
            {
                System.out.println("Wybrales 'zapamietaj mnie'");
                // to odswiezaj mu ciasteczko jesli porusza sie po stronie
                // sesja sama sie odswiezy bo ma setMaxInactiveInterval()
                Cookie oldSessionCookie = ServletService.getCookie(request, "session_id");
                if (oldSessionCookie != null) // jak istanialo jakies stare ciastko session_id
                {
                    oldSessionCookie.setMaxAge(0); // to je wywal
                    response.addCookie(oldSessionCookie);
                }

                int sessMaxAge = 60 * 60 * 24 * 7; // 7 dni
                Cookie sessionCookie = new Cookie("session_id", session.getId());
                sessionCookie.setMaxAge(sessMaxAge);
                sessionCookie.setPath("/"); // ;_;
                response.addCookie(sessionCookie);

            }
        }

        chain.doFilter(req, resp);
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
