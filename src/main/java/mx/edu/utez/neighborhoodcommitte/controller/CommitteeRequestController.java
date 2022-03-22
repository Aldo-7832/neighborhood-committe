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

import mx.edu.utez.neighborhoodcommitte.entity.CommitteeRequest;
import mx.edu.utez.neighborhoodcommitte.service.CommitteeRequestService;

@Controller
@RequestMapping(value = "/committeeRequest")
public class CommitteeRequestController {
    
    @Autowired
    private CommitteeRequestService committeeRequestService;
    
    @GetMapping(value = "/list")
    public String findAll(Model model) {
        model.addAttribute("listCommitteeRequests", committeeRequestService.findAll());
        return "";
    }

    @GetMapping(value = "/find/{id}")
    public String findOne(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        CommitteeRequest committeeRequest = committeeRequestService.findOne(id);
        if (!committeeRequest.equals(null)) {
            model.addAttribute("committeeRequest", committeeRequest);
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró la solicitud solicitada");
            return "";
        }
        
    }

    @PostMapping(value = "/save")
    public String save(Model model, CommitteeRequest committeeRequest, RedirectAttributes redirectAttributes) {
        boolean res = committeeRequestService.save(committeeRequest);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", "Solicitud guardada correctamente");
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se pudo guardar la solicitud");
            return "";
        }
    }

    @PutMapping(value = "/update")
    public String update(Model model, CommitteeRequest committeeRequest, RedirectAttributes redirectAttributes) {
        boolean res = committeeRequestService.save(committeeRequest);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", "Solicitud actualizada correctamente");
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se pudo actualizar la solicitud");
            return "";
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        CommitteeRequest committeeRequest = committeeRequestService.findOne(id);
        if (!committeeRequest.equals(null)) {
            boolean res = committeeRequestService.delete(id);
            if (res) {
                redirectAttributes.addFlashAttribute("msg_success", "Solicitud eliminada correctamente");
                return "";
            } else {
                redirectAttributes.addFlashAttribute("msg_error", "No se pudo eliminar la solicitud");
                return "";
            }
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró la solicitud solicitada");
            return "";
        }
    }

}
