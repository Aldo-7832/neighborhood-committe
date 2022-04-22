package mx.edu.utez.neighborhoodcommitte.controller;

import mx.edu.utez.neighborhoodcommitte.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.edu.utez.neighborhoodcommitte.entity.Suburb;
import mx.edu.utez.neighborhoodcommitte.security.BlacklistController;
import mx.edu.utez.neighborhoodcommitte.service.SuburbService;

@Controller
@RequestMapping(value = "/suburb")
public class SuburbController {

    @Autowired
    private CityService cityService;

    @Autowired
    private SuburbService suburbService;

    @GetMapping(value = "/list")
    public String findAll(Model model, Pageable pageable) {
        Page<Suburb> listSuburbs = suburbService
                .listarPaginacion(PageRequest.of(pageable.getPageNumber(), 4, Sort.by("postalCode").descending()));
        model.addAttribute("listSuburbs", listSuburbs);
        return "suburb/listSuburb";
    }

    @GetMapping(value = "/find/{id}")
    public String findOne(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        Suburb suburb = suburbService.findOne(id);
        if (!suburb.equals(null)) {
            model.addAttribute("suburb", suburb);

        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró el asentamiento solicitado");

        }
        return "";
    }

    @GetMapping("/create")
    public String crearMascota(Suburb suburb, Model modelo) {
        modelo.addAttribute("listCities", cityService.findAll());
        return "suburb/createSuburb";
    }

    @PostMapping(value = "/save")
    public String save(Model model, Suburb suburb, RedirectAttributes redirectAttributes) {
        String msgOk = "";
        String msgError = "";

        if (!(BlacklistController.checkBlacklistedWords(suburb.getName())
                || BlacklistController.checkBlacklistedWords(suburb.getPostalCode()))) {
            if (suburb.getId() != null) {
                msgOk = "Colonia Actualizada correctamente";
                msgError = "La colonia NO pudo ser Actualizada correctamente";
            } else {
                msgOk = "Colonia Guardada correctamente";
                msgError = "La colonia NO pudo ser Guardada correctamente";
            }

            boolean res = suburbService.save(suburb);
            if (res) {
                redirectAttributes.addFlashAttribute("msg_success", msgOk);
                return "redirect:/suburb/list";
            } else {
                redirectAttributes.addFlashAttribute("msg_error", msgError);
                return "redirect:/suburb/create";
            }
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "Ingresó una o más palabras prohibidas.");
            return "redirect:/suburb/create";
        }
    }

    @GetMapping(value = "/update/{id}")
    public String update(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        Suburb res = suburbService.findOne(id);
        if (res != null) {
            model.addAttribute("suburb", res);
            model.addAttribute("listCities", cityService.findAll());
            return "suburb/createSuburb";
        } else {
            return "suburb/listSuburb";
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        Suburb suburb = suburbService.findOne(id);
        if (!suburb.equals(null)) {
            boolean res = suburbService.delete(id);
            if (res) {
                redirectAttributes.addFlashAttribute("msg_success", "Colonia eliminada correctamente");
                return "redirect:/suburb/list";
            } else {
                redirectAttributes.addFlashAttribute("msg_error", "No se pudo eliminar la colonia");
                return "redirect:/suburb/list";
            }
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró la colonia solicitada");
            return "redirect:/suburb/list";
        }
    }

}
