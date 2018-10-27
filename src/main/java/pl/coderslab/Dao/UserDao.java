package pl.coderslab.Dao;

import pl.coderslab.Entity.User;
import pl.coderslab.Entity.UserGroup;
import pl.coderslab.Service.DBService;
import pl.coderslab.Service.DaoService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDao
{
    private String dbName = "programming_school";
    private String tableName = "users";

    public List<User> findAll()
    {
        return findAll(null, null);
    }

    public List<User> findAll(Integer limit, Integer userGroupId)
    {
        String addLimitInQuery = "";
        if (limit != null && limit > 0)
        {
            addLimitInQuery = " LIMIT " + limit;
        }

        String addWhereInQuery = "";
        if (userGroupId != null && userGroupId > 0)
        {
            addWhereInQuery = " WHERE `user_group_id` = " + userGroupId;
        }

        String query = "SELECT * FROM " + tableName + addWhereInQuery + " ORDER BY `username` ASC" + addLimitInQuery;
        try
        {
            List<Map<String, String>> result = DBService.executeSelectQuery(dbName, query, null);
            List<User> users = new ArrayList<>();

            if (result.size() > 0)
            {
                for (Map<String, String> row : result)
                {

                    User user = createUser(row);
                    users.add(user);
                }
                return users;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }

    public User getById(Integer id)
    {
        String query = "Select * from " + tableName + " where `id`=?";
        List<String> params = new ArrayList<>();
        params.add(id.toString());

        try
        {
            List<Map<String, String>> result = DBService.executeSelectQuery(dbName, query, params);
            if (result.size() > 0)
            {
                Map<String, String> resultUser = result.get(0);

                User user = createUser(resultUser);

                return user;
            }

        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }

    public User getByEmail(String email)
    {
        String query = "SELECT * FROM " + tableName + " WHERE `email` = ?";
        List<String> params = new ArrayList<>();
        params.add(email);

        try
        {
            List<Map<String, String>> result = DBService.executeSelectQuery(dbName, query, params);
            if (result.size() > 0)
            {
                Map<String, String> resultUser = result.get(0);

                User user = createUser(resultUser);

                return user;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private User createUser(Map<String, String> row)
    {
        User user = new User();

        user.setId(Integer.parseInt(row.get("id")));
        user.setUsername(row.get("username"));
        user.setEmail(row.get("email"));
        user.setPasswordNoHashing(row.get("password")); // nie hashujemy bo haslo jest juz pobrane z bazy czyli juz zahashowane

        UserGroup userGroup;
        try
        {
            UserGroupDao userGroupDao = new UserGroupDao();
            userGroup = userGroupDao.getById(Integer.parseInt(row.get("user_group_id")));
        }
        catch (NumberFormatException e)
        {
            userGroup = null;
        }
        user.setUserGroup(userGroup);

        return user;
    }

    public void save(User user)
    {
        if (user.getId() == null)
        {
            //add new userGroup
            add(user);
        }
        else
        {
            //update userGroup
            update(user);
        }
    }

    private void add(User user)
    {

        String query = "Insert into " + tableName + " values (null, ?, ?, ?, ?)"; // pamietac o kolejnosci dodawania do listy params

        List<String> params = new ArrayList<>();
        params.add(user.getUsername());
        params.add(user.getEmail());
        params.add(user.getPassword()); // powinno byc juz zahashowane
        params.add(user.getUserGroup().getId().toString());
        DaoService.setNewId(user, query, params, dbName);
        // jak powyzsze nie zadziala to uzyc:
//        try
//        {
//            Integer newId = DBService.executeInsert(dbName, query, params);
//            if (newId != null)
//            {
//                book.setId(newId);
//            }
//        }
//        catch (SQLException e)
//        {
//            //should be logger - save info about error
//            System.out.println(e);
//        }
    }

    private void update(User user)
    {

        String query = "Update " + tableName + " Set `username` = ?, `email` = ?, `password` = ?, `user_group_id` = ? where `id` = ?";

        List<String> params = new ArrayList<>();
        params.add(user.getUsername());
        params.add(user.getEmail());
        params.add(user.getPassword()); // powinno byc juz zahashowane
        params.add(user.getUserGroup().getId().toString());
        params.add(user.getId().toString());

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
        return DaoService.delete(id, tableName, dbName);

        //jak powyzsze nie zadziala to uzyc:
//        try
//        {
//            String query = "Delete From " + tableName + " where `id`=?";
//            List<String> params = new ArrayList<>();
//            params.add(id.toString());
//
//            DBService.executeQuery(dbName, query, params);
//            return true;
//        }
//        catch (SQLException e)
//        {
//            return false;
//        }
    }
}
