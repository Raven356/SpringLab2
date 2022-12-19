package actors;
import javax.persistence.*;

@Entity
@Table(name = "Auth")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(name = "Role")
    private String role;
    @Column(name = "Login")
    private String mail;
    @Column(name = "Password")
    private String password;

    public User(){

    }
    public User(String role, String mail, String password){
        this.role = role;
        this.mail = mail;
        this.password = password;
    }

    public long getId() {
        return Id;
    }

    public void setMail(String mail){
        this.mail = mail;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getRole()
    {
        return role;
    }

    public String getMail(){
        return mail;
    }

    public String getPassword(){
        return password;
    }

}
