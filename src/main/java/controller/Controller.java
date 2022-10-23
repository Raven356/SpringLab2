package controller;

import actors.User;
import models.Category;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import repository.CategoryRepository;
import repository.UserRepository;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/")
    public String StartPage(Model model){
        model.addAttribute("categories", new CategoryRepository().GetCategories());//change to Category service
        return "index";
    }

    @GetMapping("/log_in.html")
    public String Log_in(){
        return "log_in";
    }

    @PostMapping(value = "/log-in")
    public String CheckLogin(@ModelAttribute User user, Model model){
        UserRepository repository = new UserRepository(); // change to service
        
        for (User user1 : repository.GetUsers()){
            if(user1.GetMail().equals(user.GetMail())) {
                if (user1.GetPassword().equals(user.GetPassword())) {
                    if (user1.GetRole().equals("admin")) {
                        return "admin";
                    } else {
                        return "index";
                    }
                }
                else{
                    model.addAttribute("wrongPass", "You entered wrong password!");
                    return "log_in";
                }
            }
        }
        model.addAttribute("wrongPass", "Your data was wrong");
        return "log_in";
    }

    @GetMapping("/admin.html")
    public String Admin(){
        return "admin";
    }

    @RequestMapping(value = "/changeCategory", method = RequestMethod.POST)
    public String AdminChange(@ModelAttribute Category oldCategory, @ModelAttribute Category newCategory){
        new CategoryRepository().ChangeCategory(oldCategory, newCategory); // change to service
        return "admin";
    }

    @PostMapping(value = "/addCategory")
    public String AdminAdd(@ModelAttribute Category category){
        new CategoryRepository().AddCategory(category); // change to service
        return "admin";
    }
    @PostMapping(value = "/deleteCategory")
    public String AdminDelete(@ModelAttribute Category category){
        new CategoryRepository().DeleteCategory(category); // change to service
        return "admin";
    }


    @GetMapping("/index")
    public String Back(){
        return "index";
    }



}
