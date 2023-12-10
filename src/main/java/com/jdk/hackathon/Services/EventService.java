package com.jdk.hackathon.Services;

import com.jdk.hackathon.Models.Event;
import com.jdk.hackathon.Models.Location;
import com.jdk.hackathon.Models.Request;
import com.jdk.hackathon.Repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findAll(){
        List<Event> events = eventRepository.findAll();
        for (Event event : events){
            event.setLocationNames();
        }

        return events;
    }

    @Transactional
    public void save(Event event){
        eventRepository.save(event);
    }

    @Transactional
    public void findByLocations(Location location){
        eventRepository.findByLocations(location);
    }

}
