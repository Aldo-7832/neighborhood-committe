package mx.edu.utez.neighborhoodcommitte.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.edu.utez.neighborhoodcommitte.entity.City;
import mx.edu.utez.neighborhoodcommitte.service.CityService;

@Controller
@Transactional
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping(name = "/city/list")
    public String findAll(Model model) {
        model.addAttribute("cityList", cityService.findAll());
        return "";
    }

    @GetMapping(value = "/city/find/{id}")
    public String findOne(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        City city = cityService.findById(id);
        if (!city.equals(null)) {
            model.addAttribute("category", city);
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró la categoría solicitada");
        }
        return "";
    }

    @GetMapping(value = "/city/create")
    public String create(Model model, City city) {
        return "";
    }

    @PostMapping(value = "/city/save")
    public String save(Model model, City city, RedirectAttributes redirectAttributes) {
        String msgOk = "";
        String msgError = "";

        if (city.getId() != null) {
            msgOk = "Ciudad actualizada correctamente";
            msgError = "No se encontró la ciudad solicitada";
        } else {
            msgOk = "Ciudad guardada correctamente";
            msgError = "No se pudo guardar la ciudad";
        }

        boolean res = cityService.save(city);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", msgOk);
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", msgError);
            return "";
        }
    }

    @GetMapping(value = "/city/delete/{id}")
    public String delete(Model model, @PathVariable("id") long id) {
        City city = cityService.findById(id);
        if (!city.equals(null)) {
            boolean res = cityService.delete(id);
            if (res) {
                model.addAttribute("msg_success", "Ciudad eliminada correctamente");
                return "";
            } else {
                model.addAttribute("msg_error", "No se pudo eliminar la ciudad");
                return "";
            }
        } else {
            model.addAttribute("msg_error", "No se encontró la ciudad solicitada");
            return "";
        }
    }
    
}
