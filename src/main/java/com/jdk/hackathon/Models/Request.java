package com.jdk.hackathon.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "request")
public class Request {

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

    @Column(name = "place")
    private String place;

    @Column(name = "date")
    private Date date;

    @ManyToMany
    @JoinTable(
            name = "request_category",
            joinColumns = @JoinColumn(name="request_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;


    public Request() {
    }

    public Request(String title, String description, String contacts, String place, Date date) {
        this.title = title;
        this.description = description;
        this.contacts = contacts;
        this.place = place;
        this.date = date;
    }

    public Request(String title, String description, String contacts, String place) {
        this.title = title;
        this.description = description;
        this.contacts = contacts;
        this.place = place;
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }


}


/*
create table request(
    id int primary key auto_increment,
    title varchar(255) not null,
    description varchar(1000),
    contacts varchar(255) not null,
    place varchar(500),
    date varchar(50)
);

create table request_category(
    request_id int references request(id),
    category_id int references category(id),
    primary key (request_id, category_id)
);
 */