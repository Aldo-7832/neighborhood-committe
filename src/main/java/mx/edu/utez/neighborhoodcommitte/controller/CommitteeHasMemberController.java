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

import mx.edu.utez.neighborhoodcommitte.entity.CommitteeHasMember;
import mx.edu.utez.neighborhoodcommitte.service.CommitteeHasMemberService;

@Controller
@RequestMapping(value = "/committeeHasMember")
public class CommitteeHasMemberController {
    
    @Autowired
    private CommitteeHasMemberService committeeHasMemberService;

    @GetMapping(value = "/list")
    public String findAll(Model model) {
        model.addAttribute("listCommitteeHasMembers", committeeHasMemberService.findAll());
        return "";
    }

    @GetMapping(value = "/find/{id}")
    public String findOne(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        CommitteeHasMember committeeHasMember = committeeHasMemberService.findOne(id);
        if (!committeeHasMember.equals(null)) {
            model.addAttribute("committeeHasMember", committeeHasMember);
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró el registro solicitado");
            return "";
        }
    }
    
    @PostMapping(value = "/save")
    public String save(Model model, CommitteeHasMember committeeHasMember, RedirectAttributes redirectAttributes) {
        boolean res = committeeHasMemberService.save(committeeHasMember);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", "Registro guardado correctamente");
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se pudo guardar el registro");
            return "";
        }
    }

    @PutMapping(value = "/update")
    public String update(Model model, CommitteeHasMember committeeHasMember, RedirectAttributes redirectAttributes) {
        boolean res = committeeHasMemberService.save(committeeHasMember);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", "Registro actualizado correctamente");
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se pudo actualizar el registro");
            return "";
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        CommitteeHasMember committeeHasMember = committeeHasMemberService.findOne(id);
        if (!committeeHasMember.equals(null)) {
            boolean res = committeeHasMemberService.delete(id);
            if (res) {
                redirectAttributes.addFlashAttribute("msg_success", "Registro eliminado correctamente");
                return "";
            } else {
                redirectAttributes.addFlashAttribute("msg_error", "No se pudo eliminar el registro");
                return "";
            }
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró el registro solicitado");
            return "";
        }
    }

}
