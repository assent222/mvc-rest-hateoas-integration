package pkk.interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pkk.interview.domain.Person;
import pkk.interview.service.PersonService;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

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

    @RequestMapping(path = "/persons/{id}")
    public Object findPerson(@PathVariable Integer id) {
        Person person = service.find(id);
        Link link = linkTo(this.getClass()).slash(id).withSelfRel();

        Resource<?> resource = new Resource<>(person, link);

        return resource;
    }
}
