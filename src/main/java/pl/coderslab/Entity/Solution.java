package pl.coderslab.Entity;

public class Solution
{
    private Integer id; // def null
    private String created;
    private String updated;
    private String description;
    private Exercise exercise;
    private User user;

    public Solution()
    {
    }

    public Solution(String created, String updated, String description, Exercise exercise, User user)
    {
        setCreated(created);
        setUpdated(updated);
        setDescription(description);
        setExercise(exercise);
        setUser(user);
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getCreated()
    {
        return created;
    }

    public void setCreated(String created)
    {
        this.created = created;
    }

    public String getUpdated()
    {
        return updated;
    }

    public void setUpdated(String updated)
    {
        this.updated = updated;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Exercise getExercise()
    {
        return exercise;
    }

    public void setExercise(Exercise exercise)
    {
        this.exercise = exercise;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "Solution{" +
                "id=" + id +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", description='" + description + '\'' +
                ", exercise=" + exercise +
                ", user=" + user +
                '}';
    }
}
