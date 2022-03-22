package mx.edu.utez.neighborhoodcommitte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.neighborhoodcommitte.entity.Suburb;
import mx.edu.utez.neighborhoodcommitte.repository.SuburbRepository;

@Service
public class SuburbService {

    @Autowired
    private SuburbRepository suburbRepository;

    public List<Suburb> findAll() {
        return suburbRepository.findAll();
    }

    public Suburb findOne(long id) {
        return suburbRepository.getById(id);
    }

    public boolean save(Suburb obj) {
        try {
            suburbRepository.save(obj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(long id) {
        Suburb tmp = suburbRepository.getById(id);
        if (!tmp.equals(null)) {
            suburbRepository.delete(tmp);
            return true;
        }
        return false;
    }
    
}
