package com.jdk.hackathon.Repositories;

import com.jdk.hackathon.Models.Category;
import com.jdk.hackathon.Models.Location;
import com.jdk.hackathon.Models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    List<Request> findByCategories (Category category);

    List<Request> findByLocations (Location location);
}
