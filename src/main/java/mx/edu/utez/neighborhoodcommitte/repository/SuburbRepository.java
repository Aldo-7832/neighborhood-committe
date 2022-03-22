package mx.edu.utez.neighborhoodcommitte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.neighborhoodcommitte.entity.Suburb;

public interface SuburbRepository extends JpaRepository<Suburb, Long>{
    
}
