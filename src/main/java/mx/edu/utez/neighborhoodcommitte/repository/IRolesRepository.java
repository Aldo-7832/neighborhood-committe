package mx.edu.utez.neighborhoodcommitte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.neighborhoodcommitte.entity.Roles;

public interface IRolesRepository extends JpaRepository<Roles, Long> {
    public Roles findById(long id);
}
