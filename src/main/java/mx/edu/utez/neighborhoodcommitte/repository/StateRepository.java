package mx.edu.utez.neighborhoodcommitte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.neighborhoodcommitte.entity.State;

public interface StateRepository  extends JpaRepository<State, Long>{
    
}
