package mx.edu.utez.neighborhoodcommitte.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.edu.utez.neighborhoodcommitte.entity.Request;
import mx.edu.utez.neighborhoodcommitte.entity.RequestAttachments;
import mx.edu.utez.neighborhoodcommitte.entity.Users;
import mx.edu.utez.neighborhoodcommitte.entity.dto.RequestDto;
import mx.edu.utez.neighborhoodcommitte.service.CategoryService;
import mx.edu.utez.neighborhoodcommitte.service.RequestAttachmentsService;
import mx.edu.utez.neighborhoodcommitte.service.RequestService;
import mx.edu.utez.neighborhoodcommitte.service.UsersService;
import mx.edu.utez.neighborhoodcommitte.util.ImagenUtileria;

@Controller
@RequestMapping("/president")
public class RequestControllerPresident {

    @Autowired
    private RequestService requestService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RequestAttachmentsService attachmentsService;

    @RequestMapping(value = "/list/unpaid", method = RequestMethod.GET)
    public String listAllPresidentUnpaidRequests(Authentication authentication, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        Users user = usersService.findByUsername(authentication.getName());
        user.setPassword(null);
        session.setAttribute("user", user);
        model.addAttribute("unpaidList", requestService.findAllUnpaidByCommitteeId(user.getCommittee().getId()));
        return "requests/president/unpaidList";
    }

    @RequestMapping(value = "/pay/{id}/{status}", method = RequestMethod.GET)
    public ResponseEntity<Object> payRequest(@PathVariable("id") long id, @PathVariable("status") String status, RedirectAttributes redirectAttributes) {
        Map<String, Object> data = new HashMap<>();
        if(status.equals("COMPLETED")) {
            Request tmp = requestService.findById(id);
            if (!tmp.equals(null)) {
                tmp.setPaymentStatus(2);
                tmp.setStatus(3);
                requestService.save(tmp);
                data.put("error", false);
            } else {
                data.put("error", true);
            }
        } else {
            data.put("error", true);
        }
        if (data.get("error").equals(false)) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String showRequestDetails(@PathVariable("id") long id, Authentication authentication, HttpSession session, Model model,
    RedirectAttributes redirectAttributes) {
        Users user = usersService.findByUsername(authentication.getName());
        user.setPassword(null);
        session.setAttribute("user", user);
        model.addAttribute("request", requestService.findById(id));
        model.addAttribute("attachment", attachmentsService.findByRequestId(id));
        return "requests/president/details";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listAllPresidentRequests(Authentication authentication, HttpSession session, Model model,
            RedirectAttributes redirectAttributes) {
        Users user = usersService.findByUsername(authentication.getName());
        user.setPassword(null);
        session.setAttribute("user", user);
        model.addAttribute("requestList", requestService.findAllByCommitteeId(user.getCommittee().getId()));
        return "requests/president/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createPresidentRequest(Authentication authentication, HttpSession session, Model model,
            RedirectAttributes redirectAttributes, RequestDto requestDto) {
        Users user = usersService.findByUsername(authentication.getName());
        user.setPassword(null);
        session.setAttribute("user", user);
        model.addAttribute("categoryList", categoryService.findAll());
        return "requests/president/create";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
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
        obj.setStatus(2);
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
                    attachments.setName(filename.replaceAll(" ", "").replaceAll("-", "").replace("°", ""));
                    attachments.setRequest(obj);
                    boolean res2 = attachmentsService.save(attachments);
                    if (res2) {
                        redirectAttributes.addFlashAttribute("msg_success", "¡Se registró la solicitud con la evidencia!");
                        return "redirect:/president/list";
                    } else {
                        redirectAttributes.addFlashAttribute("msg_error", "Ocurrió un error al registrar la solicitud con la evidencia");
                        return "redirect:/president/create";
                    }
                } else {
                    redirectAttributes.addFlashAttribute("msg_success", "¡Se registró la solicitud correctamente!");
                    return "redirect:/president/list";
                }
            }
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "Ocurrió un error al registrar la solicitud");
            return "redirect:/president/create";
        }
        redirectAttributes.addFlashAttribute("msg_error", "Ocurrió un fallo");
        return "redirect:/president/create";
    }
    
}
