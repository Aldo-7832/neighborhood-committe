package mx.edu.utez.neighborhoodcommitte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.neighborhoodcommitte.entity.Request;

public interface IRequestRepository extends JpaRepository<Request, Long> {
    public Request findById(long id);
}
