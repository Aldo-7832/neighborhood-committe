package mx.edu.utez.neighborhoodcommitte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.neighborhoodcommitte.entity.CommentaryObject;
import mx.edu.utez.neighborhoodcommitte.repository.ICommentaryRepository;

@Service
public class CommentaryService {

    @Autowired
    private ICommentaryRepository commentaryRepository;

    public List<CommentaryObject> findAll() {
        return commentaryRepository.findAll();
    }

    public List<CommentaryObject> findAllByRequestId(long id) {
        return commentaryRepository.findAllByRequestId(id);
    }

    public CommentaryObject findById(long id) {
        return commentaryRepository.findById(id);
    }

    public boolean save(CommentaryObject obj) {
        boolean flag = false;
        CommentaryObject tmp = commentaryRepository.save(obj);
        if (!tmp.equals(null)) {
            flag = true;
        }
        return flag;
    }

    public boolean delete(long id) {
        boolean flag = false;
        CommentaryObject tmp = commentaryRepository.findById(id);
        if (!tmp.equals(null)) {
            commentaryRepository.delete(tmp);
            flag = true;
        }
        return flag;
    }
    
}
