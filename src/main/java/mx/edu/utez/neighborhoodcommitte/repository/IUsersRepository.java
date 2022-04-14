package mx.edu.utez.neighborhoodcommitte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.neighborhoodcommitte.entity.Users;

public interface IUsersRepository extends JpaRepository<Users, Long> {
    public Users findById(long id);
}
