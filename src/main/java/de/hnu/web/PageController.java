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


    @GetMapping("/home")
    public String home() {
        return "ride-selection";
    }

    @GetMapping("/rides/search")
    public String searchRidePage() {
        return "ride-search";
    }

    @GetMapping("/rides/luggage")
    public String luggageRidePage() {
        return "luggage-ride";
    }

    @GetMapping("/rides/offer")  // Offer Ride Page
    public String offerRidePage() {
    return "offer-ride";
}

    // Register Page
    @GetMapping("/register")
    public String registerPage() {
         return "register";
}

    // request ride page
    @GetMapping("/rides/request")
    public String requestRidePage() {
        return "request-ride";
    }
    // list of ride requests page
        @GetMapping("/rides/requests")
    public String requestsPage() {
        return "request-list";
    }



}
