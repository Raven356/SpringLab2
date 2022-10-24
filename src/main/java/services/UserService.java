package services;
import actors.User;
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
    public ArrayList<User> getUsers(){
        return userRepository.getUsers();
    }

    //@Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public boolean AdminCheck(User user)
    {
        for (User user1 : userRepository.getUsers()){
            if(user1.getMail().equals(user.getMail())) {
                if (user1.getPassword().equals(user.getPassword()))
                {
                    return (user1.getRole().equals("admin"));
                }
            }
        }
        return false;
    }

}
