package controller;

import actors.User;
import models.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import repository.CategoryRepository;
import repository.UserRepository;
import services.CategoriesService;
import services.UserService;


@org.springframework.stereotype.Controller
@ComponentScan(basePackages={"services"})
public class Controller
{
    private final CategoriesService categoriesService;
    private final UserService userService;
    @Autowired
    public Controller(CategoriesService categoriesService, UserService userService)
    {
        this.categoriesService = categoriesService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String StartPage(Model model){

        model.addAttribute("categories", categoriesService.getCategories());

        return "index";
    }

    @GetMapping("/log_in.html")
    public String Log_in(Model model){
        model.addAttribute("user", new User());
        return "log_in";
    }



    @PostMapping(value = "/log_in.html")
    public String CheckLogin(@ModelAttribute User user, Model model){
        if (!userService.AdminCheck(user)) {
            model.addAttribute("wrongPass", "Your data was wrong");
            return "log_in";
        }
        else
        {
            model.addAttribute("category", new Category(null, null, null));
            model.addAttribute("oldCategory", new Category(null, null, null));
            model.addAttribute("newCategory", new Category(null, null, null));
            model.addAttribute("deleteCategory", new Category(null, null, null));
            return "admin";
        }
    }

    @GetMapping("/admin.html")
    public String Admin(Model model){
        model.addAttribute("category", new Category(null, null, null));
        return "admin";
    }


    @RequestMapping(value = "/changeCategory", method = RequestMethod.POST)
    public String AdminChange(@ModelAttribute Category oldCategory, @ModelAttribute Category newCategory){
        categoriesService.ChangeCategory(oldCategory, newCategory);
        return "admin";
    }

    @PostMapping(value = "/addCategory")
    public String AdminAdd(@ModelAttribute("category") Category category){
        categoriesService.AddCategory(category);
        return "admin";
    }
    @PostMapping(value = "/deleteCategory")
    public String AdminDelete(@ModelAttribute Category category){
        categoriesService.DeleteCategory(category);

        return "admin";
    }


    @GetMapping("/index")
    public String Back(){
        return "index";
    }



}
