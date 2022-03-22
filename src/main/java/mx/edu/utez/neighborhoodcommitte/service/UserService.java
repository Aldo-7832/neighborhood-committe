package mx.edu.utez.neighborhoodcommitte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.neighborhoodcommitte.entity.User;
import mx.edu.utez.neighborhoodcommitte.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(long id) {
        return userRepository.getById(id);
    }

    public boolean save(User obj) {
        try {
            userRepository.save(obj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(long id) {
        User tmp = userRepository.getById(id);
        if (!tmp.equals(null)) {
            userRepository.delete(tmp);
            return true;
        }
        return false;
    }
    
}
