package controller;

import exceptions.CollisionException;
import exceptions.NotFoundException;
import models.Category;
import models.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import services.CategoriesDbService;
import services.CategoriesService;
import services.UserService;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@ComponentScan(basePackages={"services"})
public class RestController {
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private UserService userService;

    @Autowired
    private CategoriesDbService categoriesDbService;

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
        catch (CollisionException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("desc", "Adding category");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).build();
    }

    @PostMapping("/addGoods")
    public ResponseEntity<Void> addGoods(/*@RequestBody Goods goods*/){
        int id = 0;
        try {
            Goods goods1 = new Goods();
            goods1.setName("d");
            goods1.setPrice(1);
            goods1.setCategory(null);
            id = categoriesDbService.AddGoods(goods1);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("desc", "Adding goods");
        httpHeaders.add("Id", String.valueOf(id));
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).build();
    }

    @GetMapping("/selectGoodsByName/{name}")
    public ResponseEntity<?> selectGoodsByName(@PathVariable String name){
        try{
            Goods goods = categoriesDbService.SelectGoodsByName(name);
            return new ResponseEntity<>(goods, HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/deleteGoods")
    public ResponseEntity<Void> deleteGoods(@RequestBody Goods goods){
        try {
            goods.setId(categoriesDbService.SelectGoodsByName(goods.getName()).getId());
            categoriesDbService.DeleteGoods(goods);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("desc", "Deleting goods");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).build();
    }

    @PutMapping("/updateGoods/{id}")
    public ResponseEntity<Void> updateGoods(@RequestBody Goods goods, @PathVariable int id) {
        try {
            goods.setId(id);
            categoriesDbService.AddGoods(goods);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("desc", "Updating goods");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).build();
    }

    @DeleteMapping("/deleteCat")
    public ResponseEntity<Void> deleteCategory(@RequestBody Category category){
        try {
            categoriesService.DeleteCategory(category);
        }
        catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
        if(categories.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        try {
            categoriesService.ChangeCategory(categories.get(0), categories.get(1));
        }
        catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("desc", "Updating category");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).build();

    }

    @GetMapping("/filterCat/{category}")
    public ResponseEntity<?> filterByCat(@PathVariable String category){
        ArrayList<Category> categories = categoriesService.getCategories();
        ArrayList<Goods> filtered = new ArrayList<>();
        for(Category cat : categories){
            if(cat.getName().equals(category)){
                if(cat.getGoods() != null)
                    filtered.addAll(cat.getGoods());
            }
        }
        if(filtered.size() < 1)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(filtered, HttpStatus.FOUND);
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<?> page(@PathVariable int page){
        ArrayList<Goods> goods = new ArrayList<>();
        for (Category cat: categoriesService.getCategories()) {
            if(cat.getGoods() != null){
                goods.addAll(cat.getGoods());
            }
        }
        ArrayList<Goods> goodsOnPage = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            if((i + (page - 1) * 4) >= goods.size())
                break;
            goodsOnPage.add(goods.get(i + (page - 1) * 4));
        }
        if(goodsOnPage.size() < 1)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(goodsOnPage, HttpStatus.FOUND);
    }


}
