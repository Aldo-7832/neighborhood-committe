package mx.edu.utez.neighborhoodcommitte.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.edu.utez.neighborhoodcommitte.entity.Category;
import mx.edu.utez.neighborhoodcommitte.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService requestCategoryService;

    @GetMapping(value = "/list")
    public String findAll(Model model) {
        model.addAttribute("listRequestsCategory", requestCategoryService.findAll());
        return "category/listCategory";
    }

    @GetMapping(value = "/find/{id}")
    public String findOne(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        Category requestCategory = requestCategoryService.findOne(id);
        if (!requestCategory.equals(null)) {
            model.addAttribute("requestCategory", requestCategory);
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró la categoría solicitada");
            return "";
        }
    }

    @GetMapping("/create")
	public String crearMascota(Category request, Model modelo) {
		return "category/createCategory";
	}

    @PostMapping(value = "/save")
    public String save(Model model, Category category, RedirectAttributes redirectAttributes) {
        String msgOk = "";
        String msgError = "";

        if(category.getId() != null){
            msgOk = "Servicio Publico Actualizado correctamente";
            msgError = "El Servicio Publico NO pudo ser Actualizada correctamente";
        }else{
            msgOk = "Servicio Publico Guardado correctamente";
            msgError = "El Servicio Publico NO pudo ser Guardado correctamente";
        }

        boolean res = requestCategoryService.save(category);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", msgOk);
            return "redirect:/category/list";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", msgError);
            return "redirect:/category/create";
        }
    }

    @GetMapping(value = "/update/{id}")
    public String update(@PathVariable long id, Model modelo, RedirectAttributes redirectAttributes) {
        Category request = requestCategoryService.findOne(id);
        if (request != null) {
            modelo.addAttribute("requestsCategory", request);
            return "category/createRequests";
        }else{
            return "category/listRequests";
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        Category requestCategory = requestCategoryService.findOne(id);
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
