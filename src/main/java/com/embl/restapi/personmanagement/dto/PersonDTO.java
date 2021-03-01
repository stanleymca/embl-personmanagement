package com.embl.restapi.personmanagement.dto;

/**
 * This is a person DTO class
 * @author Stanley Stephen
 */

public class PersonDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private Integer age;
	private String favouriteColour;
	
	/**
	 * Default Constructor
	 */
	public PersonDTO() {
		
	}
	
	/**
	 * Argumented Constructor
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param favouriteColour
	 */
	public PersonDTO(Long id, String firstName, String lastName, Integer age, String favouriteColour) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.favouriteColour = favouriteColour;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return the favouriteColour
	 */
	public String getFavouriteColour() {
		return favouriteColour;
	}

	/**
	 * @param favouriteColour the favouriteColour to set
	 */
	public void setFavouriteColour(String favouriteColour) {
		this.favouriteColour = favouriteColour;
	}
	
	@Override
	public String toString() {
		return "PersonDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", favouriteColour=" + favouriteColour + "]";
	}
}
