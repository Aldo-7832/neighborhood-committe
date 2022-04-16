package mx.edu.utez.neighborhoodcommitte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.neighborhoodcommitte.entity.RequestAttachments;
import mx.edu.utez.neighborhoodcommitte.repository.IRequestAttachmentsRepository;

@Service
public class RequestAttachmentsService {

    @Autowired
    private IRequestAttachmentsRepository attachmentsRepository;

    public List<RequestAttachments> findAll() {
        return attachmentsRepository.findAll();
    }

    public RequestAttachments findById(long id) {
        return attachmentsRepository.findById(id);
    }

    public boolean save(RequestAttachments obj) {
        boolean flag = false;
        RequestAttachments tmp = attachmentsRepository.save(obj);
        if (!tmp.equals(null)) {
            flag = true;
        }
        return flag;
    }

    public boolean delete(long id) {
        boolean flag = false;
        RequestAttachments tmp = attachmentsRepository.findById(id);
        if (!tmp.equals(null)) {
            attachmentsRepository.delete(tmp);
            flag = true;
        }
        return flag;
    }
    
}
