package com.thiago.personapi.controllers;


import com.thiago.personapi.entities.Person;
import com.thiago.personapi.repositories.PersonRepository;
import com.thiago.personapi.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public List<Person> findAll(){
        return personService.listAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@RequestBody Person person){
        return personService.save(person);
    }


}
