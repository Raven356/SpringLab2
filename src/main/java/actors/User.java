package actors;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class User {
    private String role;
    private String mail;
    private String password;

    public User(String role, String mail, String password){
        this.role = role;
        this.mail = mail;
        this.password = password;
    }

    public String GetRole()
    {
        return role;
    }

    public String GetMail(){
        return mail;
    }

    public String GetPassword(){
        return password;
    }
}
