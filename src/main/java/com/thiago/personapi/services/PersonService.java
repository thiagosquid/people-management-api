package com.thiago.personapi.services;

import com.thiago.personapi.dto.mapper.PersonMapper;
import com.thiago.personapi.dto.request.PersonDTO;
import com.thiago.personapi.dto.response.MessageResponseDTO;
import com.thiago.personapi.entities.Person;
import com.thiago.personapi.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    public List<PersonDTO> listAll(){
        List<Person> allPeople;

        allPeople = personRepository.findAll();

        return allPeople.stream().map((person)->{
            return personMapper.toDTO(person);
        }).collect(Collectors.toList());
    }

    public MessageResponseDTO create(PersonDTO personDTO) {
        Person person = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(person);

        MessageResponseDTO messageResponseDTO = createMessageResponse("Person successfully created whit ID: ", savedPerson.getId());
        return messageResponseDTO;
    }

    private MessageResponseDTO createMessageResponse(String message, Long id){
        return MessageResponseDTO.builder().message(message + id).build();
    }

}
