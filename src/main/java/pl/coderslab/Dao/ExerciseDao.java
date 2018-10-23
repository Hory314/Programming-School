package pl.coderslab.Dao;

import pl.coderslab.Entity.Exercise;
import pl.coderslab.Service.DBService;
import pl.coderslab.Service.DaoService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExerciseDao
{
    private String dbName = "programming_school";
    private String tableName = "exercise";

    public List<Exercise> findAll()
    {

        String query = "Select * from " + tableName;
        try
        {
            List<Map<String, String>> result = DBService.executeSelectQuery(dbName, query, null);
            List<Exercise> exercises = new ArrayList<>();

            if (result.size() > 0)
            {
                for (Map<String, String> row : result)
                {

                    Exercise exercise = createExercise(row);
                    exercises.add(exercise);
                }
                return exercises;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }

    public Exercise getById(Integer id)
    {
        String query = "Select * from " + tableName + " where `id`=?";
        List<String> params = new ArrayList<>();
        params.add(id.toString());

        try
        {
            List<Map<String, String>> result = DBService.executeSelectQuery(dbName, query, params);
            if (result.size() > 0)
            {
                Map<String, String> resultExercise = result.get(0);

                Exercise exercise = createExercise(resultExercise);

                return exercise;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }

    private Exercise createExercise(Map<String, String> row)
    {
        Exercise exercise = new Exercise();

        exercise.setId(Integer.parseInt(row.get("id")));
        exercise.setTitle(row.get("title"));
        exercise.setDescription(row.get("description"));

        return exercise;
    }

    public void save(Exercise exercise)
    {
        if (exercise.getId() == null)
        {
            //add new userGroup
            add(exercise);
        }
        else
        {
            //update userGroup
            update(exercise);
        }
    }

    private void add(Exercise exercise)
    {

        String query = "Insert into " + tableName + " values (null, ?, ?)"; // pamietac o kolejnosci dodawania do listy params

        List<String> params = new ArrayList<>();
        params.add(exercise.getTitle());
        params.add(exercise.getDescription());
        DaoService.setNewId(exercise, query, params, dbName);
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

    private void update(Exercise exercise)
    {

        String query = "Update " + tableName + " Set `title` = ?, `description` = ? where `id` = ?";

        List<String> params = new ArrayList<>();
        params.add(exercise.getTitle());
        params.add(exercise.getDescription());
        params.add(exercise.getId().toString());

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
