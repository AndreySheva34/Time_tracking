package org.itstep.timeTracking;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    String home(Model model) {
        model.addAttribute("title", "Home Page");
        return "index";
    }
}
