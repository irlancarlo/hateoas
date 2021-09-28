package com.example.heteoas.controller;

import com.example.heteoas.model.PersonModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;;

@RestController
@RequestMapping("/heteoas")
public class PersonController {

    @GetMapping("/all")
    public ResponseEntity findAll(){

        List<PersonModel> personModels = new ArrayList<>();
        PersonModel personModel = new PersonModel();
        personModel.setId(1);
        personModel.setFirstName("Irlan");
        personModel.setLastName("Gomes");
        personModels.add(personModel);

        personModel = new PersonModel();
        personModel.setId(2);
        personModel.setFirstName("Pedro");
        personModel.setLastName("Carlo Gomes");
        personModels.add(personModel);

        personModels.stream().forEach(p->p.add(
                linkTo(methodOn(PersonController.class).findById(p.getId())).withSelfRel()
        ));

        return ResponseEntity.ok(personModels);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") int id){
        PersonModel personModel = new PersonModel();
        personModel.setId(id);
        personModel.setFirstName("Irlan");
        personModel.setLastName("Gomes");

        Class<PersonController> controllerClass = PersonController.class;

        // Start the affordance with the "self" link, i.e. this method.
        Link findOneLink = linkTo(methodOn(controllerClass).findById(id)).withSelfRel();
        personModel.add(findOneLink);

        return ResponseEntity.ok(personModel);
    }

}
