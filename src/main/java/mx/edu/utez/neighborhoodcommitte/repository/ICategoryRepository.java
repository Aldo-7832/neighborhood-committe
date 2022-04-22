package mx.edu.utez.neighborhoodcommitte.repository;

import mx.edu.utez.neighborhoodcommitte.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.neighborhoodcommitte.entity.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> , PagingAndSortingRepository<Category, Long> {
    public Category findById(long id);
}
