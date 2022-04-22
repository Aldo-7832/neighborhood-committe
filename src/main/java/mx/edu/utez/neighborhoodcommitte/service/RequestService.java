package mx.edu.utez.neighborhoodcommitte.service;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import mx.edu.utez.neighborhoodcommitte.entity.Request;
import mx.edu.utez.neighborhoodcommitte.repository.IRequestRepository;

@Service
public class RequestService {

    @Autowired
    private IRequestRepository requestRepository;

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public List<Request> findAllByCommitteeId(long id) {
        return requestRepository.findAllByCommitteeId(id);
    }

    public List<Request> findAllUnpaidByCommitteeId(long id) {
        return requestRepository.findAllUnpaidByCommitteeId(id);
    }

    public Page<Request> listarPaginacion(PageRequest page) {
        return requestRepository.findAll((org.springframework.data.domain.Pageable) page);
    }

    public Request findById(long id) {

        return requestRepository.getById(id);
    }

    public boolean save(Request obj) {
        try {
            requestRepository.save(obj);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(long id) {
        boolean flag = false;
        Request tmp = requestRepository.findById(id);
        if (!tmp.equals(null)) {
            requestRepository.delete(tmp);
            flag = true;
        }
        return flag;
    }

    public Page<Request> listarPaginacion(Pageable ofSize, int i, Sort startDate) {
        return requestRepository.findAll((org.springframework.data.domain.Pageable) ofSize);
    }
}
