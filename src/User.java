/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author Manar
 */
@Entity
@Table(name="user")
public class User implements java.io.Serializable{
    @Id
    @Column(name="username")
    private String username;
     @Column(name="name")
    private String name;
     @Column(name="password")
    private String password;
     @Column(name="mobile")
    private String mobile;
     @Column(name="userLevel")
    private String userLevel;

    public User() {
    }
     

    public User(String username, String name, String password, String mobile, String userLevel) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.mobile = mobile;
        this.userLevel = userLevel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }
    
}
