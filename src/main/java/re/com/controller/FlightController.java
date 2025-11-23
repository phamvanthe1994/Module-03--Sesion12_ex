package re.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import re.com.service.FlightService;

@Controller
@RequestMapping("/flightController")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping("/findAll")
    public String findAll(Model model) {
        model.addAttribute("listFlight", flightService.findAll());
        return "flight";
    }
}
