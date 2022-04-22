package mx.edu.utez.neighborhoodcommitte.service;

import java.util.List;

import mx.edu.utez.neighborhoodcommitte.entity.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mx.edu.utez.neighborhoodcommitte.entity.Category;
import mx.edu.utez.neighborhoodcommitte.repository.ICategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findOne(long id) {
        return categoryRepository.getById(id);
    }

    public Page<Category> listarPaginacion(PageRequest page) {
        return categoryRepository.findAll((org.springframework.data.domain.Pageable) page);
    }

    public boolean save(Category obj) {
        try {
            categoryRepository.save(obj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(long id) {
        Category tmp = categoryRepository.getById(id);
        if (!tmp.equals(null)) {
            categoryRepository.delete(tmp);
            return true;
        }
        return false;
    }
    
}
