package pl.coderslab.Dao;

import pl.coderslab.Entity.UserGroup;
import pl.coderslab.Service.DBService;
import pl.coderslab.Service.DaoService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserGroupDao
{
    private String dbName = "programming_school";
    private String tableName = "user_group";

    public List<UserGroup> findAll()
    {
        String query = "Select * from " + tableName;
        try
        {
            List<Map<String, String>> result = DBService.executeSelectQuery(dbName, query, null);
            List<UserGroup> userGroups = new ArrayList<>();

            if (result.size() > 0)
            {
                for (Map<String, String> row : result)
                {

                    UserGroup userGroup = createUserGroup(row);
                    userGroups.add(userGroup);
                }
                return userGroups;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }

    public UserGroup getById(Integer id)
    {
        String query = "Select * from " + tableName + " where `id`=?";
        List<String> params = new ArrayList<>();
        params.add(id.toString());

        try
        {
            List<Map<String, String>> result = DBService.executeSelectQuery(dbName, query, params);
            if (result.size() > 0)
            {
                Map<String, String> resultUserGroup = result.get(0);

                UserGroup userGroup = createUserGroup(resultUserGroup);

                return userGroup;
            }

        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }

    private UserGroup createUserGroup(Map<String, String> row)
    {
        UserGroup userGroup = new UserGroup();
        userGroup.setId(Integer.parseInt(row.get("id")));
        userGroup.setName(row.get("name"));

        return userGroup;
    }

    public void save(UserGroup userGroup)
    {
        if (userGroup.getId() == null)
        {
            //add new userGroup
            add(userGroup);
        }
        else
        {
            //update userGroup
            update(userGroup);
        }
    }

    private void add(UserGroup userGroup)
    {

        String query = "Insert into " + tableName + " values (null, ?)"; // pamietac o kolejnosci dodawanie do listy params

        List<String> params = new ArrayList<>();
        params.add(userGroup.getName());
        DaoService.setNewId(userGroup, query, params, dbName);
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

    private void update(UserGroup userGroup)
    {

        String query = "Update " + tableName + " Set `name` = ? where `id` = ?";

        List<String> params = new ArrayList<>();
        params.add(userGroup.getName());
        params.add(userGroup.getId().toString());

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
