package com.embl.restapi.personmanagement.service;

import java.util.List;

import com.embl.restapi.personmanagement.dto.ApiResponseMsgDTO;
import com.embl.restapi.personmanagement.dto.PersonDTO;

/**
 * This is a person service interface which has the operations 
 * for get, create, update and delete a person
 * @author Stanley Stephen
 *
 */
public interface PersonService {

	/**
	 * This method is used to retrieve the person by id
	 * @param id
	 * @return PersonDTO
	 */
	ApiResponseMsgDTO getPersonById(Long id);
    
    /**
     * This method is used to retrieve all the persons
     * @return List of PersonDTO
     */
    List<PersonDTO> getAllPersons();
    
    /**
     * This method is used to create a person or update a person
     * @param personDto
     * @return PersonDTO
     */
    ApiResponseMsgDTO createOrUpdatePerson(PersonDTO personDto);
    
    /**
     * This method is used to delete a person by id
     * @param id
     * @return ApiResponseMsgDTO
     */
    ApiResponseMsgDTO deletePerson(Long id);
    
}
