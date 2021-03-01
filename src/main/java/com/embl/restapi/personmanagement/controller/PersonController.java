package com.embl.restapi.personmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.embl.restapi.personmanagement.dto.ApiResponseMsgDTO;
import com.embl.restapi.personmanagement.dto.PersonDTO;
import com.embl.restapi.personmanagement.model.Person;
import com.embl.restapi.personmanagement.service.PersonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Stanley Stephen
 * This is the controller class to perform CRUD operations on Person
 */

@RestController
@RequestMapping("/person")
@Api(value = "EMBL Person Management Rest APIs")
public class PersonController {

	private static final Logger log = LoggerFactory.getLogger(PersonController.class);
	
    @Autowired
    PersonService  personService;
    
    @ApiOperation(value = "API for Get Person Details by ID"
            , response = Person.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Person Details Successfully sent"),
            @ApiResponse(code = 404, message = "Person does not exist for given id")})
    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponseMsgDTO> getPersonById(@PathVariable String id) throws Exception {
        log.info("Request received for getting person details by id");
        return ResponseEntity.ok(personService.getPersonById(Long.parseLong(id)));
    }

    @ApiOperation(value = "API for Get All Persons Details"
            , response = Person.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Person Details Successfully sent"),
            @ApiResponse(code = 404, message = "Person does not exist")})
    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        log.info("Request received for getting all Person details");
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @ApiOperation(value = "API for Create Person Details"
            , response = Person.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Person Details created Successfully")})
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponseMsgDTO> createPerson(@Valid @RequestBody PersonDTO personDto) {
        log.info("Request received for creating person details..."+personDto);
        return ResponseEntity.ok(personService.createOrUpdatePerson(personDto));
    }

    @ApiOperation(value = "API for Delete Person Details by ID"
            , response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleted Person Successfully"),
            @ApiResponse(code = 404, message = "Person does not exist for the given id")})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ApiResponseMsgDTO> deletePerson(@PathVariable String id) {
        log.info("Request received for deleting person details");
        return ResponseEntity.ok(personService.deletePerson(Long.parseLong(id)));
    }

    @ApiOperation(value = "API for Update Person Details only"
            , response = Person.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Updated Person Successfully")})
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponseMsgDTO> updatePerson(@PathVariable String id,
    		@Valid @RequestBody PersonDTO personDto) {
        log.info("Request received for updating person details..."+personDto);
        
        ApiResponseMsgDTO apiResponseMsgDTO = personService.getPersonById(Long.parseLong(id));
    	if(apiResponseMsgDTO.getCode().equalsIgnoreCase("EMBL001")) {
			if(personDto.getId() != null && Long.parseLong(id) == personDto.getId().longValue()) {
				return ResponseEntity.ok(personService.createOrUpdatePerson(personDto));
			}
			else {
	    		ApiResponseMsgDTO error = new ApiResponseMsgDTO("EMBL002", "Person id should be "+id+" in the personDto.", personDto);
	    		return ResponseEntity.ok(error);
	    	}
    	}    	
    	else {
    		return ResponseEntity.ok(apiResponseMsgDTO);
    	}
        
    }
}
