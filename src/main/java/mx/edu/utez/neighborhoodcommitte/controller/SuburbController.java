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

import mx.edu.utez.neighborhoodcommitte.entity.Suburb;
import mx.edu.utez.neighborhoodcommitte.service.SuburbService;

@Controller
@RequestMapping(value = "/suburb")
public class SuburbController {
    
    @Autowired
    private SuburbService suburbService;

    @GetMapping(value = "/list")
    public String findAll(Model model) {
        model.addAttribute("listSuburbs", suburbService.findAll());
        return "";
    }

    @GetMapping(value = "/find/{id}")
    public String findOne(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        Suburb suburb = suburbService.findOne(id);
        if (!suburb.equals(null)) {
            model.addAttribute("suburb", suburb);
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró el asentamiento solicitado");
            return "";
        }
    }

    @PostMapping(value = "/save")
    public String save(Model model, Suburb suburb, RedirectAttributes redirectAttributes) {
        boolean res = suburbService.save(suburb);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", "Asentamiento guardado correctamente");
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se pudo guardar el asentamiento");
            return "";
        }
    }

    @PutMapping(value = "/update")
    public String update(Model model, Suburb suburb, RedirectAttributes redirectAttributes) {
        boolean res = suburbService.save(suburb);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", "Asentamiento actualizado correctamente");
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se pudo actualizar el asentamiento");
            return "";
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        Suburb suburb = suburbService.findOne(id);
        if (!suburb.equals(null)) {
            boolean res = suburbService.delete(id);
            if (res) {
                redirectAttributes.addFlashAttribute("msg_success", "Asentamiento eliminado correctamente");
                return "";
            } else {
                redirectAttributes.addFlashAttribute("msg_error", "No se pudo eliminar el asentamiento");
                return "";
            }
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró el asentamiento solicitado");
            return "";
        }
    }

}
