package mx.edu.utez.neighborhoodcommitte.controller;

import mx.edu.utez.neighborhoodcommitte.entity.Request;
import mx.edu.utez.neighborhoodcommitte.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/request")
public class RequestController {
    @Autowired
    private RequestService requestService;

    @RequestMapping (value = "/amount/{id}", method = RequestMethod.GET)
    public String amount(Model model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes){
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

    @RequestMapping (value = "/detalles/{id}", method = RequestMethod.GET)
    public String detalles(Model model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        Request request = requestService.findById(id);
        if (request != null){
            model.addAttribute("request", requestService.findById(id));
            return "requests/amountRequests";
        }else {
            redirectAttributes.addFlashAttribute("msg_error", "Registro No Encontrado");
            return "redirect:/requests/listRequests";
        }
    }


    @PostMapping(value = "/update")
    public String actualizar(@ModelAttribute("request") Request request, Model modelo, RedirectAttributes redirectAttributes) {
        Request obj = requestService.findById(request.getId());
        obj.setPaymentAmount(request.getPaymentAmount());
        if (obj != null) {
            requestService.save(obj);
            modelo.addAttribute("listRequests", requestService.findAll());
        }
        return "requests/listRequests";

    }

    @GetMapping(value = "/list")
    public String findAll(Model model){
        List<Request> listRequests = requestService.findAll();
        model.addAttribute("listRequests", requestService.findAll());
        System.out.println(listRequests.size());
        return "requests/listRequests";
    }
    @GetMapping("/create")
    public String create(Request request, Model modelo) {
        modelo.addAttribute("listRequests", requestService.findAll());
        return "requests/amountRequests";
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



    @PostMapping(value = "/save")
    public String save(Model model, Request request, RedirectAttributes redirectAttributes) {
        String msgOk = "";
        String msgError = "";

        if(request.getId() != null){
            msgOk = "Solictud Actualizada correctamente";
            msgError = "La solicitud NO pudo ser Actualizada correctamente";
        }else{
            msgOk = "Solicitud Guardada correctamente";
            msgError = "La solicitud NO pudo ser Guardada correctamente";
        }

        boolean res = requestService.save(request);
        System.out.println("ID: "+ request.getId());
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", msgOk);
            return "redirect:/requests/list";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", msgError);
            return "redirect:/requests/amountRequests";
        }
    }


}
