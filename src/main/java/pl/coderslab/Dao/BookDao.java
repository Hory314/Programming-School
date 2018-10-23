package pl.coderslab.Dao;

import pl.coderslab.Entity.Author;
import pl.coderslab.Entity.Book;
import pl.coderslab.Service.DBService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookDao
{

    private String dbName = "katjee04_workshop";
    private String tableName = "Book";

    public List<Book> findAll()
    {

        String query = "Select * from " + tableName;
        try
        {
            List<Map<String, String>> result = DBService.executeSelectQuery(dbName, query, null);
            List<Book> books = new ArrayList<>();

            if (result.size() > 0)
            {

                for (Map<String, String> row : result)
                {

                    Book book = createBook(row);
                    books.add(book);
                }
                return books;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }

    private Book createBook(Map<String, String> data)
    {
        AuthorDao authorDao = new AuthorDao();
        Author author = authorDao.getById(Integer.parseInt(data.get("author_id")));

        Book book = new Book();
        book.setId(Integer.parseInt(data.get("id")));
        book.setTitle(data.get("title"));
        book.setAuthor(author);
        book.setIsbn(data.get("isbn"));
        return book;
    }

    public Book getById(Integer id)
    {

        String query = "Select * from " + tableName + " where `id`=?";
        List<String> params = new ArrayList<>();
        params.add(id.toString());

        try
        {
            List<Map<String, String>> result = DBService.executeSelectQuery(dbName, query, params);
            if (result.size() > 0)
            {
                Map<String, String> resultBook = result.get(0);

                Book book = createBook(resultBook);

                return book;
            }

        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }

    public void save(Book book)
    {

        if (book.getId() == null)
        {
            //add new book
            add(book);
        }
        else
        {
            //update book
            update(book);
        }

    }

    private void add(Book book)
    {

        String query = "Insert into " + tableName + " values (null, ?, ?, ?)"; // pamietac o kolejnosci dodawanie do listy params

        List<String> params = new ArrayList<>();
        params.add(book.getTitle());
        params.add(book.getIsbn());
        params.add(book.getAuthor().getId().toString());

        try
        {
            Integer newId = DBService.executeInsert(dbName, query, params);
            if (newId != null)
            {
                book.setId(newId);
            }
        }
        catch (SQLException e)
        {
            //should be logger - save info about error
            System.out.println(e);
        }

    }

    private void update(Book book)
    {

        String query = "Update " + tableName + " Set `author_id` = ?, `title` = ?, `isbn` = ?  where `id` = ?";

        List<String> params = new ArrayList<>();
        params.add(book.getAuthor().getId().toString());
        params.add(book.getTitle());
        params.add(book.getIsbn());
        params.add(book.getId().toString());

        try
        {
            DBService.executeQuery(dbName, query, params);
        }
        catch (SQLException e)
        {
            //should be logger - save info about error
            System.out.println(e);
        }

    }

    public boolean delete(Integer id)
    {

        try
        {
            String query = "Delete From " + tableName + " where `id`=?";
            List<String> params = new ArrayList<>();
            params.add(id.toString());

            DBService.executeQuery(dbName, query, params);
            return true;
        }
        catch (SQLException e)
        {
            return false;
        }

    }

}
