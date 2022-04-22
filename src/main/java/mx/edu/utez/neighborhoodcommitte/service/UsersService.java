package mx.edu.utez.neighborhoodcommitte.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.neighborhoodcommitte.entity.Users;
import mx.edu.utez.neighborhoodcommitte.repository.IUsersRepository;

@Service
@Transactional
public class UsersService {

    @Autowired
    private IUsersRepository usersRepository;

    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public Users findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    public boolean cambiarContrasena(String password, String username) {
        try {
            usersRepository.updatePassword(password, username);
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            return false;
        }
    }

    public Users findById(long id) {
        return usersRepository.findById(id);
    }

    public String findPasswordById(long id) {
        return usersRepository.findPasswordById(id);
    }

    public boolean save(Users obj) {
        boolean flag = false;
        Users tmp = usersRepository.save(obj);
        if (!tmp.equals(null)) {
            flag = true;
        }
        return flag;
    }

    public boolean delete(long id) {
        boolean flag = false;
        Users tmp = usersRepository.findById(id);
        if (!tmp.equals(null)) {
            usersRepository.delete(tmp);
            flag = true;
        }
        return flag;
    }

    public Users findByUsername(String username) {
		return usersRepository.findByUsername(username);
	}
    
    public boolean guardar(Users user) {
       try {
        usersRepository.save(user);
           return true;
       } catch (Exception e) {
           e.printStackTrace();
           return false;
       }
    }
}
