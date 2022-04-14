package mx.edu.utez.neighborhoodcommitte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.neighborhoodcommitte.entity.RequestAttachments;

public interface IRequestAttachmentsRepository extends JpaRepository<RequestAttachments, Long> {
    public RequestAttachments findById(long id);
}
