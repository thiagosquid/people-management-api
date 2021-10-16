package com.thiago.personapi.services;

import com.thiago.personapi.dto.mapper.PersonMapper;
import com.thiago.personapi.dto.request.PersonDTO;
import com.thiago.personapi.entities.Person;
import com.thiago.personapi.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    public List<Person> listAll(){
        List<Person> allPeople;

        allPeople = personRepository.findAll();

        return allPeople;
    }

    public Person create(PersonDTO personDTO) {
        Person person = personMapper.toModel(personDTO);
        return personRepository.save(person);
    }
}
