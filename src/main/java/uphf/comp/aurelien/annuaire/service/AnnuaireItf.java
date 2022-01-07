package uphf.comp.aurelien.annuaire.service;
import uphf.comp.aurelien.annuaire.model.CreatePerson;
import uphf.comp.aurelien.annuaire.model.Person;
import org.springframework.http.ResponseEntity;

import java.util.Map;


public interface AnnuaireItf {

    public ResponseEntity<?> createPersons();

    public Map<String, Person> getPersons();

    public Person findById(int id);

    public Map<String, Person> findByName(String name);

    public Person addPerson(String name, String surname, String phone, String city);

    public void deletePerson(int id);

    public Person updatePerson(String id, String name, String surname, String phone, String city);



	/**
	 * 
	 * MAINTENANT 
	 * C'EST LE SERVICE REST
	 *
	 */
    
	ResponseEntity<?> getPersonsREST();
	
	ResponseEntity<?> createPersonREST(CreatePerson createPerson);

	ResponseEntity<?> findByIdREST(int id);

	ResponseEntity<?> getPersonNameREST(String name);
}
