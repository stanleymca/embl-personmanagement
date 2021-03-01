package com.embl.restapi.personmanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ApiResponseMsgDTO {

    private String code;
    private String message;
    private PersonDTO personDto;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    public ApiResponseMsgDTO(String code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
    
    public ApiResponseMsgDTO(String code, String message, PersonDTO personDto) {
        this.code = code;
        this.message = message;
        this.personDto = personDto;
        this.timestamp = LocalDateTime.now();
    }

	/**
	 * @return the errorCode
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the personDto
	 */
	public PersonDTO getPersonDto() {
		return personDto;
	}

	/**
	 * @param personDto the personDto to set
	 */
	public void setPersonDto(PersonDTO personDto) {
		this.personDto = personDto;
	}

	/**
	 * @return the timestamp
	 */
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}    
}
