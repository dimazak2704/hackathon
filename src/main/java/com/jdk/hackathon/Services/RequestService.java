package com.jdk.hackathon.Services;

import com.jdk.hackathon.Models.Category;
import com.jdk.hackathon.Models.Location;
import com.jdk.hackathon.Models.Request;
import com.jdk.hackathon.Repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class RequestService {
    private final RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }


    public List<Request> findAll(){
        List<Request> requests = requestRepository.findAll();

        for (Request request : requests){
            request.setDifferenceInDays();
            request.setLocationNames();
            request.setCategoryNames();
        }

        return requests;
    }

    public List<Request> findByCategory(Category category){
        return requestRepository.findByCategories(category);
    }

    public List<Request> findByLocation(Location location){
        return requestRepository.findByLocations(location);
    }

    @Transactional
    public void save(Request request){
        request.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        requestRepository.save(request);
    }

    @Transactional
    public void delete(int id) {
        requestRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Request newRequest){
        newRequest.setId(id);
        requestRepository.save(newRequest);
    }

}
