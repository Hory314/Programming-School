package pl.coderslab.Entity;

import org.mindrot.jbcrypt.BCrypt;

public class User
{
    private Integer id; // default null
    private String username;
    private String email;
    private String password;
    private UserGroup userGroup;

    public User()
    {
    }

    public User(String username, String email, String password, UserGroup userGroup)
    {
        setUsername(username);
        setEmail(email);
        setHashedPassword(password); // ustawiaj zahashowane
        setUserGroup(userGroup);
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    private static String hashPassword(String password)
    {
        return BCrypt.hashpw(password, BCrypt.gensalt()); // ma zwracac zahashowane haslo
    }

    public String getPassword()
    {
        return password;
    }

    public void setHashedPassword(String password)
    {
        this.password = hashPassword(password); // ustawiaj zahashowane
    }

    /* for dao only */
    public void setPasswordNoHashing(String password)
    {
        this.password = password; // ustawiaj bez hashowania (dla selecta z bazy)
    }

    public UserGroup getUserGroup()
    {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup)
    {
        this.userGroup = userGroup;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userGroup=" + userGroup +
                '}';
    }
}
