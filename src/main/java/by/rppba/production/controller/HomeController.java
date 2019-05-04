package by.rppba.production.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        GrantedAuthority grantedAuthority = auth.getAuthorities().stream().findFirst().get();
        switch (grantedAuthority.getAuthority()) {
            case "MASTER_ASSAMBLY":
            case "MASTER_SHOP":
                return "master";
            case "TECHNOLOGIST":
            case "DISPATCHER":
                return grantedAuthority.getAuthority().toLowerCase();
            default:
                return "home";
        }
    }
}
