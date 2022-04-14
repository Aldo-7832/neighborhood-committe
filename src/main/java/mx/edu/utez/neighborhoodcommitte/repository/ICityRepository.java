package mx.edu.utez.neighborhoodcommitte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.neighborhoodcommitte.entity.City;

public interface ICityRepository extends JpaRepository<City, Long> {
    public City findById(long id);
}
