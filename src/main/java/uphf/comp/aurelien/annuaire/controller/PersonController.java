package uphf.comp.aurelien.annuaire.controller;

import uphf.comp.aurelien.annuaire.repository.PersonRepository;
import uphf.comp.aurelien.annuaire.service.Annuaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

///Controller pour les Person

@Controller
public class PersonController {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	Annuaire annuaire;

	/**
	 * Création de 3 personnes dans la BDD
	 * en appellant la route createPersons
	 */
	@GetMapping("createPersons")
	public ResponseEntity<?> createPerson() {
		return annuaire.createPersons();
	}

	/**
	 * Récupère toutes les personnes de la BDD
	 *
	 */
	@GetMapping("annuaire")
	public String getPersons(@RequestParam(name = "id", required = false, defaultValue = "*") String id,
			@RequestParam(name = "name", required = false, defaultValue = "*") String name,
			@RequestParam(name = "surname", required = false, defaultValue = "*") String surname,
			@RequestParam(name = "phone", required = false, defaultValue = "*") String phone,
			@RequestParam(name = "city", required = false, defaultValue = "*") String city,
			Model model) {

		model.addAttribute("personMap", annuaire.getPersons());

		return "personList";
	}
	

	@GetMapping("annuaire/person")
	public String getPersonByName(@RequestParam(name = "id", required = false, defaultValue = "*") String id,
			@RequestParam(name = "name", required = false, defaultValue = "*") String name,
			@RequestParam(name = "surname", required = false, defaultValue = "*") String surname,
			@RequestParam(name = "phone", required = false, defaultValue = "*") String phone,
			@RequestParam(name = "city", required = false, defaultValue = "*") String city,
			Model model) {

		if (!name.equals("*")) {
			model.addAttribute("personMap", annuaire.findByName(name));
		}
		return "personList"; //page html
	}
	
	
	

	@GetMapping("annuaire/addPerson")
	public String formPerson() {
		return "addPerson"; // page html
	}

	
	
	
	
	@PostMapping("annuaire/remove")
	public String deletePerson(@RequestParam(name = "id", required = false, defaultValue = "*") int id) {

		annuaire.deletePerson(id);

		return "redirect:";
	}

	@PostMapping("/addPerson")
	public String createPerson(@RequestParam(name = "name", required = false, defaultValue = "*") String name,
			@RequestParam(name = "surname", required = false, defaultValue = "*") String surname,
			@RequestParam(name = "phone", required = false, defaultValue = "*") String phone,
			@RequestParam(name = "city", required = false, defaultValue = "*") String city) {

		annuaire.addPerson(name, surname, phone, city);

		return "redirect:annuaire";
	}

	@GetMapping("/updatePerson")
	public String getPersonToUpdateById(@RequestParam(name = "id", required = false, defaultValue = "*") String id,
			@RequestParam(name = "name", required = false, defaultValue = "*") String name,
			@RequestParam(name = "surname", required = false, defaultValue = "*") String surname,
			@RequestParam(name = "phone", required = false, defaultValue = "*") String phone,
			@RequestParam(name = "city", required = false, defaultValue = "*") String city,
			Model model) {
		model.addAttribute("person", annuaire.findById(Integer.parseInt(id)));
		return "updatePerson"; //page html
	}

	@PostMapping("/updatePerson")
	public String updatePerson(@RequestParam(name = "id", required = false, defaultValue = "*") String id,
			@RequestParam(name = "name", required = false, defaultValue = "*") String name,
			@RequestParam(name = "surname", required = false, defaultValue = "*") String surname,
			@RequestParam(name = "phone", required = false, defaultValue = "*") String phone,
			@RequestParam(name = "city", required = false, defaultValue = "*") String city,
			Model model) 
	{
		model.addAttribute("person", annuaire.updatePerson(id, name, surname, phone, city));
		return "redirect:annuaire";
	}

}
