package mx.edu.utez.neighborhoodcommitte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.neighborhoodcommitte.entity.CommitteeRequest;
import mx.edu.utez.neighborhoodcommitte.repository.CommitteeRequestRepository;

@Service
public class CommitteeRequestService {

    @Autowired
    CommitteeRequestRepository committeeRepository;

    public List<CommitteeRequest> findAll() {
        return committeeRepository.findAll();
    }

    public CommitteeRequest findOne(long id) {
        return committeeRepository.getById(id);
    }

    public boolean save(CommitteeRequest obj) {
        try {
            committeeRepository.save(obj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(long id) {
        CommitteeRequest tmp = committeeRepository.getById(id);
        if (!tmp.equals(null)) {
            committeeRepository.delete(tmp);
            return true;
        }
        return false;
    }
}
