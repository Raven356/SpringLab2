package services;
import actors.User;
import models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.ArrayList;

@Service
@ComponentScan(basePackages={"repository"})
public class UserService
{
    private UserRepository userRepository;
    public UserService(UserRepository UserRepo)
    {
        this.userRepository = UserRepo;
    }
    public ArrayList<User> GetUsers(){
        return userRepository.GetUsers();
    }

    //@Autowired
    public void SetUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public boolean AdminCheck(User user)
    {
        for (User user1 : userRepository.GetUsers()){
            if(user1.GetMail().equals(user.GetMail())) {
                if (user1.GetPassword().equals(user.GetPassword()))
                {
                    return (user1.GetRole().equals("admin"));
                }
            }
        }
        return false;
    }

}
