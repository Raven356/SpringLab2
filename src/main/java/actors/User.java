package actors;

public class User {
    private String role;
    private String mail;
    private String password;

    public User(){

    }
    public User(String role, String mail, String password){
        this.role = role;
        this.mail = mail;
        this.password = password;
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
