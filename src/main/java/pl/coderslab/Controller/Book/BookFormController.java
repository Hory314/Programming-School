package pl.coderslab.Controller.Book;

import pl.coderslab.Dao.AuthorDao;
import pl.coderslab.Dao.BookDao;
import pl.coderslab.Entity.Author;
import pl.coderslab.Entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookFormController", urlPatterns = {"/book/form"})
public class BookFormController extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        String title = request.getParameter("title");
        String authorId = request.getParameter("author_id");
        String isbn = request.getParameter("isbn");

        AuthorDao authorDao = new AuthorDao();
        Author author = authorDao.getById(Integer.parseInt(authorId));

        Book book = new Book(author, title, isbn);

        BookDao bookDao = new BookDao();
        bookDao.save(book);

        response.sendRedirect("/book/details?id=" + book.getId());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        AuthorDao authorDao = new AuthorDao();
        List<Author> authorList = authorDao.findAll();

        request.setAttribute("authors", authorList);

        getServletContext().getRequestDispatcher("/WEB-INF/views/book/form.jsp")
                .forward(request, response);
    }
}
