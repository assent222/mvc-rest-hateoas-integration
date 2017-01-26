package pkk.interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pkk.interview.domain.Person;
import pkk.interview.interceptor.HateoasResourceAssembler;
import pkk.interview.service.PersonService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by root on 26.01.2017.
 */

@Controller
@RequestMapping(path = "/rest")
public class PersonController {

    @Autowired
    private PersonService service;

    @RequestMapping(path = "/persons", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody

    public ResponseEntity persons(HateoasResourceAssembler resourceAssembler) {
        System.out.println(">> persons");


        Link link = linkTo(this.getClass()).slash("/persons").withSelfRel();


        List<Resource> list = new ArrayList<>();
        for (Person person : service.findAll()) {
            list.add(new Resource(person, linkTo(this.getClass()).slash("/persons/" + person.getId()).withSelfRel()));
            System.out.println("resourceAssembler=" + resourceAssembler.toResource(person));
        }

        Resources<Resource> resources = new Resources<Resource>(list,link);

        System.out.println("<< persons");
        return new ResponseEntity(resources, HttpStatus.OK);
    }

    @RequestMapping(path = "/persons/{id}")
    @ResponseBody
    public Object findPerson(@PathVariable Integer id) {
        Person person = service.find(id);
        Link link = linkTo(this.getClass()).slash(id).withSelfRel();

        Resource<?> resource = new Resource<>(person, link);

        return resource;
    }
}
