package controller;

import actors.User;
import models.Category;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import repository.CategoryRepository;
import repository.UserRepository;

@org.springframework.stereotype.Controller
public class Controller {

    //TODO
    //Add constructor with services

    @GetMapping("/")
    public String StartPage(Model model){
        model.addAttribute("categories", new CategoryRepository().GetCategories());//change to Category service
        return "index";
    }

    @GetMapping("/log_in.html")
    public String Log_in(Model model){
        model.addAttribute("user", new User());
        return "log_in";
    }

    @PostMapping(value = "/log_in.html")
    public String CheckLogin( @ModelAttribute("user") User user, Model model){
        UserRepository repository = new UserRepository(); // change to service

        for (User user1 : repository.GetUsers()){
            if(user1.getMail().equals(user.getMail())) {
                if (user1.getPassword().equals(user.getPassword())) {
                    if (user1.getRole().equals("admin")) {
                        model.addAttribute("category", new Category(null, null, null));
                        model.addAttribute("oldCategory", new Category(null, null, null));
                        model.addAttribute("newCategory", new Category(null, null, null));
                        model.addAttribute("deleteCategory", new Category(null, null, null));
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
    public String Admin(Model model){
        model.addAttribute("category", new Category(null, null, null));
        return "admin";
    }

    @RequestMapping(value = "/changeCategory", method = RequestMethod.POST)
    public String AdminChange(@ModelAttribute("oldCategory") Category oldCategory, @ModelAttribute("newCategory") Category newCategory){
        new CategoryRepository().ChangeCategory(oldCategory, newCategory); // change to service
        return "admin";
    }

    @PostMapping(value = "/addCategory")
    public String AdminAdd(@ModelAttribute("category") Category category){
        new CategoryRepository().AddCategory(category); // change to service
        return "admin";
    }
    @PostMapping(value = "/deleteCategory")
    public String AdminDelete(@ModelAttribute("deleteCategory") Category category){
        new CategoryRepository().DeleteCategory(category); // change to service
        return "admin";
    }


    @GetMapping("/index")
    public String Back(){
        return "index";
    }



}
