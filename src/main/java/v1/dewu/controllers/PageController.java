package v1.dewu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import v1.dewu.service.IdentificationService;

import javax.websocket.server.PathParam;

@Controller
public class PageController {

    @Autowired
    IdentificationService identificationService;

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/generate")
    public String generate() {
        return "generate";
    }

    @RequestMapping(value = "/identification")
    public String identification(@RequestParam("serial") long serialnumber) {
        if(!identificationService.exsist(serialnumber))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "serial not found");
        else
            return "identification";
    }

}
