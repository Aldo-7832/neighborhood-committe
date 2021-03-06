package mx.edu.utez.neighborhoodcommitte.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.edu.utez.neighborhoodcommitte.entity.Users;
import mx.edu.utez.neighborhoodcommitte.service.UsersService;

@Controller
public class HomeController {

	@Autowired
	private UsersService usersService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// @GetMapping(value = "/prueba")
	// public String index() {
	// return "pruebaPago";
	// }

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/login")
	public String mostrarLogin() {
		return "login";
	}

	@GetMapping("/administrador/dashboard")
	public String dashboardAdministrador(Authentication authentication, HttpSession session) {
		if (session.getAttribute("user") == null) {
			Users user = usersService.findByUsername(authentication.getName());
			user.setPassword(null);
			session.setAttribute("user", user);
		}
		return "administrador/dashboard";
	}

	@GetMapping("/enlace/dashboard")
	public String dashboardEnlace(Authentication authentication, HttpSession session) {
		if (session.getAttribute("user") == null) {
			Users user = usersService.findByUsername(authentication.getName());
			user.setPassword(null);
			session.setAttribute("user", user);
		}
		return "enlace/dashboard";
	}

	@GetMapping("/miembro/dashboard")
	public String dashboardMiembro(Authentication authentication, HttpSession session) {
		if (session.getAttribute("user") == null) {
			Users user = usersService.findByUsername(authentication.getName());
			user.setPassword(null);
			session.setAttribute("user", user);
		}
		return "miembro/dashboard";
	}

	@GetMapping("/presidente/dashboard")
	public String dashboardPresidente(Authentication authentication, HttpSession session) {
		if (session.getAttribute("user") == null) {
			Users user = usersService.findByUsername(authentication.getName());
			user.setPassword(null);
			session.setAttribute("user", user);
		}
		return "presidente/dashboard";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try {
			SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
			logoutHandler.logout(request, null, null);
			redirectAttributes.addFlashAttribute("msg_success", "??Sesi??n cerrada! Hasta luego");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg_error",
					"Ocurri?? un error al cerrar la sesi??n, intenta de nuevo.");
		}
		return "redirect:/login";
	}

	@GetMapping("/encriptar/{contrasena}")
	@ResponseBody
	public String encriptarContrasenas(@PathVariable("contrasena") String contrasena) {
		return contrasena + " encriptada con el algoritmo bcrypt: " + passwordEncoder.encode(contrasena);
	}

}
