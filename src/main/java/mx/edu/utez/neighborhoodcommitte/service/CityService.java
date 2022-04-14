package mx.edu.utez.neighborhoodcommitte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.neighborhoodcommitte.entity.City;
import mx.edu.utez.neighborhoodcommitte.repository.ICityRepository;

@Service
public class CityService {

    @Autowired
    private ICityRepository cityRepository;

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public City findById(long id) {
        return cityRepository.findById(id);
    }

    public boolean save(City obj) {
        boolean flag = false;
        City tmp = cityRepository.save(obj);
        if (!tmp.equals(null)) {
            flag = true;
        }
        return flag;
    }

    public boolean delete(long id) {
        boolean flag = false;
        City tmp = cityRepository.findById(id);
        if (!tmp.equals(null)) {
            cityRepository.delete(tmp);
            flag = true;
        }
        return flag;
    }
    
}
