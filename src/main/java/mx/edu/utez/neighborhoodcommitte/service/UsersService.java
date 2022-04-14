package mx.edu.utez.neighborhoodcommitte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    
}
