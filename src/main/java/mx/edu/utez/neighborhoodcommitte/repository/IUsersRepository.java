package mx.edu.utez.neighborhoodcommitte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.edu.utez.neighborhoodcommitte.entity.Users;

public interface IUsersRepository extends JpaRepository<Users, Long> {
    public Users findById(long id);

    Users findByUsername(String username);

    Users findByEmail(String email);

    @Query(value = "SELECT u.password FROM users u WHERE u.id = :id", nativeQuery = true)
    String findPasswordById(@Param("id") long id);

    @Modifying
    @Query(value = "update users u set u.password = :password where u.email = :username", nativeQuery = true)
    void updatePassword(@Param("password") String password, @Param("username") String username);
}
