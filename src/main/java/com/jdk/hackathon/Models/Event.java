package com.jdk.hackathon.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "place")
    private String place;

    @Column(name = "date")
    private Date date;

    @ManyToMany
    @JoinTable(
            name = "event_location",
            joinColumns = @JoinColumn(name="event_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private List<Location> locations;

    @Transient
    private List<String> locationNames;

    @Transient
    private List<String> selectedLocations;

    @Transient
    private String datefromfront;

    public Event() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<String> getLocationNames() {
        return locationNames;
    }

    public void setLocationNames() {
        List<String> names = new ArrayList<>();
        List<Location> locations = getLocations();
        for(Location location : locations){
            names.add(location.getValue());
        }

        this.locationNames = names;
    }


    public List<String> getSelectedLocations() {
        return selectedLocations;
    }

    public void setSelectedLocations(List<String> selectedLocations) {
        this.selectedLocations = selectedLocations;
    }

    public String getDatefromfront() {
        return datefromfront;
    }

    public void setDatefromfront(String datefromfront) {
        this.datefromfront = datefromfront;
    }
}
