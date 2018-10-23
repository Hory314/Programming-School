package pl.coderslab.Service;

import pl.coderslab.Entity.Exercise;
import pl.coderslab.Entity.Solution;
import pl.coderslab.Entity.User;
import pl.coderslab.Entity.UserGroup;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoService
{
    public static void setNewId(Object obj, String query, List<String> params, String dbName)
    {
        try
        {
            Integer newId = DBService.executeInsert(dbName, query, params);
            if (newId != null)
            {

                if (obj instanceof UserGroup)
                {
                    ((UserGroup) obj).setId(newId); // hmm powinno działać
                }
                else if (obj instanceof User)
                {
                    ((User) obj).setId(newId); // hmm powinno działać
                }
                else if (obj instanceof Solution)
                {
                    ((Solution) obj).setId(newId); // hmm powinno działać
                }
                else if (obj instanceof Exercise)
                {
                    ((Exercise) obj).setId(newId); // hmm powinno działać
                }
            }
        }
        catch (SQLException e)
        {
            //should be logger - save info about error
            System.out.println(e);
        }
    }

    // delete (by id)  is universal for all Daos
    public static boolean delete(Integer id, String tableName, String dbName)
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
