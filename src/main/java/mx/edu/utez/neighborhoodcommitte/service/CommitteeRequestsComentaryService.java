package mx.edu.utez.neighborhoodcommitte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.neighborhoodcommitte.entity.CommitteeRequestsComentary;
import mx.edu.utez.neighborhoodcommitte.repository.CommitteeRequestsComentaryRepository;

@Service
public class CommitteeRequestsComentaryService {

    @Autowired
    private CommitteeRequestsComentaryRepository requestsComentaryRepository;

    public List<CommitteeRequestsComentary> findAll() {
        return requestsComentaryRepository.findAll();
    }

    public CommitteeRequestsComentary findOne(long id) {
        return requestsComentaryRepository.getById(id);
    }

    public boolean save(CommitteeRequestsComentary obj) {
        try {
            requestsComentaryRepository.save(obj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(long id) {
        CommitteeRequestsComentary tmp = requestsComentaryRepository.getById(id);
        if (!tmp.equals(null)) {
            requestsComentaryRepository.delete(tmp);
            return true;
        }
        return false;
    }
    
}
