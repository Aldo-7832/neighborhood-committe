package mx.edu.utez.neighborhoodcommitte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.neighborhoodcommitte.entity.Roles;
import mx.edu.utez.neighborhoodcommitte.repository.IRolesRepository;

@Service
public class RolesService {

    @Autowired
    private IRolesRepository rolesRepository;

    public List<Roles> findAll() {
        return rolesRepository.findAll();
    }

    public Roles findById(long id) {
        return rolesRepository.findById(id);
    }

    public boolean save(Roles obj) {
        boolean flag = false;
        Roles tmp = rolesRepository.save(obj);
        if (!tmp.equals(null)) {
            flag = true;
        }
        return flag;
    }

    public boolean delete(long id) {
        boolean flag = false;
        Roles tmp = rolesRepository.findById(id);
        if (!tmp.equals(null)) {
            flag = true;
            rolesRepository.delete(tmp);
        }
        return flag;
    }

    public Roles findByAuthority(String authority) {
		return rolesRepository.findByAuthority(authority);
	}    
}
