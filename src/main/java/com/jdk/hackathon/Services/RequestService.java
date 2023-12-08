package com.jdk.hackathon.Services;

import com.jdk.hackathon.Models.Category;
import com.jdk.hackathon.Models.Request;
import com.jdk.hackathon.Repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDate.*;

@Service
@Transactional(readOnly = true)
public class RequestService {
    private final RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }


    public List<Request> findAll(){
        return requestRepository.findAll();
    }

    public List<Request> findByCategory(Category category){
        return requestRepository.findByCategories(category);
    }

    @Transactional
    public void save(Request request){
        LocalDate local = LocalDate.now();
        request.setDate(Date.from(local.atStartOfDay(ZoneId.systemDefault()).toInstant()));
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