package mx.edu.utez.neighborhoodcommitte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.neighborhoodcommitte.entity.CommitteeRequest;

public interface CommitteeRequestRepository extends JpaRepository<CommitteeRequest, Long>{
    
}
