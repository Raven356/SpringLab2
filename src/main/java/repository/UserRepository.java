package repository;

import actors.User;
import models.Category;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserRepository {
    private ArrayList<User> users = new ArrayList<User>(){
        {
            add(new User("user", "kittie@gmail.com", "kittieLover"));
            add(new User("admin", "admin", "admin"));
        }
    };

    @Bean
    public ArrayList<User> getUsers() { return this.users; }
    public void setUsers(ArrayList<User> users)
    {
        this.users = users;

    }
}
