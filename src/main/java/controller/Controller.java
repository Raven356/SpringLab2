package controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/")
    public String StartPage(){
        return "index";
    }

    @GetMapping("/log_in.html")
    public String Log_in(){
        return "log_in";
    }

    @GetMapping("/admin.html")
    public String Admin(){
        return "admin";
    }

    @GetMapping("/index")
    public String Back(){
        return StartPage();
    }



}
