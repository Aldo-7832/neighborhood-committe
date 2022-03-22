package mx.edu.utez.neighborhoodcommitte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.edu.utez.neighborhoodcommitte.entity.CommitteeRequestsComentary;
import mx.edu.utez.neighborhoodcommitte.service.CommitteeRequestsComentaryService;

@Controller
@RequestMapping(value = "/committeRequestComents")
public class CommitteeRequestsComentaryController {
    
    @Autowired
    private CommitteeRequestsComentaryService committeeRequestsComentaryService;

    @GetMapping(value = "/list")
    public String findAll(Model model) {
        model.addAttribute("listCommitteeRequestsComentary", committeeRequestsComentaryService.findAll());
        return "";
    }

    @GetMapping(value = "/find/{id}")
    public String findOne(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        CommitteeRequestsComentary committeeRequestsComentary = committeeRequestsComentaryService.findOne(id);
        if (!committeeRequestsComentary.equals(null)) {
            model.addAttribute("committeRequestComentary", committeeRequestsComentary);
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró el comentario solicitado");
            return "";
        }
        
    }

    @PostMapping(value = "/save")
    public String save(Model model, CommitteeRequestsComentary committeeRequestsComentary, RedirectAttributes redirectAttributes) {
        boolean res = committeeRequestsComentaryService.save(committeeRequestsComentary);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", "Comentario guardado correctamente");
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se pudo guardar el comentario");
            return "";
        }
    }

    @PutMapping(value = "/update")
    public String update(Model model, CommitteeRequestsComentary committeeRequestsComentary, RedirectAttributes redirectAttributes) {
        boolean res = committeeRequestsComentaryService.save(committeeRequestsComentary);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", "Comentario actualizado correctamente");
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se pudo actualizar el comentario");
            return "";
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        CommitteeRequestsComentary committeeRequestsComentary = committeeRequestsComentaryService.findOne(id);
        if (!committeeRequestsComentary.equals(null)) {
            boolean res = committeeRequestsComentaryService.delete(id);
            if (res) {
                redirectAttributes.addFlashAttribute("msg_success", "Comentario eliminado correctamente");
                return "";
            } else {
                redirectAttributes.addFlashAttribute("msg_error", "No se pudo eliminar el comentario");
                return "";
            }
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró el comentario solicitado");
            return "";
        }
    }

}
