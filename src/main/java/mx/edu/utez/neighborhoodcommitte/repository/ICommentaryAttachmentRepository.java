package mx.edu.utez.neighborhoodcommitte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.neighborhoodcommitte.entity.CommentaryAttachments;

public interface ICommentaryAttachmentRepository extends JpaRepository<CommentaryAttachments, Long> {
    public CommentaryAttachments findById(long id);
}
