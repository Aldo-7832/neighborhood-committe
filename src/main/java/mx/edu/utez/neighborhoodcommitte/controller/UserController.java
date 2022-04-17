package mx.edu.utez.neighborhoodcommitte.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.edu.utez.neighborhoodcommitte.entity.Users;
import mx.edu.utez.neighborhoodcommitte.service.CommitteeService;
import mx.edu.utez.neighborhoodcommitte.service.UsersService;

@Controller
@RequestMapping(value ="/users")
public class UserController {

    @Autowired
    private UsersService userService;

    @Autowired
    private CommitteeService committeeService;

    @GetMapping(value = "/list")
    public String findAll(Model model) {
        model.addAttribute("listUsers", userService.findAll());
        return "users/listUser";
    }

    @GetMapping(value = "/find/{id}")
    public String findOne(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        Users user = userService.findById(id);
        if (!user.equals(null)) {
            model.addAttribute("user", user);
        } else{
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró el Usuario solicitado");
        }
        return "";
    }

    @GetMapping("/create")
	public String crearMascota(Users user, Model modelo) {
		modelo.addAttribute("listCommittee", committeeService.findAll());
		return "users/createUser";
	}

    @PostMapping(value = "/save")
    public String save(Model model, Users user, RedirectAttributes redirectAttributes) {
        String msgOk = "";
        String msgError = "";

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        user.setProfilePicture("imagen");
        user.setRegisteredDate(date);

        if(user.getId() != null){
            msgOk = "Usuario Actualizado correctamente";
            msgError = "El usuario NO pudo ser Actualizado correctamente";
        }else{
            msgOk = "Usuario Guardado correctamente";
            msgError = "El Usuario NO pudo ser Guardado correctamente";
        }

        boolean res = userService.save(user);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", msgOk);
            return "redirect:/users/list";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", msgError);
            return "redirect:/users/create";
        }
    }
}
