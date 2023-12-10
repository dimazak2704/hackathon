package com.jdk.hackathon.Repositories;

import com.jdk.hackathon.Models.Category;
import com.jdk.hackathon.Models.Location;
import com.jdk.hackathon.Models.Proposal;
import com.jdk.hackathon.Models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Integer> {

    List<Proposal> findByCategories (Category categories);

    List<Proposal> findByLocations (Location locations);
}
