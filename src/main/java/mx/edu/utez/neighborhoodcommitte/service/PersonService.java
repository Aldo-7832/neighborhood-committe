package mx.edu.utez.neighborhoodcommitte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.neighborhoodcommitte.entity.Person;
import mx.edu.utez.neighborhoodcommitte.repository.PersonRepository;

@Service
public class PersonService {
    
    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findOne(long id) {
        return personRepository.getById(id);
    }

    public boolean save(Person obj) {
        try {
            personRepository.save(obj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(long id) {
        Person tmp = personRepository.getById(id);
        if (!tmp.equals(null)) {
            personRepository.delete(tmp);
            return true;
        }
        return false;
    }

}
