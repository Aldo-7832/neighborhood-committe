package mx.edu.utez.neighborhoodcommitte.controller;

import mx.edu.utez.neighborhoodcommitte.entity.CommitteeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.edu.utez.neighborhoodcommitte.service.CommitteeRequestService;

import java.util.List;

@Controller
@RequestMapping(value = "/committeeRequest")
public class CommitteeRequestController {

    @Autowired
    private CommitteeRequestService committeeRequestService;

    @GetMapping(value = "/list")
    public String findAll(Model model) {
        List<CommitteeRequest> listCommitteeRequests = committeeRequestService.findAll();
        model.addAttribute("listCommitteeRequests", committeeRequestService.findAll());
        System.out.println(listCommitteeRequests.size());
        return "requests/listRequests";
    }

    @GetMapping(value = "/find/{id}")
    public String findOne(Model model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        CommitteeRequest committeeRequest = committeeRequestService.findOne(id);
        if (!committeeRequest.equals(null)) {
            model.addAttribute("committeeRequest", committeeRequest);
            System.out.println("aqui si llega, pero ya no direcciona");
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró la solicitud solicitada");

        }
        return "requests/listRequests";
    }
    @RequestMapping(value = "/detalles/{id}", method = RequestMethod.GET)
    public String detalles(@PathVariable Long id, Model modelo, RedirectAttributes redirectAttributes) {
        CommitteeRequest committeeRequest = committeeRequestService.findOne(id);
        if (committeeRequest != null) {
            modelo.addAttribute("committeeRequests", committeeRequestService.findOne(id));
            modelo.addAttribute("listCommitteeRequests", committeeRequestService.findAll());
            return "requests/detailsRequests";
        }
        redirectAttributes.addFlashAttribute("msg_error", "Registro No Encontrado");
        return "redirect:/requests/listRequests";
    }


    @GetMapping("/create")
    public String crearRequests(CommitteeRequest committeeRequest, Model modelo) {
        System.out.println("Llega al metodo");
        modelo.addAttribute("listRequests", committeeRequestService.findAll());
        return "requests/createRequests";
    }

    @PostMapping(value = "/save")
    public String save(Model model, CommitteeRequest committeeRequest, RedirectAttributes redirectAttributes) {
        String msgOk = "";
        String msgError = "";

        if(committeeRequest.getId() != null){
            msgOk = "Solicitud Actualizada correctamente";
            msgError = "La solicitud NO pudo ser Actualizada correctamente";
        }else{
            msgOk = "Solicitud Guardada correctamente";
            msgError = "La solicitud NO pudo ser Guardada correctamente";
        }

        boolean res = committeeRequestService.save(committeeRequest);
        System.out.println("ID: "+ committeeRequest.getId());
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", msgOk);
            return "redirect:/requests/listRequests";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", msgError);
            return "redirect:/requests/createRequests";
        }
    }

    @PutMapping(value = "/update/{id}")
    public String update(@PathVariable long id, Model modelo, CommitteeRequest committeeRequest, RedirectAttributes redirectAttributes) {
        CommitteeRequest committeeRequest1 = committeeRequestService.findOne(id);
        if (committeeRequest1!= null) {
            modelo.addAttribute("committeeRequest", committeeRequest);
            modelo.addAttribute("listCommiteeRequests", committeeRequest1);
            return "requests/createRequests";
        } else {
            return "requests/listRequests";
        }
    }



    @DeleteMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        CommitteeRequest committeeRequest = committeeRequestService.findOne(id);
        if (!committeeRequest.equals(null)) {
            boolean res = committeeRequestService.delete(id);
            if (res) {
                redirectAttributes.addFlashAttribute("msg_success", "Solicitud eliminada correctamente");
                return "";
            } else {
                redirectAttributes.addFlashAttribute("msg_error", "No se pudo eliminar la solicitud");
                return "";
            }
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró la solicitud solicitada");
            return "";
        }
    }

}