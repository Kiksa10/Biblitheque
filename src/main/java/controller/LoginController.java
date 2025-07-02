package controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    

    @GetMapping("/")
    public String redirectToLogin() {
        System.out.println("Redirection vers /login");
        return "index";
    }

    @GetMapping("/login")
    public String redirectToLog() {
        System.out.println("Redirection vers /login");
        return "index";
    }

    @GetMapping("/register")
    public String redirectToRegistre() {
        System.out.println("Redirection vers /Inscription");
        return "inscription";
    }
    
}
