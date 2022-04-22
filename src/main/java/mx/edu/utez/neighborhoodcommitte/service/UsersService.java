package mx.edu.utez.neighborhoodcommitte.service;

import java.util.List;

import mx.edu.utez.neighborhoodcommitte.entity.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mx.edu.utez.neighborhoodcommitte.entity.Users;
import mx.edu.utez.neighborhoodcommitte.repository.IUsersRepository;

@Service
public class UsersService {

    @Autowired
    private IUsersRepository usersRepository;

    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public Users findById(long id) {
        return usersRepository.findById(id);
    }

    public Page<Users> listarPaginacion(PageRequest page) {
        return usersRepository.findAll((org.springframework.data.domain.Pageable) page);
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
