package mx.edu.utez.neighborhoodcommitte.repository;

import mx.edu.utez.neighborhoodcommitte.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.neighborhoodcommitte.entity.Committee;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICommitteeRepository extends JpaRepository<Committee, Long>, PagingAndSortingRepository<Committee, Long> {
    public Committee findById(long id);
}
