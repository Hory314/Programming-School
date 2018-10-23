package pl.coderslab.Entity;

import org.mindrot.jbcrypt.BCrypt;

public class User
{
    private Integer id; // default 0
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

    private static String hashPassword(String password)
    {
        return BCrypt.hashpw(password, BCrypt.gensalt()); // ma zwracac zahashowane haslo
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
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

    public String getPassword()
    {
        return password; // pobiera oczywiscie zahashowane bo takie bedzie trzymane tylko w obiekcie
    }

    public void setHashedPassword(String password)
    {
        this.password = hashPassword(password); // ustawiaj zahashowane
    }

//    public void setPasswordNoHashing(String password)
//    {
//        this.password = password;
//    }

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
