package danandla.model.entities;


import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name="usersTable")
public class User extends MyEntity{
    public User(){}

    @Id
    @Column(name = "UserId")
    private long userId;

    @Column(name = "Login")
    private String login;

    @Column(name = "Password")
    private byte[] password;

    @Column(name = "Salt")
    private byte[] salt;

    public long getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public byte[] getPassword() {
        return password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public User(long userId, String login, byte[] password, byte[] salt) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "user{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password=" + Arrays.toString(password) +
                ", salt=" + Arrays.toString(salt) +
                '}';
    }
}
