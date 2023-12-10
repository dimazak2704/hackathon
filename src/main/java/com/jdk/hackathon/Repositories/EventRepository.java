package com.jdk.hackathon.Repositories;

import com.jdk.hackathon.Models.Event;
import com.jdk.hackathon.Models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    List<Event> findByLocations(Location location);
}
