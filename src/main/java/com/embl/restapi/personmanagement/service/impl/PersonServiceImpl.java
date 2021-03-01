package com.embl.restapi.personmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.embl.restapi.personmanagement.dto.ApiResponseMsgDTO;
import com.embl.restapi.personmanagement.dto.PersonDTO;
import com.embl.restapi.personmanagement.model.Person;
import com.embl.restapi.personmanagement.repository.PersonRepository;
import com.embl.restapi.personmanagement.service.PersonService;

/**
 * This is the implementation class for person service
 * @author Stanley Stephen
 *
 */
@Service
public class PersonServiceImpl implements PersonService {
	
	private static final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

	@Autowired
    private PersonRepository repository;

	/**
	 * This is the implementation method which is used to retrieve the person by id
	 * @param id
	 * @return PersonDTO
	 */
	@Override
	public ApiResponseMsgDTO getPersonById(Long id) {
		log.info("@Search_person_by_id..."+id);
		Optional<Person> person=repository.findById(id);
		
		if(person != null && person.isPresent()) {           
			log.info("@Search_person_data..."+person.get());
			PersonDTO dto = new PersonDTO();
			dto.setId(person.get().getId());
            dto.setFirstName(person.get().getFirstName());
            dto.setLastName(person.get().getLastName());
            dto.setAge(person.get().getAge());
            dto.setFavouriteColour(person.get().getFavouriteColour());
            ApiResponseMsgDTO success = new ApiResponseMsgDTO("EMBL001", "Person "+id+" exists in the system", dto);
        	return success;
        } else {
        	ApiResponseMsgDTO error = new ApiResponseMsgDTO("EMBL002", "Person "+id+" does not exist in the system.");
            return error;
        }
	}

	 /**
     * This is the implementation method which is used to retrieve all the persons
     * @return List of PersonDTO
     */
    @Override
    public List<PersonDTO> getAllPersons()  {
    	List<PersonDTO> dtoList = new ArrayList<PersonDTO>();
    	
    	log.info("@Get_all_persons...");
    	//Get all the persons
    	List<Person> list = repository.findAll();
    	
    	//Check if the list contains persons
    	if(list != null && list.size() > 0) {
    		//Loop through the list and get each person object, 
    		//set each element in the DTO and add it in the list
	    	for(int i=0;i<list.size();i++) {  
	    		Person person = (Person)list.get(i);
	    		
	    		PersonDTO dto = new PersonDTO();
	    		dto.setId(person.getId());
	    		dto.setFirstName(person.getFirstName());
	            dto.setLastName(person.getLastName());
	            dto.setAge(person.getAge());
	            dto.setFavouriteColour(person.getFavouriteColour());
	            dtoList.add(dto);
	    	}
    	}
    	//return the list of person DTO.
        return dtoList;
    }

    /**
     * This is the implementation method which is used to create a person or update a person
     * @param personDto
     * @return PersonDTO
     */
    @Override
    public ApiResponseMsgDTO createOrUpdatePerson(PersonDTO personDto) {
    	log.info("@createOrUpdatePerson-personDto..."+personDto);
    	
    	if (personDto.getId() == null || personDto.getFirstName() == null || personDto.getFirstName().trim().equals("")
    			|| personDto.getLastName() == null || personDto.getLastName().trim().equals("")
    			|| personDto.getAge() == null || personDto.getAge() <= 0
    			|| personDto.getFavouriteColour() == null || personDto.getFavouriteColour().trim().equals("")) {
    		ApiResponseMsgDTO error = new ApiResponseMsgDTO("EMBL003", "All the values in person Dto are mandatory", personDto);
        	return error;
    	}
    	
    	Person person = new Person();
    	person.setId(personDto.getId());
    	person.setFirstName(personDto.getFirstName());
    	person.setLastName(personDto.getLastName());
    	person.setAge(personDto.getAge());
    	person.setFavouriteColour(personDto.getFavouriteColour());
        repository.save(person);
        
        ApiResponseMsgDTO success = new ApiResponseMsgDTO("EMBL003", "Person has been saved successfully", personDto);
    	return success;
    }

    /**
     * This is the implementation method which is used to delete a person by id
     * @param id
     * @return ApiResponseMsgDTO
     */
    @Override
    public ApiResponseMsgDTO deletePerson(Long id)  {
    	log.info("@Delete_person_by_id..."+id);
        Optional<Person> person=repository.findById(id);
        if(person.isPresent()) {
        	repository.deleteById(id);
        	ApiResponseMsgDTO success = new ApiResponseMsgDTO("EMBL004", "Person "+id+" has been deleted successfully");
        	return success;
        } else {        	
            ApiResponseMsgDTO error = new ApiResponseMsgDTO("EMBL005", "Person does not exist for the given id "+id+" to delete.");
            return error;
        }
    }
}
