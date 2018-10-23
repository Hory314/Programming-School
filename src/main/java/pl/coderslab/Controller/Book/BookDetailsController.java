package pl.coderslab.Controller.Book;

import pl.coderslab.Dao.BookDao;
import pl.coderslab.Entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BookDetailsController", urlPatterns = {"/book/details"})
public class BookDetailsController extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        Book book = null;

        try
        {
            Integer id = Integer.parseInt(request.getParameter("id"));
            BookDao bookDao = new BookDao();
            book = bookDao.getById(id);

        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        request.setAttribute("book", book);

        getServletContext().getRequestDispatcher("/WEB-INF/views/book/details.jsp")
                .forward(request, response);

    }
}
