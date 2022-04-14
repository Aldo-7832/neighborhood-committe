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

import mx.edu.utez.neighborhoodcommitte.entity.Category;
import mx.edu.utez.neighborhoodcommitte.service.CategoryService;

@Controller
@RequestMapping("/category")
@Transactional
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(name = "/list")
    public String findAll(Model model) {
        model.addAttribute("categoryList", categoryService.findAll());
        return "";
    }

    @GetMapping(value = "/find/{id}")
    public String findOne(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        Category category = categoryService.findById(id);
        if (!category.equals(null)) {
            model.addAttribute("category", category);
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró la categoría solicitada");
        }
        return "";
    }

    @GetMapping(value = "/create")
    public String create(Model model, Category category) {
        return "";
    }

    @PostMapping(value = "/save")
    public String save(Model model, Category category, RedirectAttributes redirectAttributes) {
        String msgOk = "";
        String msgError = "";

        if (category.getId() != null) {
            msgOk = "Categoría actualizada correctamente";
            msgError = "No se encontró la categoría solicitada";
        } else {
            msgOk = "Categoría guardada correctamente";
            msgError = "No se pudo guardar la categoría";
        }

        boolean res = categoryService.save(category);
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
        Category category = categoryService.findById(id);
        if (!category.equals(null)) {
            boolean res = categoryService.delete(id);
            if (res) {
                model.addAttribute("msg_success", "Categoría eliminada correctamente");
                return "";
            } else {
                model.addAttribute("msg_error", "No se pudo eliminar la categoría");
                return "";
            }
        } else {
            model.addAttribute("msg_error", "No se encontró la categoría solicitada");
            return "";
        }
    }
    
}
