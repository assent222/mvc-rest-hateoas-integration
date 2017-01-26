package pkk.interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pkk.interview.service.PersonService;

/**
 * Created by root on 26.01.2017.
 */

@RestController
@RequestMapping(path = "/rest")
public class PersonController {

    @Autowired
    private PersonService service;

    @RequestMapping(path = "/persons")
    public Object persons() {
        return service.findAll();
    }
}
