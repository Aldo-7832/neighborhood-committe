package mx.edu.utez.neighborhoodcommitte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.neighborhoodcommitte.entity.Request;
import mx.edu.utez.neighborhoodcommitte.repository.IRequestRepository;

@Service
public class RequestService {

    @Autowired
    private IRequestRepository requestRepository;

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public Request findById(long id) {
        return requestRepository.findById(id);
    }

    public boolean save(Request obj) {
        boolean flag = false;
        Request tmp = requestRepository.save(obj);
        if (!tmp.equals(null)) {
            flag = true;
        }
        return flag;
    }

    public boolean delete(long id) {
        boolean flag = false;
        Request tmp = requestRepository.findById(id);
        if (!tmp.equals(null)) {
            requestRepository.delete(tmp);
            flag = true;
        }
        return flag;
    }
    
}
