package mx.edu.utez.neighborhoodcommitte.controller;


import mx.edu.utez.neighborhoodcommitte.entity.Request;
import mx.edu.utez.neighborhoodcommitte.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/request")
public class RequestController {
    @Autowired
    private RequestService requestService;

    @GetMapping(value = "/list")
    public String findAll(Model model){
        List<Request> listRequests = requestService.findAll();
        model.addAttribute("listRequests", requestService.findAll());
        System.out.println(listRequests.size());
        return "requests/listRequests";
    }

    @GetMapping(value = "/find/{id}")
    public String findOne(Model model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes){
      Request request = requestService.findById(id);
      if (!request.equals(null)){
          model.addAttribute("request", request);
      }else {
          redirectAttributes.addFlashAttribute("msg_error", "No se encontró la solicitud solicitada");
      }
        return "requests/listRequests";
    }

    @RequestMapping (value = "/detalles/{id}", method = RequestMethod.GET)
    public String detalles(Model model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        Request request = requestService.findById(id);
        if (!request.equals(null)){
            model.addAttribute("request", requestService.findById(id));
            model.addAttribute("listRequests", requestService.findAll());
            return "requests/detailsRequests";
        }else {
            redirectAttributes.addFlashAttribute("msg_error", "Registro No Encontrado");
            return "redirect:/requests/listRequests";
        }
    }
}
