package mx.edu.utez.neighborhoodcommitte.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import mx.edu.utez.neighborhoodcommitte.entity.Roles;
import mx.edu.utez.neighborhoodcommitte.entity.Users;
import mx.edu.utez.neighborhoodcommitte.security.BlacklistController;
import mx.edu.utez.neighborhoodcommitte.service.CommitteeService;
import mx.edu.utez.neighborhoodcommitte.service.RolesService;
import mx.edu.utez.neighborhoodcommitte.service.UsersService;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UsersService userService;

    @Autowired
    private RolesService roleService;

    @Autowired
    private CommitteeService committeeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/list")
    public String findAll(Model model, Pageable pageable) {
        Page<Users> listUsers = userService
                .listarPaginacion(PageRequest.of(pageable.getPageNumber(), 4, Sort.by("name").ascending()));
        model.addAttribute("listUsers", listUsers);
        return "users/listUser";
    }

    @GetMapping(value = "/find/{id}")
    public String findOne(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        Users user = userService.findById(id);
        if (!user.equals(null)) {
            model.addAttribute("user", user);
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró el Usuario solicitado");
        }
        return "";
    }

    @GetMapping("/create")
    public String crearMascota(Users user, Model modelo) {
        modelo.addAttribute("listCommittee", committeeService.findAll());
        return "users/createUser";
    }

    @GetMapping("/disable/{id}")
    public String disableUser(@PathVariable("id") long id, RedirectAttributes redirectAttributes,
            Authentication authentication, HttpSession session) {
        Users user = userService.findByUsername(authentication.getName());
        user.setPassword(null);
        session.setAttribute("user", user);
        user.setPassword(userService.findPasswordById(user.getId()));
        Users tmp = userService.findById(id);
        tmp.setPassword(userService.findPasswordById(id));
        if (user.getUsername().equals(tmp.getUsername())) {
            redirectAttributes.addFlashAttribute("msg_error", "No puedes deshabilitarte");
            return "redirect:/users/list";
        } else {
            if (tmp.getEnabled() == 1) {
                tmp.setEnabled(0);
                redirectAttributes.addFlashAttribute("msg_success", "Usuario deshabilitado");
            } else {
                tmp.setEnabled(1);
                redirectAttributes.addFlashAttribute("msg_success", "Usuario habilitado");
            }
        }
        userService.save(tmp);
        return "redirect:/users/list";
    }

    @PostMapping("/signup")
    public String guardarUsuario(@RequestParam("tipoUsuario") String tipoUsuario, Users user,
            RedirectAttributes redirectAttributes) {
        Date date = new Date();
        user.setProfilePicture("imagen");
        user.setRegisteredDate(date);
        user.setEnabled(1);
        if (!(BlacklistController.checkBlacklistedWords(user.getEmail())
                || BlacklistController.checkBlacklistedWords(user.getEmployeeNumber())
                || BlacklistController.checkBlacklistedWords(user.getLastName())
                || BlacklistController.checkBlacklistedWords(user.getName())
                || BlacklistController.checkBlacklistedWords(user.getPassword())
                || BlacklistController.checkBlacklistedWords(user.getPhone())
                || BlacklistController.checkBlacklistedWords(user.getProfilePicture())
                || BlacklistController.checkBlacklistedWords(user.getSurname())
                || BlacklistController.checkBlacklistedWords(user.getUsername()))) {
            String contrasenaPlano = user.getPassword();
            String contrasenaEncriptada = passwordEncoder.encode(contrasenaPlano);
            user.setPassword(contrasenaEncriptada);

            Roles role = null;
            if (tipoUsuario.equals("opcionEnlace")) {
                role = roleService.findByAuthority("ROL_ENLACE");
            } else if (tipoUsuario.equals("opcionPresidente")) {
                role = roleService.findByAuthority("ROL_PRESIDENTE");
            }
            user.agregarRol(role);

            boolean respuesta = userService.guardar(user);
            if (respuesta) {
                redirectAttributes.addFlashAttribute("msg_success",
                        "Se Registro Exitoso. Ya Puede Iniciar Sesión el Nuevo Usuario");
                return "redirect:/users/list";
            } else {
                redirectAttributes.addFlashAttribute("msg_error", "¡Registro fallido! Por favor intenta de nuevo.");
                return "redirect:/signup";
            }
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "Ingresó una o más palabras prohibidas.");
            return "redirect:/signup";
        }
    }
}
