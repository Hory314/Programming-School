package pl.coderslab.Dao;


import pl.coderslab.Entity.Author;
import pl.coderslab.Service.DBService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AuthorDao {

    private String dbName = "katjee04_workshop";
    private String tableName = "Author";

    public List<Author> findAll(){

        String query = "Select * from "+tableName;
        try {
            List<Map<String, String>> result = DBService.executeSelectQuery(dbName, query, null);
            List<Author> authors = new ArrayList<>();

            if(result.size()>0) {

                for(Map<String,String> row : result) {

                    Author author = createAuthor(row);
                    authors.add(author);

                }

                return authors;
            }

        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }

    private Author createAuthor(Map<String, String> data) {
        Author author = new Author();
        author.setId(Integer.parseInt(data.get("id")));
        author.setName(data.get("name"));
        author.setSurname(data.get("surname"));
        return author;
    }


    public Author getById(Integer id){

        String query = "Select * from "+tableName+" where `id`=?";
        List<String> params = new ArrayList<>();
        params.add(id.toString());

        try {
            List<Map<String, String>> result = DBService.executeSelectQuery(dbName, query, params);
            if(result.size()>0) {
                Map<String, String> resultAuthor = result.get(0);

                Author author = createAuthor(resultAuthor);

                return  author;
            }

        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }

    // refactor for author

//    public void save(Book book){
//
//        if(book.getId() == null){
//            //add new book
//            add(book);
//        }else{
//            //update book
//            update(book);
//        }
//
//    }
//
//
//
//    private void add(Book book){
//
//        String query = "Insert into "+tableName+" values (null, ?, ?, ?)";
//
//        List<String> params = new ArrayList<>();
//        params.add(book.getTitle());
//        params.add(book.getIsbn());
//        params.add(book.getAuthor().getId().toString());
//
//        try {
//            Integer newId = DBService.executeInsert(dbName, query, params);
//            if(newId != null){
//                book.setId(newId);
//            }
//        }catch (SQLException e){
//            //should be logger - save info about error
//            System.out.println(e);
//        }
//
//
//    }
//
//    private void update(Book book){
//
//        String query = "Update"+tableName+" Set `author_id` = ?, `title` = ?, `isbn` = ?  where `id` = ?";
//
//        List<String> params = new ArrayList<>();
//        params.add(book.getAuthor().getId().toString());
//        params.add(book.getTitle());
//        params.add(book.getIsbn());
//        params.add(book.getId().toString());
//
//        try {
//            DBService.executeQuery(dbName, query, params);
//        }catch (SQLException e){
//            //should be logger - save info about error
//            System.out.println(e);
//        }
//
//    }
//
//    public boolean delete(Integer id){
//
//        try {
//            String query = "Delete From " + tableName + " where `id`=?";
//            List<String> params = new ArrayList<>();
//            params.add(id.toString());
//
//            DBService.executeQuery(dbName, query, params);
//            return true;
//        }catch (SQLException e){
//            return false;
//        }
//
//    }


}
