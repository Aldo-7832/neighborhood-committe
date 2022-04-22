package mx.edu.utez.neighborhoodcommitte.service;

import java.util.List;

import mx.edu.utez.neighborhoodcommitte.entity.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public City findOne(long id) {
        return cityRepository.getById(id);
    }

    public Page<City> listarPaginacion(PageRequest page) {
        return cityRepository.findAll((org.springframework.data.domain.Pageable) page);
    }

    public boolean save(City obj) {
        try {
            cityRepository.save(obj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(long id) {
        City tmp = cityRepository.getById(id);
        if (!tmp.equals(null)) {
            cityRepository.delete(tmp);
            return true;
        }
        return false;
    }
    
}
