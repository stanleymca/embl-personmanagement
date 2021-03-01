package com.embl.restapi.personmanagement.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.embl.restapi.personmanagement.dto.ApiResponseMsgDTO;
import com.embl.restapi.personmanagement.dto.PersonDTO;
import com.embl.restapi.personmanagement.exception.PersonNotFoundException;
import com.embl.restapi.personmanagement.model.Person;
import com.embl.restapi.personmanagement.repository.PersonRepository;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Mock
    private PersonRepository repository;
    
    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    public void whenGetPersonById_shouldReturnPersonEntity() {

        Person person = createPersonDetails();
        given(repository.findById(anyLong())).willReturn(Optional.of(person));
        ApiResponseMsgDTO responseDto = personService.getPersonById(1L);
        PersonDTO response = responseDto.getPersonDto();
        assertEquals(person.getId(), response.getId());
        assertEquals(person.getFirstName(), response.getFirstName());
        assertEquals(person.getLastName(), response.getLastName());
        assertEquals(person.getAge(), response.getAge());
        assertEquals(person.getFavouriteColour(), response.getFavouriteColour());
    }

    @Test
    public void whenGetPersonById_shouldReturnPersonNotFoundError() {

        given(repository.findById(anyLong())).willReturn(Optional.empty());
        thrown.expect(PersonNotFoundException.class);
        personService.getPersonById(1L);
    }

    @Test
    public void whenGetAllPersons_shouldReturnPersonsEntityList() {

        Person person = createPersonDetails();
        given(repository.findAll()).willReturn(Collections.singletonList(person));
        List<PersonDTO> response = personService.getAllPersons();
        assertEquals(person.getId(), response.get(0).getId());
        assertEquals(person.getFirstName(), response.get(0).getFirstName());
        assertEquals(person.getLastName(), response.get(0).getLastName());
        assertEquals(person.getAge(), response.get(0).getAge());
        assertEquals(person.getFavouriteColour(), response.get(0).getFavouriteColour());
    }

    @Test
    public void whenCreatePerson_shouldReturnPersonEntity() {

        Person person = createPersonDetails();
        given(repository.save(any(Person.class))).willReturn(person);
        PersonDTO request = new PersonDTO(person.getId(), person.getFirstName(), person.getLastName(), person.getAge(), person.getFavouriteColour());
        ApiResponseMsgDTO responseDto = personService.createOrUpdatePerson(request);
        PersonDTO response = responseDto.getPersonDto();
        assertEquals(person.getId(), response.getId());
        assertEquals(person.getFirstName(), response.getFirstName());
        assertEquals(person.getLastName(), response.getLastName());
        assertEquals(person.getAge(), response.getAge());
        assertEquals(person.getFavouriteColour(), response.getFavouriteColour());
    }

    @Test
    public void whenDeletePerson_shouldNotReturnError() {

        Person person = createPersonDetails();
        given(repository.findById(anyLong())).willReturn(Optional.of(person));
        Mockito.doNothing().when(repository).deleteById(anyLong());
        personService.deletePerson(1L);
    }

    @Test
    public void whenDeletePerson_shouldReturnPersonNotFoundError() {

        given(repository.findById(anyLong())).willReturn(Optional.empty());
        thrown.expect(PersonNotFoundException.class);
        personService.deletePerson(1L);
    }

    private Person createPersonDetails() {
    	Person person = new Person();
    	person.setId(1L);
    	person.setFirstName("Sarah");
    	person.setLastName("Robinson");
    	person.setAge(54);
    	person.setFavouriteColour("Red");
        return person;
    }

}
