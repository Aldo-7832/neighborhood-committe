package mx.edu.utez.neighborhoodcommitte.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.edu.utez.neighborhoodcommitte.entity.Binnacle;
import mx.edu.utez.neighborhoodcommitte.service.BinnacleService;

@Controller
@RequestMapping(name = "/binnacle")
@Transactional
public class BinnacleController {

    @Autowired
    private BinnacleService binnacleService;

    @GetMapping(value = "/list")
    public String findAll(Model model) {
        model.addAttribute("binnacleList", binnacleService.findAll());
        return "";
    }

    @GetMapping(value = "/find/{id}")
    public String findOne(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        Binnacle binnacle = binnacleService.findById(id);
        if (!binnacle.equals(null)) {
            model.addAttribute("binnacle", binnacle);
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró el registro solicitado");
        }
        return "";
    }

    @GetMapping(value = "/create")
    public String create(Binnacle binnacle, Model model) {
        return "";
    }

    @PostMapping(value = "/save")
    public String save(Model model, Binnacle binnacle, RedirectAttributes redirectAttributes) {
        String msgOk = "";
        String msgError = "";

        if (binnacle.getId() != null) {
            msgOk = "Registro actualizado correctamente";
            msgError = "No se encontró el registro solicitado";
        } else {
            msgOk = "Registro guardado correctamente";
            msgError = "No se pudo guardar el registro";
        }

        boolean res = binnacleService.save(binnacle);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", msgOk);
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", msgError);
            return "";
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") long id) {
        Binnacle binnacle = binnacleService.findById(id);
        if (!binnacle.equals(null)) {
            boolean res = binnacleService.delete(id);
            if (res) {
                model.addAttribute("msg_success", "Registro eliminado correctamente");
                return "";
            } else {
                model.addAttribute("msg_error", "No se pudo eliminar el registro");
                return "";
            }
        } else {
            model.addAttribute("msg_error", "No se encontró el registro solicitado");
            return "";
        }
    }
    
}
