package mx.edu.utez.neighborhoodcommitte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.edu.utez.neighborhoodcommitte.entity.RequestAttachments;

public interface IRequestAttachmentsRepository extends JpaRepository<RequestAttachments, Long> {
    public RequestAttachments findById(long id);
    @Query(value = "SELECT * FROM request_attachments ra WHERE ra.request = :id", nativeQuery = true)
    public RequestAttachments findAttachmentsByRequestId(@Param("id") long id);
}
