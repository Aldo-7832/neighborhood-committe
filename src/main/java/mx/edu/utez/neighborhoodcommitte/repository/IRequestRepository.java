package mx.edu.utez.neighborhoodcommitte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.edu.utez.neighborhoodcommitte.entity.Request;

public interface IRequestRepository extends JpaRepository<Request, Long> {
    public Request findById(long id);
    @Query(value = "SELECT r.* FROM committee c INNER JOIN users u ON u.committee = c.id INNER JOIN request r ON r.user = u.id WHERE c.id = :id", nativeQuery = true)
    List<Request> findAllByCommitteeId(@Param("id") long id);
}
