package mx.edu.utez.neighborhoodcommitte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.neighborhoodcommitte.entity.Binnacle;
import mx.edu.utez.neighborhoodcommitte.repository.IBinnacleRepository;

@Service
public class BinnacleService {

    @Autowired
    private IBinnacleRepository binnacleRepository;

    public List<Binnacle> findAll() {
        return binnacleRepository.findAll();
    }

    public Binnacle findById(long id) {
        return binnacleRepository.findById(id);
    }

    public boolean save(Binnacle obj) {
        boolean flag = false;
        Binnacle tmp = binnacleRepository.save(obj);
        if (!tmp.equals(null)) {
            flag = true;
        }
        return flag;
    }

    public boolean delete(long id) {
        boolean flag = false;
        Binnacle tmp = binnacleRepository.findById(id);
        if (!tmp.equals(null)) {
            binnacleRepository.delete(tmp);
            flag = true;
        }
        return flag;
    }
    
}
