package uphf.comp.aurelien.annuaire.controller;

import uphf.comp.aurelien.annuaire.model.CreatePerson;
import uphf.comp.aurelien.annuaire.model.Person;
import uphf.comp.aurelien.annuaire.repository.PersonRepository;
import uphf.comp.aurelien.annuaire.service.Annuaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/// REST Controller pour les Person

@RestController
public class PersonControllerREST {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	Annuaire annuaire;


	
	@GetMapping("REST/persons")
	public List<Person> getAllPersons()

	{
		return personRepository.findAll();
		
	}
	
	@GetMapping("REST/persons/{id}")
    public Optional<Person> findByIdREST(@PathVariable("id") Integer id) {
    	Optional<Person> person = personRepository.findById(id);
        return person;
    }
	
	
    @PostMapping("REST/persons")
    public Person createPersonREST(@Validated @RequestBody CreatePerson person){
        return personRepository.save(
              new Person
                (
                person.getName(),
                person.getSurname(),
                person.getPhone(),
                person.getCity()
                )
        );
    }
    
    
    @GetMapping("REST/persons/nom/name")
    public List<Person> getPersonNameREST(@RequestParam("name") String name) {
    	List<Person> person = personRepository.findAllByName(name);
    	return person;
    }
    
    
    @DeleteMapping("REST/persons/{id}")
    public void deletePersonREST(@PathVariable Integer id){
   	personRepository.deleteById(id);

   }
    
  
	

}
