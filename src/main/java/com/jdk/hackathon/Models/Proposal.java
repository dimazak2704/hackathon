package com.jdk.hackathon.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "proposal")
public class Proposal {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "contacts")
    private String contacts;

    @Column(name = "date")
    private Date date;

    @ManyToMany
    @JoinTable(
            name = "proposal_category",
            joinColumns = @JoinColumn(name="proposal_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @ManyToMany
    @JoinTable(
            name = "proposal_location",
            joinColumns = @JoinColumn(name="proposal_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private List<Location> locations;

    @Transient
    private String differenceInDays;

    @Transient
    private List<String> locationNames;

    @Transient
    private List<String> categoryNames;

    @Transient
    private List<String> SelectedCategories;

    @Transient
    private List<String> SelectedLocations;

    public Proposal() {
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

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public String getDifferenceInDays() {
        return differenceInDays;
    }

    public void setDifferenceInDays() {
        Date currentDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date requestDate = Date.from(getDate().toInstant());
        long daysAgo = ChronoUnit.DAYS.between(requestDate.toInstant(), currentDate.toInstant());

        String result = String.valueOf(daysAgo);

        if (daysAgo % 10 == 1 && daysAgo != 11) {
            result+=" день назад";
        } else if ((daysAgo % 10 == 2 || daysAgo % 10 == 3 || daysAgo % 10 == 4) && (daysAgo < 10 || daysAgo > 20)) {
            result+=" дні назад";
        } else {
            result+=" днів назад";
        }
        this.differenceInDays = result;
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

    public List<String> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames() {
        List<String> names = new ArrayList<>();
        List<Category> categories = getCategories();
        for (Category category : categories){
            names.add(category.getValue());
        }

        this.categoryNames = names;
    }

    public List<String> getSelectedCategories() {
        return SelectedCategories;
    }

    public void setSelectedCategories(List<String> selectedCategories) {
        SelectedCategories = selectedCategories;
    }

    public List<String> getSelectedLocations() {
        return SelectedLocations;
    }

    public void setSelectedLocations(List<String> selectedLocations) {
        SelectedLocations = selectedLocations;
    }
}
