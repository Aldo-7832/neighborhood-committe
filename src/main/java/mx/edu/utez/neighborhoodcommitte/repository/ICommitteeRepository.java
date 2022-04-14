package mx.edu.utez.neighborhoodcommitte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.neighborhoodcommitte.entity.Committee;

public interface ICommitteeRepository extends JpaRepository<Committee, Long> {
    public Committee findById(long id);
}
