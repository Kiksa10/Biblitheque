package controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    

    @GetMapping("/")
    public String redirectToList() {
        System.out.println("Redirection vers /login");
        return "index";
    }
}
