package com.csc340.mvcdemo.Controller;

import com.csc340.mvcdemo.model.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author sunny
 */
@Controller
public class GreetingController {

    @GetMapping("/hello")
    public String hello(Model model) {
        Greeting greeting = new Greeting("Hello", "World");
        model.addAttribute("greeting", greeting);
        return "hello";
    }
}
