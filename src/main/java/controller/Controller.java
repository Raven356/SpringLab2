package controller;

import actors.User;
import models.Category;

import models.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import services.CategoriesService;
import services.UserService;
import thymeleafModels.CategoryGoodsNames;

import java.util.ArrayList;


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
            SetAdminModel(model);
            return "admin";
        }
    }

    @GetMapping("/admin.html")
    public String Admin(Model model){
        model.addAttribute("category", new Category(null, null, null));
        return "admin";
    }


    @RequestMapping(value = "/changeCategory", method = RequestMethod.POST)
    public String AdminChange(@ModelAttribute Category oldCategory
            , @ModelAttribute("categoryGoodsNames") CategoryGoodsNames categoryGoodsNames, Model model){
        Category newCategory = new Category(null, null, null);
        if(!oldCategory.getName().equals("")){
            if(!categoryGoodsNames.getCategoryName().equals("")){
                newCategory.setName(categoryGoodsNames.getCategoryName());
                if(categoryGoodsNames.getSubCategoryName().equals("")){
                    newCategory.setCategories(null);
                }
                else
                {
                    newCategory.setCategories(new ArrayList<Category>(){
                        {
                            add(new Category(categoryGoodsNames.getSubCategoryName(), null, null));
                        }
                    });
                }
                if(categoryGoodsNames.getItemsName().equals("")){
                    newCategory.setGoods(null);
                }
                else
                {
                    newCategory.setGoods(new ArrayList<Goods>(){
                        {
                            add(new Goods(categoryGoodsNames.getItemsName(), 0));
                        }
                    });
                }
            }
            categoriesService.ChangeCategory(oldCategory, newCategory);
        }
        SetAdminModel(model);
        return "admin";
    }

    @PostMapping(value = "/addCategory")
    public String AdminAdd(@ModelAttribute("category") Category category
            , @ModelAttribute("categoryGoodsNames") CategoryGoodsNames categoryGoodsNames, Model model){
        if(categoryGoodsNames.getSubCategoryName().equals(""))
            category.setCategories(null);
        else
            category.setCategories(new ArrayList<Category>(){
                {add(new Category(categoryGoodsNames.getSubCategoryName(), null, null));}
            });
        if(categoryGoodsNames.getItemsName().equals("")){
            category.setGoods(null);
        }
        else{
            category.setGoods(new ArrayList<Goods>(){
                {add(new Goods(categoryGoodsNames.getItemsName(), 0));}
            });
        }
        categoriesService.AddCategory(category);
        SetAdminModel(model);
        return "admin";
    }
    @PostMapping(value = "/deleteCategory")
    public String AdminDelete(@ModelAttribute("deleteCategory") Category category
            , @ModelAttribute("categoryGoodsNames") CategoryGoodsNames categoryGoodsNames, Model model){
        if(!category.getName().equals("")){
            category.setCategories(null);

            if(categoryGoodsNames.getItemsName().equals("")) {
                category.setGoods(null);
                categoriesService.DeleteCategory(category);
            }
            else {
                category.setGoods(new ArrayList<Goods>(){
                    {
                        add(new Goods(categoryGoodsNames.getItemsName(), 0));
                    }
                });
                categoriesService.DeleteGoods(category);
            }
        }
        SetAdminModel(model);
        return "admin";
    }


    @GetMapping("/index")
    public String Back(){
        return "index";
    }

    private void SetAdminModel(Model model){
        model.addAttribute("category", new Category(null, null, null));
        model.addAttribute("categoryGoodsNames", new CategoryGoodsNames());
        model.addAttribute("oldCategory", new Category(null, null, null));
        model.addAttribute("newCategory", new Category(null, null, null));
        model.addAttribute("deleteCategory", new Category(null, null, null));
    }



}
