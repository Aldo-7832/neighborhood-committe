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

import mx.edu.utez.neighborhoodcommitte.entity.User;
import mx.edu.utez.neighborhoodcommitte.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping(value = "/list")
    public String findAll(Model model) {
        model.addAttribute("listUsers", userService.findAll());
        return "";
    }

    @GetMapping(value = "/find/{id}")
    public String findOne(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        User user = userService.findOne(id);
        if (!user.equals(null)) {
            model.addAttribute("user", user);
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró el usuario solicitado");
            return "";
        }
    }
    
    @PostMapping(value = "/save")
    public String save(Model model, User user, RedirectAttributes redirectAttributes) {
        boolean res = userService.save(user);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", "Usuario guardado correctamente");
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se pudo guardar el usuario");
            return "";
        }
    }

    @PutMapping(value = "/update")
    public String update(Model model, User user, RedirectAttributes redirectAttributes) {
        boolean res = userService.save(user);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", "Usuario actualizado correctamente");
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se pudo actualizar el usuario");
            return "";
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        User user = userService.findOne(id);
        if (!user.equals(null)) {
            boolean res = userService.delete(id);
            if (res) {
                redirectAttributes.addFlashAttribute("msg_success", "Usuario eliminado correctamente");
                return "";
            } else {
                redirectAttributes.addFlashAttribute("msg_error", "No se pudo eliminar el usuario");
                return "";
            }
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró el usuario solicitado");
            return "";
        }
    }

}
