package mx.edu.utez.neighborhoodcommitte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.neighborhoodcommitte.entity.CommitteeHasMember;
import mx.edu.utez.neighborhoodcommitte.repository.CommitteeHasMemberRepository;

@Service
public class CommitteeHasMemberService {

    @Autowired
    private CommitteeHasMemberRepository committeeRepository;

    public List<CommitteeHasMember> findAll() {
        return committeeRepository.findAll();
    }

    public CommitteeHasMember findOne(long id) {
        return committeeRepository.getById(id);
    }

    public boolean save(CommitteeHasMember obj) {
        try {
            committeeRepository.save(obj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(long id) {
        CommitteeHasMember tmp = committeeRepository.getById(id);
        if (!tmp.equals(null)) {
            committeeRepository.delete(tmp);
            return true;
        }
        return false;
    }
    
}
