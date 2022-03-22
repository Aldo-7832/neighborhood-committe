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

import mx.edu.utez.neighborhoodcommitte.entity.Person;
import mx.edu.utez.neighborhoodcommitte.service.PersonService;

@Controller
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/list")
    public String findAll(Model model) {
        model.addAttribute("listPersons", personService.findAll());
        return "";
    }

    @GetMapping(value = "/find/{id}")
    public String findOne(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        Person person = personService.findOne(id);
        if (!person.equals(null)) {
            model.addAttribute("person", person);
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró la persona solicitada");
            return "";
        }
    }

    @PostMapping(value = "/save")
    public String save(Model model, Person person, RedirectAttributes redirectAttributes) {
        boolean res = personService.save(person);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", "Persona guardada correctamente");
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se pudo guardar la persona");
            return "";
        }
    }

    @PutMapping(value = "/update")
    public String update(Model model, Person person, RedirectAttributes redirectAttributes) {
        boolean res = personService.save(person);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", "Persona actualizada correctamente");
            return "";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se pudo actualizar la persona");
            return "";
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        Person person = personService.findOne(id);
        if (!person.equals(null)) {
            boolean res = personService.delete(id);
            if (res) {
                redirectAttributes.addFlashAttribute("msg_success", "Persona eliminada correctamente");
                return "";
            } else {
                redirectAttributes.addFlashAttribute("msg_error", "No se pudo eliminar la persona");
                return "";
            }
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró la persona solicitada");
            return "";
        }
    }
    
}
