package com.jdk.hackathon.Services;

import com.jdk.hackathon.Models.Category;
import com.jdk.hackathon.Models.Location;
import com.jdk.hackathon.Models.Proposal;
import com.jdk.hackathon.Models.Request;
import com.jdk.hackathon.Repositories.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class ProposalService {
    private final ProposalRepository proposalRepository;

    @Autowired
    public ProposalService(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    public List<Proposal> findAll(){
        List<Proposal> proposals = proposalRepository.findAll();

        for (Proposal proposal : proposals){
            proposal.setDifferenceInDays();
            proposal.setLocationNames();
            proposal.setCategoryNames();
        }

        return proposals;
    }

    public List<Proposal> findByCategory(Category category){
        return proposalRepository.findByCategories(category);
    }

    public List<Proposal> findByLocation(Location location){
        return proposalRepository.findByLocations(location);
    }

    @Transactional
    public void save(Proposal proposal){
        proposal.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        proposalRepository.save(proposal);
    }

    @Transactional
    public void delete(int id) {
        proposalRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Proposal newproposal){
        newproposal.setId(id);
        proposalRepository.save(newproposal);
    }
}
