package repository;

import actors.User;
import models.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserRepository {
    private ArrayList<User> users = new ArrayList<User>(){
        {
            add(new User("user", "kittie@gmail.com", "kittieLover"));
            add(new User("admin", "admin", "admin"));
        }
    };
    public ArrayList<User> getUsers() { return this.users; }
    public void setUsers(ArrayList<User> users)
    {
        this.users = users;
    }
}
