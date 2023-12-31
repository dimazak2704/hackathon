package com.jdk.hackathon.Repositories;

import com.jdk.hackathon.Models.Category;
import com.jdk.hackathon.Models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    Location findByValue(String value);

}
