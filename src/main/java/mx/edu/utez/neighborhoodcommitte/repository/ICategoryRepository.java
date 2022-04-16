package mx.edu.utez.neighborhoodcommitte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.neighborhoodcommitte.entity.Category;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
    public Category findById(long id);
}
