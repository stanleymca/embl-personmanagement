package com.embl.restapi.personmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.*;

/**
 * @author Stanley Stephen
 * This is a Person model class 
 * Validations have been implemented for each property
 */

@Entity
@Table(name = "PERSON")
public class Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	@Column(name="ID")
	private Long id;
	
	@NotNull
	@Size (min=2, message="First Name should have atleast 2 characters.")
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@NotNull
	@Size (min=2, message="Last Name should have atleast 2 characters.")
	@Column(name="LAST_NAME")
	private String lastName;
	
	@NotNull
	@Positive (message="Age should be atleast 1 positive number.")
	@Column(name="AGE")
	private Integer age;
	
	@NotNull 
	@Size (min=3, message="Favourite Colour should have atleast 3 character.")
	@Column(name="FAVOURITE_COLOUR")
	private String favouriteColour;
	
	/**
	 * Default Constructor
	 */
	public Person() {
		
	}
	
	/**
	 * Argumented Constructor
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param favouriteColour
	 */
	public Person(Long id, String firstName, String lastName, Integer age, String favouriteColour) {
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
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", favouriteColour=" + favouriteColour + "]";
	}
}
