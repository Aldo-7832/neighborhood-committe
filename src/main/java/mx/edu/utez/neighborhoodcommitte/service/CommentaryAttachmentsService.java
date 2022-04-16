package mx.edu.utez.neighborhoodcommitte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.neighborhoodcommitte.entity.CommentaryAttachments;
import mx.edu.utez.neighborhoodcommitte.repository.ICommentaryAttachmentRepository;

@Service
public class CommentaryAttachmentsService {
    
    @Autowired
    private ICommentaryAttachmentRepository attachmentRepository;

    public List<CommentaryAttachments> findAll() {
        return attachmentRepository.findAll();
    }

    public CommentaryAttachments findById(long id) {
        return attachmentRepository.findById(id);
    }

    public boolean save(CommentaryAttachments obj) {
        boolean flag = false;
        CommentaryAttachments tmp = attachmentRepository.save(obj);
        if (!tmp.equals(null)) {
            flag = true;
        }
        return flag;
    }

    public boolean delete(long id) {
        boolean flag = false;
        CommentaryAttachments tmp = attachmentRepository.findById(id);
        if (!tmp.equals(null)) {
            attachmentRepository.delete(tmp);
            flag = true;
        }
        return flag;
    }

}
