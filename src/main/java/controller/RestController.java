package controller;

import models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import services.CategoriesService;
import services.UserService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@ComponentScan(basePackages={"services"})
public class RestController {
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private UserService userService;

    @GetMapping("/selectAll")
    public ResponseEntity<?> selectAll(){
        return new ResponseEntity<>(categoriesService.getCategories(), HttpStatus.OK);
    }

    @GetMapping("/selectByName/{name}")
    public ResponseEntity<?> selectByName(@PathVariable String name){

        for(Category cat : categoriesService.getCategories()){
            if(cat.getName().equals(name))
                return new ResponseEntity<>(cat, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addCat")
    public ResponseEntity<Void> addCategory(@RequestBody Category category){
        try {
            categoriesService.AddCategory(category);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("desc", "Adding category");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).build();
    }

    @PostMapping("/deleteCat")
    public ResponseEntity<Void> deleteCategory(@RequestBody Category category){
        try {
            categoriesService.DeleteCategory(category);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("desc", "Deleting category");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).build();
    }

    @PutMapping ("/changeCat")
    public ResponseEntity<Void> changeCategory(@RequestBody List<Category> categories){
        try {
            categoriesService.ChangeCategory(categories.get(0), categories.get(1));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("desc", "Updating category");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).build();

    }

}
