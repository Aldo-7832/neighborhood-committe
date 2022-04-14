package mx.edu.utez.neighborhoodcommitte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.neighborhoodcommitte.entity.State;

public interface IStateRepository extends JpaRepository<State, Long> {
    public State findById(long id);
}
