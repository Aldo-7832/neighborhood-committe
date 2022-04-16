package mx.edu.utez.neighborhoodcommitte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.neighborhoodcommitte.entity.Binnacle;

public interface IBinnacleRepository extends JpaRepository<Binnacle, Long> {
    public Binnacle findById(long id);
}
