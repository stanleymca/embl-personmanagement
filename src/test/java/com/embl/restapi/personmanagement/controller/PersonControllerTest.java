package com.embl.restapi.personmanagement.controller;



import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.embl.restapi.personmanagement.dto.ApiResponseMsgDTO;
import com.embl.restapi.personmanagement.dto.PersonDTO;
import com.embl.restapi.personmanagement.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
@ActiveProfiles("test")
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private PersonService personService;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "embl", password = "embl")
    public void getPersonByIdTest() throws Exception {

    	ApiResponseMsgDTO responseDto = createPersonDetails();
    	PersonDTO personDto = responseDto.getPersonDto();
        given(personService.getPersonById(anyLong())).willReturn(responseDto);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/person/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(personDto.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(personDto.getLastName())))
                .andExpect(jsonPath("$.age", is(personDto.getAge())))
                .andExpect(jsonPath("$.favouriteColour", is(personDto.getFavouriteColour())))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "embl", password = "embl")
    public void getAllPersonsTest() throws Exception {

    	ApiResponseMsgDTO responseDto = createPersonDetails();
    	PersonDTO personDto = responseDto.getPersonDto();
        given(personService.getAllPersons()).willReturn(Arrays.asList(personDto));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/person")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[0].firstName", is(personDto.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(personDto.getLastName())))
                .andExpect(jsonPath("$[0].age", is(personDto.getAge())))
                .andExpect(jsonPath("$[0].favouriteColour", is(personDto.getFavouriteColour())))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "embl", password = "embl")
    public void createPersonTest() throws Exception {

    	ApiResponseMsgDTO responseDto = createPersonDetails();
    	PersonDTO personDto = responseDto.getPersonDto();
        given(personService.createOrUpdatePerson(any(PersonDTO.class))).willReturn(responseDto);
        mockMvc.perform(post("/person")
                .content(objectMapper.writeValueAsString(personDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", is(personDto.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(personDto.getLastName())))
                .andExpect(jsonPath("$.age", is(personDto.getAge())))
                .andExpect(jsonPath("$.favouriteColour", is(personDto.getFavouriteColour())))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "embl", password = "embl")
    public void updatePersonTest() throws Exception {

    	ApiResponseMsgDTO responseDto = createPersonDetails();
    	PersonDTO personDto = responseDto.getPersonDto();
        given(personService.createOrUpdatePerson(any(PersonDTO.class))).willReturn(responseDto);
        mockMvc.perform(put("/person")
                .content(objectMapper.writeValueAsString(personDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", is(personDto.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(personDto.getLastName())))
                .andExpect(jsonPath("$.age", is(personDto.getAge())))
                .andExpect(jsonPath("$.favouriteColour", is(personDto.getFavouriteColour())))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "embl", password = "embl")
    public void deletePersonTest() throws Exception {

        Mockito.doNothing().when(personService).deletePerson(anyLong());
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/person/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private ApiResponseMsgDTO createPersonDetails() {
        PersonDTO personDto = new PersonDTO();
        personDto.setId(1L);
        personDto.setFirstName("John");
        personDto.setLastName("Daniel");
        personDto.setAge(45);
        personDto.setFavouriteColour("Green");
        ApiResponseMsgDTO success = new ApiResponseMsgDTO("EMBL003", "Person saved successfully", personDto);
    	return success;
    }

}
