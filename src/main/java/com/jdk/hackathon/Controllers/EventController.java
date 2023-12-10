package com.jdk.hackathon.Controllers;

import com.jdk.hackathon.Models.Event;
import com.jdk.hackathon.Models.Location;
import com.jdk.hackathon.Services.EventService;
import com.jdk.hackathon.Services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;
    private final LocationService locationService;

    @Autowired
    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("events", eventService.findAll());
        model.addAttribute("locations", locationService.findAll());

        return "event/Events";
    }


    @GetMapping("/new")
    public String newEvent(@ModelAttribute("event")Event event,
                           Model model){
        model.addAttribute("locations", locationService.findAll());

        return "event/Events_Registration";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("event") Event event,
                         Model model) throws ParseException {
        model.addAttribute("location", locationService.findAll());

        List<String> selectedLocations = event.getSelectedLocations();
        List<Location> locations = new ArrayList<>();
        for (String s : selectedLocations){
            locations.add(locationService.findByValue(s));
        }

        String datefront = event.getDatefromfront();

        datefront = datefront.replace('T', ' ');
        datefront = datefront+":00";

        SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = formatter6.parse(datefront);

        event.setDate(date);

        event.setLocations(locations);
        eventService.save(event);
        return "redirect:/event/index";
    }
}
