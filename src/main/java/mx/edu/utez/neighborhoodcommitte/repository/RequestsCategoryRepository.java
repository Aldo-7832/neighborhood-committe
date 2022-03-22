package mx.edu.utez.neighborhoodcommitte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.neighborhoodcommitte.entity.RequestsCategory;

public interface RequestsCategoryRepository extends JpaRepository<RequestsCategory, Long>{
    
}
