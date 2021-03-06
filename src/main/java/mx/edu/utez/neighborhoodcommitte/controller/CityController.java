package mx.edu.utez.neighborhoodcommitte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.edu.utez.neighborhoodcommitte.entity.City;
import mx.edu.utez.neighborhoodcommitte.security.BlacklistController;
import mx.edu.utez.neighborhoodcommitte.service.CityService;
import mx.edu.utez.neighborhoodcommitte.service.StateService;

@Controller
@RequestMapping(value ="/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private StateService stateService;

    @GetMapping(value = "/list")
    public String findAll(Model model, Pageable pageable) {
        Page<City> listCities = cityService.listarPaginacion(PageRequest.of(pageable.getPageNumber(), 110, Sort.by("name").ascending()));
        model.addAttribute("listCities", listCities);
        model.addAttribute("listStates", stateService.findAll());
        return "city/listCities";
    }

    @GetMapping(value = "/find/{id}")
    public String findOne(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        City city = cityService.findOne(id);
        if (!city.equals(null)) {
            model.addAttribute("city", city);
        } else{
            redirectAttributes.addFlashAttribute("msg_error", "No se encontrĂ³ la ciudad solicitada");
        }
        return "users/createUser";
    }

    @GetMapping("/create")
	public String crearMascota(City city, Model modelo) {
		modelo.addAttribute("listStates", stateService.findAll());
		return "city/createCity";
	}

    @PostMapping(value = "/save")
    public String save(Model model, City city, RedirectAttributes redirectAttributes) {
        String msgOk = "";
        String msgError = "";

        if (!BlacklistController.checkBlacklistedWords(city.getName())) {
            if(city.getId() != null){
                msgOk = "Ciudad Actualizada correctamente";
                msgError = "La ciudad NO pudo ser Actualizada correctamente";
            }else{
                msgOk = "Ciudad Guardada correctamente";
                msgError = "La ciudad NO pudo ser Guardada correctamente";
            }
    
            boolean res = cityService.save(city);
            if (res) {
                redirectAttributes.addFlashAttribute("msg_success", msgOk);
                return "redirect:/city/list";
            } else {
                redirectAttributes.addFlashAttribute("msg_error", msgError);
                return "redirect:/city/create";
            }
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "IngresĂ³ una o mĂ¡s palabras prohibidas.");
            return "redirect:/city/create";
        } 
    }

    @GetMapping(value = "/update/{id}")
    public String update(@PathVariable long id, Model modelo, RedirectAttributes redirectAttributes) {
        City city = cityService.findOne(id);
        if (city != null) {
            modelo.addAttribute("city", city);
            modelo.addAttribute("listStates", stateService.findAll());
            return "city/createCity";
        }else{
            return "city/listCities";
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
            model.addAttribute("msg_error", "No se encontrĂ³ la ciudad solicitada");
            return "";
        }
    }
    
}
