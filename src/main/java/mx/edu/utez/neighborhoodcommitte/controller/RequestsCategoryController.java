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
        return "";
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

    @PostMapping(value = "/save")
    public String save(Model model, RequestsCategory requestsCategory, RedirectAttributes redirectAttributes) {
        boolean res = requestCategoryService.save(requestsCategory);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", "Categoría guardada correctamente");
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se pudo guardar la categoría");
            return "";
        }
    }

    @PutMapping(value = "/update")
    public String update(Model model, RequestsCategory requestsCategory, RedirectAttributes redirectAttributes) {
        boolean res = requestCategoryService.save(requestsCategory);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", "Categoría actualizada correctamente");
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se pudo actualizar la categoría");
            return "";
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
