package mx.edu.utez.neighborhoodcommitte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.neighborhoodcommitte.entity.Suburb;

public interface ISuburbRepository extends JpaRepository<Suburb, Long> {
    public Suburb findById(long id);
}
