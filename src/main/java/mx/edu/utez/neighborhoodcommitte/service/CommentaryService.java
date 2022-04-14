package mx.edu.utez.neighborhoodcommitte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.neighborhoodcommitte.entity.Commentary;
import mx.edu.utez.neighborhoodcommitte.repository.ICommentaryRepository;

@Service
public class CommentaryService {

    @Autowired
    private ICommentaryRepository commentaryRepository;

    public List<Commentary> findAll() {
        return commentaryRepository.findAll();
    }

    public Commentary findById(long id) {
        return commentaryRepository.findById(id);
    }

    public boolean save(Commentary obj) {
        boolean flag = false;
        Commentary tmp = commentaryRepository.save(obj);
        if (!tmp.equals(null)) {
            flag = true;
        }
        return flag;
    }

    public boolean delete(long id) {
        boolean flag = false;
        Commentary tmp = commentaryRepository.findById(id);
        if (!tmp.equals(null)) {
            commentaryRepository.delete(tmp);
            flag = true;
        }
        return flag;
    }
    
}
