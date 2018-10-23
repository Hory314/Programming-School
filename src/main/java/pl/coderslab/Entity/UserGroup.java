package pl.coderslab.Entity;

public class UserGroup
{
    private Integer id; // default null
    private String name;

    public UserGroup()
    {
    }

    public UserGroup(String name)
    {
        setName(name);
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "UserGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
