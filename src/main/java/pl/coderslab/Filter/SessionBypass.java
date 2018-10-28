package pl.coderslab.Filter;

import pl.coderslab.Dao.UserDao;
import pl.coderslab.Entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "SessionBypass", urlPatterns = {"/*"}) // delete `, urlPatterns = {"/*"}` after testing
public class SessionBypass implements Filter
{
    public void destroy()
    {
    }

    /**
     * DUMMY SESSION FOR TESTING
     * Comment doFilter method after testing or delete this class
     * @param req
     * @param resp
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException
    {
        System.err.println("*** SESSION IS BYPASSED! ***");

        HttpServletRequest request = (HttpServletRequest) req; // full request

        UserDao userDao = new UserDao();
        User dbUser = userDao.getByEmail("hory314@o2.pl");

        HttpSession newSession = request.getSession(true);
        newSession.setAttribute("session_user", dbUser); //dbUser bo on ma pełne dane uzupełnone
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException
    {

    }

}
