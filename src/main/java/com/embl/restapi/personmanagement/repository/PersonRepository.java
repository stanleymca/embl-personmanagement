package com.embl.restapi.personmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.embl.restapi.personmanagement.model.Person;

/**
 * @author Stanley Stephen
 * This is a Person repository interface which extends JpaRepository
 *
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
