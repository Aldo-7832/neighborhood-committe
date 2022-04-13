package mx.edu.utez.neighborhoodcommitte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.edu.utez.neighborhoodcommitte.entity.RequestsCategory;
import mx.edu.utez.neighborhoodcommitte.service.RequestsCategoryService;

@Controller
@RequestMapping(value = "/requestsCategory")
public class RequestsCategoryController {

    @Autowired
    private RequestsCategoryService requestCategoryService;

    @GetMapping(value = "/list")
    public String findAll(Model model) {
        model.addAttribute("listRequestsCategory", requestCategoryService.findAll());
        return "requestsCategory/listRequests";
    }

    @GetMapping(value = "/find/{id}")
    public String findOne(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        RequestsCategory requestCategory = requestCategoryService.findOne(id);
        if (!requestCategory.equals(null)) {
            model.addAttribute("requestCategory", requestCategory);
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró la categoría solicitada");
            return "";
        }
    }

    @GetMapping("/create")
	public String crearMascota(RequestsCategory request, Model modelo) {
		return "requestsCategory/createRequests";
	}

    @PostMapping(value = "/save")
    public String save(Model model, RequestsCategory requests, RedirectAttributes redirectAttributes) {
        String msgOk = "";
        String msgError = "";

        if(requests.getId() != null){
            msgOk = "Servicio Publico Actualizado correctamente";
            msgError = "El Servicio Publico NO pudo ser Actualizada correctamente";
        }else{
            msgOk = "Servicio Publico Guardado correctamente";
            msgError = "El Servicio Publico NO pudo ser Guardado correctamente";
        }

        boolean res = requestCategoryService.save(requests);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", msgOk);
            return "redirect:/requestsCategory/list";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", msgError);
            return "redirect:/requestsCategory/create";
        }
    }

    @GetMapping(value = "/update/{id}")
    public String update(@PathVariable long id, Model modelo, RedirectAttributes redirectAttributes) {
        RequestsCategory request = requestCategoryService.findOne(id);
        if (request != null) {
            modelo.addAttribute("requestsCategory", request);
            return "requestsCategory/createRequests";
        }else{
            return "requestsCategory/listRequests";
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        RequestsCategory requestCategory = requestCategoryService.findOne(id);
        if (!requestCategory.equals(null)) {
            boolean res = requestCategoryService.delete(id);
            if (res) {
                redirectAttributes.addFlashAttribute("msg_success", "Categoría eliminada correctamente");
                return "";
            } else {
                redirectAttributes.addFlashAttribute("msg_error", "No se pudo eliminar la categoría");
                return "";
            }
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró la categoría solicitada");
            return "";
        }
    }
    
}
