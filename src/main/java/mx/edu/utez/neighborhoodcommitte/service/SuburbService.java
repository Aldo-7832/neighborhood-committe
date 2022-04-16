package mx.edu.utez.neighborhoodcommitte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.neighborhoodcommitte.entity.Suburb;
import mx.edu.utez.neighborhoodcommitte.repository.ISuburbRepository;

@Service
public class SuburbService {

    @Autowired
    private ISuburbRepository suburbRepository;

    public List<Suburb> findAll() {
        return suburbRepository.findAll();
    }

    public Suburb findById(long id) {
        return suburbRepository.findById(id);
    }

    public boolean save(Suburb obj) {
        boolean flag = false;
        Suburb tmp = suburbRepository.save(obj);
        if (!tmp.equals(null)) {
            flag = true;
        }
        return flag;
    }

    public boolean delete(long id) {
        boolean flag = false;
        Suburb tmp = suburbRepository.findById(id);
        if (!tmp.equals(null)) {
            flag = true;
            suburbRepository.delete(tmp);
        }
        return flag;
    }
    
}
