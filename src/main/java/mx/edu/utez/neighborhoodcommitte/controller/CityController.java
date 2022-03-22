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

import mx.edu.utez.neighborhoodcommitte.entity.City;
import mx.edu.utez.neighborhoodcommitte.service.CityService;

@Controller
@RequestMapping(value ="/city")
public class CityController {
    
    @Autowired
    private CityService cityService;

    @GetMapping(value = "/list")
    public String findAll(Model model) {
        model.addAttribute("listCities", cityService.findAll());
        return "";
    }

    @GetMapping(value = "/find/{id}")
    public String findOne(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        City city = cityService.findOne(id);
        if (!city.equals(null)) {
            model.addAttribute("city", city);
        } else{
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró la ciudad solicitada");
        }
        return "";
    }

    @PostMapping(value = "/save")
    public String save(Model model, City city, RedirectAttributes redirectAttributes) {
        boolean res = cityService.save(city);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", "Ciudad guardada correctamente");
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se pudo guardar la ciudad");
            return "";
        }
    }

    @PutMapping(value = "/update")
    public String update(Model model, City city, RedirectAttributes redirectAttributes) {
        boolean res = cityService.save(city);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", "Ciudad actualizada correctamente");
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se pudo actualizar la ciudad");
            return "";
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") long id) {
        City city = cityService.findOne(id);
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
