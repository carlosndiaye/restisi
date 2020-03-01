/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isi.restisi.controller;

import com.isi.restisi.exception.ResourceNotFoundException;
import com.isi.restisi.model.Person;
import com.isi.restisi.repository.PersonRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author carlosndiaye
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class PersonController {
	@Autowired
	private PersonRepository personRepository;

	@GetMapping("/person")
	public List<Person> getAllEmployees() {
		return personRepository.findAll();
	}

	@GetMapping("/person/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") Long personId)
			throws ResourceNotFoundException {
		Person employee = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found for ID :: " + personId));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/person")
	public Person createPerson(@Valid @RequestBody Person person) {
		return personRepository.save(person);
	}

	@PutMapping("/person/{id}")
	public ResponseEntity<Person> updateEmployee(@PathVariable(value = "id") Long personId,
			@Valid @RequestBody Person personDetails) throws ResourceNotFoundException {
		Person person = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for ID :: " + personId));

		person.setAddress(personDetails.getAddress());
		person.setLastname(personDetails.getLastname());
		person.setFirstname(personDetails.getFirstname());
		final Person updatedPerson = personRepository.save(person);
		return ResponseEntity.ok(updatedPerson);
	}

	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deletePerson(@PathVariable(value = "id") Long personId)
			throws ResourceNotFoundException {
		Person person = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for ID :: " + personId));

		personRepository.delete(person);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}