package com.jdk.hackathon.Services;

import com.jdk.hackathon.Models.Category;
import com.jdk.hackathon.Models.Location;
import com.jdk.hackathon.Repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }


    public List<Location> findAll(){
        return locationRepository.findAll();
    }

}
