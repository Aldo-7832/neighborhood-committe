package mx.edu.utez.neighborhoodcommitte.repository;

import mx.edu.utez.neighborhoodcommitte.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.neighborhoodcommitte.entity.Suburb;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ISuburbRepository extends JpaRepository<Suburb, Long>, PagingAndSortingRepository<Suburb, Long> {
    public Suburb findById(long id);
}
