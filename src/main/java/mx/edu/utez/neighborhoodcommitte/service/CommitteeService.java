package mx.edu.utez.neighborhoodcommitte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.neighborhoodcommitte.entity.Committee;
import mx.edu.utez.neighborhoodcommitte.repository.CommitteeRepository;

@Service
public class CommitteeService {
    
    @Autowired
    private CommitteeRepository committeeRepository;

    public List<Committee> findAll() {
        return committeeRepository.findAll();
    }

    public Committee findOne(long id) {
        return committeeRepository.getById(id);
    }

    public boolean save(Committee obj) {
        try {
            committeeRepository.save(obj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(long id) {
        Committee tmp = committeeRepository.getById(id);
        if (!tmp.equals(null)) {
            committeeRepository.delete(tmp);
            return true;
        }
        return false;
    }

}
