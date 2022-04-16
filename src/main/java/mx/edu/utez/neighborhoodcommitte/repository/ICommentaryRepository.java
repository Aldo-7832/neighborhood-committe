package mx.edu.utez.neighborhoodcommitte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.neighborhoodcommitte.entity.Commentary;

public interface ICommentaryRepository extends JpaRepository<Commentary, Long> {
    public Commentary findById(long id);
}
