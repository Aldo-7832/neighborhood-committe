package mx.edu.utez.neighborhoodcommitte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Category findById(long id) {
        return categoryRepository.findById(id);
    }

    public boolean save(Category obj) {
        boolean flag = false;
        Category tmp = categoryRepository.save(obj);
        if (!tmp.equals(null)) {
            flag = true;
        }
        return flag;
    }

    public boolean delete(long id) {
        boolean flag = false;
        Category tmp = categoryRepository.findById(id);
        if (!tmp.equals(null)) {
            categoryRepository.delete(tmp);
            flag = true;
        }
        return flag;
    }
    
}
