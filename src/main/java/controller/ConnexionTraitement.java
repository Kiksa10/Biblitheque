package controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;


@Controller
public class ConnexionTraitement {
    

    @PostMapping("/login")
    public String traitement_Login(){

        return "redirect:/adminHome";
    }
}
