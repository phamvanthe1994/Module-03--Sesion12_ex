package re.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import re.com.dto.request.FlightDTO;
import re.com.dto.request.FlightUpdateDTO;
import re.com.model.Flight;
import re.com.service.FlightService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/flightController")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping("/findAll")
    public String findAll(Model model,
                          @RequestParam(defaultValue = "1") int pageNumber,
                          @RequestParam(defaultValue = "3") int pageSize) {
        List<Flight> listFlight = flightService.findAll(pageNumber, pageSize);
        long total = flightService.countAll();
        int totalPage = (int) Math.ceil((double) total / pageSize);
        model.addAttribute("listFlight", listFlight);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPage", totalPage);
        return "flight";
    }

    @GetMapping("/initCreate")
    public String initCreateFlight(Model model,
                                   @RequestParam(defaultValue = "1") int pageNumber,
                                   @RequestParam(defaultValue = "3") int pageSize) {
        List<Flight> listFlight = flightService.findAll(pageNumber, pageSize);
        model.addAttribute("flightDTO", new FlightDTO());
        model.addAttribute("listFlight", listFlight);
        return "newFlight";
    }

    @PostMapping("/create")
    public String createFlight(@Valid @ModelAttribute("flightDTO")
                               FlightDTO flightDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "newFlight";
        }
        boolean result = flightService.saveFlight(flightDTO);
        if (result) {
            return "redirect:findAll";
        }

        return "error";
    }

    @GetMapping("/initUpdate")
    public String initUpdateFlight(Model model, int flightId,
                                   @RequestParam(defaultValue = "1") int pageNumber,
                                   @RequestParam(defaultValue = "3") int pageSize) {
        FlightUpdateDTO flightUpdateDTO = flightService.findFlightById(flightId);
        model.addAttribute("flightUpdateDTO", flightUpdateDTO);
        model.addAttribute("listFlight", flightService.findAll(pageNumber, pageSize));
        return "updateFlight";
    }

    @PostMapping("/update")
    public String updateFlight(@Valid @ModelAttribute("flightUpdateDTO")
                               FlightUpdateDTO flightUpdateDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "updateFlight";
        }
        boolean result = flightService.updateFlight(flightUpdateDTO);
        if (result) {
            return "redirect:findAll";
        }
        return "error";
    }

    @PostMapping("/updateStatus")
    public String updateFlightStatus(@RequestParam("flightId") int flightId, @RequestParam("status") int status) {
        flightService.updateFlightStatus(flightId, status);
        return "redirect:findAll";
    }

    @GetMapping("/findByName")
    public String findFlightByName(@RequestParam String flightName, Model model) {
        List<Flight> listFlight = flightService.findFlightByFlightName(flightName);
        model.addAttribute("listFlight", listFlight);
        return "flight";
    }

    @GetMapping("/findByRoute")
    public String findFlightByRoute(@RequestParam String from, @RequestParam String to, Model model) {
        List<Flight> listFlight = flightService.findFlightByRoute(from, to);
        model.addAttribute("listFlight", listFlight);
        return "flight";
    }

    @GetMapping("/delete")
    public String deleteFlight(int flightId) {
        boolean result = flightService.deleteFlightByFlightId(flightId);
        if (result) {
            return "redirect:findAll";
        }
        return "error";
    }
}
