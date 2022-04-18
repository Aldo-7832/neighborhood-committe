package mx.edu.utez.neighborhoodcommitte.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.edu.utez.neighborhoodcommitte.service.UsersService;

@Controller
public class HomeController {

    @Autowired
	private UsersService usersService;

	@Autowired
	private PasswordEncoder passwordEncoder;
    
    // @GetMapping(value = "/prueba")
    // public String index() {
    //     return "pruebaPago";
    // }

	@GetMapping("/")
	public String index() {
		return "login";
	}

	@GetMapping("/login")
	public String mostrarLogin() {
        return "login";
	}
	
	// @GetMapping("/login")

	// public String mostrarIndex(Authentication authentication, HttpSession session) {
	// 	String username = authentication.getName();
	// 	System.out.println("Username: " + username);
	// 	for(GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
	// 		System.out.println("Role: " + grantedAuthority.getAuthority());
	// 	}
	// 	Users user = usersService.findByUsername(username);
	// 	//Add data user session
	// 	System.out.println("Nombre: " + user.getName());
	// 	session.setAttribute("user", user);
	// 	return "redirect:/";
	// }

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
       try {
           SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
           logoutHandler.logout(request, null, null);
           redirectAttributes.addFlashAttribute("msg_success", "¡Sesión cerrada! Hasta luego");
       } catch (Exception e) {
           redirectAttributes.addFlashAttribute("msg_error", "Ocurrió un error al cerrar la sesión, intenta de nuevo.");
       }
       return "redirect:/login";
	}

	@GetMapping("/encriptar/{contrasena}")
	@ResponseBody
	public String encriptarContrasenas(@PathVariable("contrasena") String contrasena) {
       return contrasena + " encriptada con el algoritmo bcrypt: " + passwordEncoder.encode(contrasena);
	}


}
