package mx.edu.utez.neighborhoodcommitte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.edu.utez.neighborhoodcommitte.entity.CommentaryObject;

public interface ICommentaryRepository extends JpaRepository<CommentaryObject, Long> {
    public CommentaryObject findById(long id);
    @Query(value = "SELECT * FROM commentary c WHERE c.request = :id ORDER BY c.id DESC", nativeQuery = true)
    public List<CommentaryObject> findAllByRequestId(@Param("id") long id);
}
