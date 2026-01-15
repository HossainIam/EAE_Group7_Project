package de.hnu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
    @GetMapping("/")
    public String root() {
    return "redirect:/login";
    }


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password) {

        // validate user here

        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home() {
        return "ride-selection";
    }

    @GetMapping("/rides/search")
    public String searchRidePage() {
        return "search-ride";
    }

    @GetMapping("/rides/luggage")
    public String luggageRidePage() {
        return "luggage-ride";
    }
    // Register Page
    @GetMapping("/register")
    public String registerPage() {
    return "register";
}

    @PostMapping("/register")
    public String register(@RequestParam String username,
                       @RequestParam String password) {

    // TODO: save user to DB (recommended)
    // for now: store in memory or use repository

    return "redirect:/login";
}

}
