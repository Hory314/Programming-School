package pl.coderslab.Dao;

import pl.coderslab.Entity.Exercise;
import pl.coderslab.Entity.Solution;
import pl.coderslab.Entity.User;
import pl.coderslab.Service.DBService;
import pl.coderslab.Service.DaoService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SolutionDao
{
    private String dbName = "programming_school";
    private String tableName = "solution";

    public List<Solution> findAll()
    {
        return findAll(null, null, null);
    }

    public List<Solution> findAll(Integer limit, Integer exerciseId, Integer userId)
    {
        String addLimitInQuery = "";
        if (limit != null && limit > 0)
        {
            addLimitInQuery = " LIMIT " + limit;
        }

        String addWhereInQuery = "";
        if (userId != null && userId > 0)
        {
            addWhereInQuery = " WHERE `users_id` = " + userId;
        }

        if (exerciseId != null && exerciseId > 0)
        {
            addWhereInQuery = " WHERE `exercise_id` = " + exerciseId;
        }

        String query = "SELECT * FROM " + tableName + addWhereInQuery + " ORDER BY `created` DESC" + addLimitInQuery;
        System.out.println(query); // test
        try
        {
            List<Map<String, String>> result = DBService.executeSelectQuery(dbName, query, null);
            List<Solution> solutions = new ArrayList<>();

            if (result.size() > 0)
            {
                for (Map<String, String> row : result)
                {

                    Solution solution = createSolution(row);
                    solutions.add(solution);
                }
                return solutions;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }

    public Solution getById(Integer id)
    {
        String query = "Select * from " + tableName + " where `id`=?";
        List<String> params = new ArrayList<>();
        params.add(id.toString());

        try
        {
            List<Map<String, String>> result = DBService.executeSelectQuery(dbName, query, params);
            if (result.size() > 0)
            {
                Map<String, String> resultSolution = result.get(0);

                Solution solution = createSolution(resultSolution);

                return solution;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }

    private Solution createSolution(Map<String, String> row)
    {
        Solution solution = new Solution();

        solution.setId(Integer.parseInt(row.get("id")));
        solution.setCreated(row.get("created"));
        solution.setUpdated(row.get("updated"));
        solution.setDescription(row.get("description"));

        ExerciseDao exerciseDao = new ExerciseDao();
        Exercise exercise = exerciseDao.getById(Integer.parseInt(row.get("exercise_id")));
        solution.setExercise(exercise);

        UserDao userDao = new UserDao();
        User user = userDao.getById(Integer.parseInt(row.get("users_id")));
        solution.setUser(user);

        return solution;
    }

    public void save(Solution solution)
    {
        if (solution.getId() == null)
        {
            //add new userGroup
            add(solution);
        }
        else
        {
            //update userGroup
            update(solution);
        }
    }

    private void add(Solution solution)
    {

        String query = "Insert into " + tableName + " values (null, ?, ?, ?, ?, ?)"; // pamietac o kolejnosci dodawania do listy params

        List<String> params = new ArrayList<>();
        params.add(solution.getCreated());
        params.add(solution.getUpdated());
        params.add(solution.getDescription());
        params.add(solution.getExercise().getId().toString());
        params.add(solution.getUser().getId().toString());
        DaoService.setNewId(solution, query, params, dbName);
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

    private void update(Solution solution)
    {

        String query = "Update " + tableName + " Set `created` = ?, `updated` = ?, `description` = ?, `exercise_id` = ?, `users_id` = ? where `id` = ?";

        List<String> params = new ArrayList<>();
        params.add(solution.getCreated());
        params.add(solution.getUpdated());
        params.add(solution.getDescription());
        params.add(solution.getExercise().getId().toString());
        params.add(solution.getUser().getId().toString());
        params.add(solution.getId().toString());

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
