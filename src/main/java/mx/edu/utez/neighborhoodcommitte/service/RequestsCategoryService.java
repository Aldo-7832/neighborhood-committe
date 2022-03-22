package mx.edu.utez.neighborhoodcommitte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.neighborhoodcommitte.entity.RequestsCategory;
import mx.edu.utez.neighborhoodcommitte.repository.RequestsCategoryRepository;


@Service
public class RequestsCategoryService {
    @Autowired
    private RequestsCategoryRepository requestsCategoryRepository;

    public List<RequestsCategory> findAll() {
        return requestsCategoryRepository.findAll();
    }

    public RequestsCategory findOne(long id) {
        return requestsCategoryRepository.getById(id);
    }

    public boolean save(RequestsCategory obj) {
        try {
            requestsCategoryRepository.save(obj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(long id) {
        RequestsCategory tmp = requestsCategoryRepository.getById(id);
        if (!tmp.equals(null)) {
            requestsCategoryRepository.delete(tmp);
            return true;
        }
        return false;
    }
}
