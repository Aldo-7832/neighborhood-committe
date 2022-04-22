package mx.edu.utez.neighborhoodcommitte.controller;

import mx.edu.utez.neighborhoodcommitte.entity.CommentaryObject;
import mx.edu.utez.neighborhoodcommitte.entity.Request;
import mx.edu.utez.neighborhoodcommitte.entity.Roles;
import mx.edu.utez.neighborhoodcommitte.entity.Users;
import mx.edu.utez.neighborhoodcommitte.service.CommentaryService;
import mx.edu.utez.neighborhoodcommitte.service.RequestAttachmentsService;
import mx.edu.utez.neighborhoodcommitte.service.RequestService;
import mx.edu.utez.neighborhoodcommitte.service.UsersService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping(value = "/request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private CommentaryService commentaryService;

    @Autowired
    RequestAttachmentsService attachmentsService;

    @RequestMapping(value = "/amount/{id}", method = RequestMethod.GET)
    public String amount(Model model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Request request = requestService.findById(id);
        if (!request.equals(null)) {
            model.addAttribute("request", requestService.findById(id));
            model.addAttribute("listRequests", requestService.findAll());
            return "requests/amountRequests";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "Registro No Encontrado");
            return "redirect:/request/list";
        }
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String details(Model model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        if (!requestService.findById(id).equals(null)) {
            model.addAttribute("request", requestService.findById(id));
            model.addAttribute("attachment", attachmentsService.findByRequestId(id));
            return "/requests/detailsRequests";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "La solicitud que buscas no existe");
            return "redirect:/request/list";
        }
    }

    /*
     * @RequestMapping(value = "/detalles/{id}", method = RequestMethod.GET)
     * public String detalles(Model model, @PathVariable("id") Long id,
     * RedirectAttributes redirectAttributes) {
     * Request request = requestService.findById(id);
     * if (request != null) {
     * model.addAttribute("request", requestService.findById(id));
     * return "requests/detailsRequests";
     * } else {
     * redirectAttributes.addFlashAttribute("msg_error", "Registro No Encontrado");
     * return "redirect:/request/list";
     * }
     * }
     */

    @PostMapping(value = "/update")
    public String actualizar(@ModelAttribute("request") Request request, Model modelo,
            RedirectAttributes redirectAttributes) {
        Request obj = requestService.findById(request.getId());
        obj.setPaymentAmount(request.getPaymentAmount());
        if (obj != null) {
            requestService.save(obj);
            modelo.addAttribute("listRequests", requestService.findAll());
        }
        return "requests/listRequests";

    }

    @GetMapping(value = "/list")
    public String findAll(Model model, RedirectAttributes redirectAttributes, Pageable pageable) {
        Page<Request> listRequests = requestService.listarPaginacion(PageRequest.of(pageable.getPageNumber(), 2, Sort.by("startDate").descending()));
        model.addAttribute("listRequests", listRequests);
        return "requests/listRequests";
    }

    @GetMapping("/create")
    public String create(Request request, Model modelo) {
        modelo.addAttribute("listRequests", requestService.findAll());
        return "requests/amountRequests";
    }

    @GetMapping(value = "/find/{id}")
    public String findOne(Model model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Request request = requestService.findById(id);
        if (!request.equals(null)) {
            model.addAttribute("request", request);
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró la solicitud solicitada");
        }
        return "requests/listRequests";
    }

    @PostMapping(value = "/save")
    public String save(Model model, Request request, RedirectAttributes redirectAttributes) {
        String msgOk = "";
        String msgError = "";

        if (request.getId() != null) {
            msgOk = "Solictud Actualizada correctamente";
            msgError = "La solicitud NO pudo ser Actualizada correctamente";
        } else {
            msgOk = "Solicitud Guardada correctamente";
            msgError = "La solicitud NO pudo ser Guardada correctamente";
        }

        boolean res = requestService.save(request);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", msgOk);
            return "redirect:/requests/list";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", msgError);
            return "redirect:/requests/amountRequests";
        }
    }

    @RequestMapping(value = "/commentary/{id}", method = RequestMethod.GET)
    public String chat(@PathVariable("id") long id, Authentication authentication, HttpSession session, Model model,
            RedirectAttributes redirectAttributes, CommentaryObject commentaryObject) {
        Users user = usersService.findByUsername(authentication.getName());
        user.setPassword(null);
        session.setAttribute("user", user);
        model.addAttribute("listComents", commentaryService.findAllByRequestId(id));
        model.addAttribute("request", requestService.findById(id));
        return "requests/comments";
    }

    @RequestMapping(value = "/commentary/save/{id}", method = RequestMethod.POST)
    public String saveCommentary(Model model, CommentaryObject commentaryObject, Authentication authentication,
            HttpSession session, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        Users user = usersService.findByUsername(authentication.getName());
        user.setPassword(null);
        session.setAttribute("user", user);
        commentaryObject.setRequest(requestService.findById(id));
        Users tmp = usersService.findById(user.getId());
        tmp.setPassword(usersService.findPasswordById(tmp.getId()));
        Roles tmpRole = (Roles) tmp.getRoles().toArray()[0];
        if (tmpRole.getAuthority().equals("ROL_PRESIDENTE")) {
            commentaryObject.setAutor("Presidente");
        } else {
            commentaryObject.setAutor("Enlace");
        }
        boolean res = commentaryService.save(commentaryObject);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", "Comentario publicado");
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "Ocurrió un error al publicar el comentario");
        }
        return ("redirect:/request/commentary/" + id);
    }

}
