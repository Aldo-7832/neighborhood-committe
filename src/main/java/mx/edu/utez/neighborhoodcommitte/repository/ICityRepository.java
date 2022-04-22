package mx.edu.utez.neighborhoodcommitte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.neighborhoodcommitte.entity.City;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICityRepository extends JpaRepository<City, Long> , PagingAndSortingRepository<City, Long> {
    public City findById(long id);
}
