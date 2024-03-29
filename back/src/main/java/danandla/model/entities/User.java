package danandla.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

@Entity
@Table(name = "userstable")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column(name = "Login")
    private String login;

    @Column(name = "Password")
    private byte[] password;

    @Column(name = "Salt")
    private byte[] salt;

    @Column(name = "token")
    private String token;

    public String getLogin() {
        return login;
    }

    public byte[] getPassword() {
        return password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public long getId() {
        return id;
    }

    public User() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    //    public User(String login, byte[] password, byte[] salt) {
//        this.login = login;
//        this.password = password;
//        this.salt = salt;
//    }


    public User(String login, byte[] password, byte[] salt, String token) {
        this.login = login;
        this.password = password;
        this.salt = salt;
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password=" + Arrays.toString(password) +
                ", salt=" + Arrays.toString(salt) +
                '}';
    }
}
