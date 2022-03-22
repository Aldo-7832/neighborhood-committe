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

import mx.edu.utez.neighborhoodcommitte.entity.Committee;
import mx.edu.utez.neighborhoodcommitte.service.CommitteeService;

@Controller
@RequestMapping(value = "/committee")
public class CommitteeController {

    @Autowired
    private CommitteeService committeeService;

    @GetMapping(value = "/list")
    public String findAll(Model model) {
        model.addAttribute("listCommittees", committeeService.findAll());
        return "";
    }

    @GetMapping(value = "/find/{id}")
    public String findOne(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        Committee committee = committeeService.findOne(id);
        if (!committee.equals(null)) {
            model.addAttribute("committee", committee);
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró el comité solicitado");
            return "";
        }
    }

    @PostMapping(value = "/save")
    public String save(Model model, Committee committee, RedirectAttributes redirectAttributes) {
        boolean res = committeeService.save(committee);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", "Comité guardado correctamente");
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se pudo guardar el comité");
            return "";
        }
    }

    @PutMapping(value = "/update")
    public String update(Model model, Committee committee, RedirectAttributes redirectAttributes) {
        boolean res = committeeService.save(committee);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", "Comité actualizado correctamente");
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se pudo actualizar el comité");
            return "";
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        Committee committee = committeeService.findOne(id);
        if (!committee.equals(null)) {
            boolean res = committeeService.delete(id);
            if (res) {
                redirectAttributes.addFlashAttribute("msg_success", "Comité eliminado correctamente");
                return "";
            } else {
                redirectAttributes.addFlashAttribute("msg_error", "No se pudo eliminar el comité");
                return "";
            }
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró el comité solicitado");
            return "";
        }
    }
    
}
