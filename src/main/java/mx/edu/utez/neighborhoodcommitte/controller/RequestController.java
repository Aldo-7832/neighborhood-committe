package mx.edu.utez.neighborhoodcommitte.controller;

import mx.edu.utez.neighborhoodcommitte.entity.Request;
import mx.edu.utez.neighborhoodcommitte.entity.RequestAttachments;
import mx.edu.utez.neighborhoodcommitte.entity.Users;
import mx.edu.utez.neighborhoodcommitte.entity.dto.RequestDto;
import mx.edu.utez.neighborhoodcommitte.service.RequestService;
import mx.edu.utez.neighborhoodcommitte.service.CategoryService;
import mx.edu.utez.neighborhoodcommitte.service.RequestAttachmentsService;
import mx.edu.utez.neighborhoodcommitte.service.UsersService;
import mx.edu.utez.neighborhoodcommitte.util.ImagenUtileria;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/request")
public class RequestController {
    @Autowired
    private RequestService requestService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RequestAttachmentsService attachmentsService;

    @RequestMapping(value = "/amount/{id}", method = RequestMethod.GET)
    public String amount(Model model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Request request = requestService.findById(id);
        if (!request.equals(null)) {
            model.addAttribute("request", requestService.findById(id));
            model.addAttribute("listRequests", requestService.findAll());
            return "requests/detailsRequests";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "Registro No Encontrado");
            return "redirect:/requests/listRequests";
        }
    }

    @RequestMapping(value = "/president/list", method = RequestMethod.GET)
    public String listAllPresidentRequests(Authentication authentication, HttpSession session, Model model,
            RedirectAttributes redirectAttributes) {
        Users user = usersService.findByUsername(authentication.getName());
        user.setPassword(null);
        session.setAttribute("user", user);
        model.addAttribute("requestList", requestService.findAllByCommitteeId(user.getCommittee().getId()));
        return "requests/president/list";
    }

    @RequestMapping(value = "/president/create", method = RequestMethod.GET)
    public String createPresidentRequest(Authentication authentication, HttpSession session, Model model,
            RedirectAttributes redirectAttributes, RequestDto requestDto) {
        Users user = usersService.findByUsername(authentication.getName());
        user.setPassword(null);
        session.setAttribute("user", user);
        model.addAttribute("categoryList", categoryService.findAll());
        return "requests/president/create";
    }

    @RequestMapping(value = "/president/save", method = RequestMethod.POST)
    public String savePresidentRequest(Authentication authentication, HttpSession session, Model model,
            RedirectAttributes redirectAttributes, RequestDto requestDto,
            @RequestParam("attachment") MultipartFile multipartFile) {
        Users user = usersService.findByUsername(authentication.getName());
        user.setPassword(null);
        session.setAttribute("user", user);
        Request obj = new Request();
        obj.setCategory(requestDto.getCategory());
        obj.setDescription(requestDto.getDescription());
        obj.setStartDate(new Date());
        obj.setStatus(1);
        obj.setPaymentStatus(1);
        obj.setUser(usersService.findById(user.getId()));
        obj.getUser().setPassword(usersService.findPasswordById(user.getId()));
        boolean res1 = requestService.save(obj);
        if (res1) {
            if (!multipartFile.isEmpty()) {
                RequestAttachments attachments = new RequestAttachments();
                String path = "C:/comve/docs";
                String filename = ImagenUtileria.guardarImagen(multipartFile, path);
                if (filename != null) {
                    attachments.setName(filename);
                    attachments.setRequest(obj);
                    boolean res2 = attachmentsService.save(attachments);
                    if (res2) {
                        redirectAttributes.addFlashAttribute("msg_success", "¡Se registró la solicitud con la evidencia!");
                        return "redirect:/request/president/list";
                    } else {
                        redirectAttributes.addFlashAttribute("msg_error", "Ocurrió un error al registrar la solicitud con la evidencia");
                        return "redirect:/request/president/create";
                    }
                } else {
                    redirectAttributes.addFlashAttribute("msg_success", "¡Se registró la solicitud correctamente!");
                    return "redirect:/request/president/list";
                }
            }
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "Ocurrió un error al registrar la solicitud");
            return "redirect:/request/president/create";
        }
        redirectAttributes.addFlashAttribute("msg_error", "Ocurrió un fallo");
        return "redirect:/request/president/create";
    }

    @RequestMapping(value = "/detalles/{id}", method = RequestMethod.GET)
    public String detalles(Model model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Request request = requestService.findById(id);
        if (request != null) {
            model.addAttribute("request", requestService.findById(id));
            return "requests/amountRequests";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "Registro No Encontrado");
            return "redirect:/requests/listRequests";
        }
    }

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
    public String findAll(Model model) {
        model.addAttribute("listRequests", requestService.findAll());
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

}
