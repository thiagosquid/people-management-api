package com.thiago.personapi.services;

import com.thiago.personapi.dto.mapper.PersonMapper;
import com.thiago.personapi.dto.request.PersonDTO;
import com.thiago.personapi.dto.response.MessageResponseDTO;
import com.thiago.personapi.entities.Person;
import com.thiago.personapi.exceptions.PersonNotFoundException;
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

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);

        return personMapper.toDTO(person);

    }

    public void deleteById(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);

    }

    public MessageResponseDTO updatePerson(Long id, PersonDTO personDTO) throws PersonNotFoundException {

        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);
        Person updatedPerson = personRepository.save(personToUpdate);

        MessageResponseDTO message = createMessageResponse("Person successfully updated with ID ", updatedPerson.getId());

        return message;
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {

        return personRepository.findById(id).orElseThrow(()-> new PersonNotFoundException(id));

    }

    private MessageResponseDTO createMessageResponse(String message, Long id){
        return MessageResponseDTO.builder().message(message + id).build();
    }
}
