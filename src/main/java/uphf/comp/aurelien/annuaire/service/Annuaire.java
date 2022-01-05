package uphf.comp.aurelien.annuaire.service;

import uphf.comp.aurelien.annuaire.model.CreatePerson;
import uphf.comp.aurelien.annuaire.model.Person;
import uphf.comp.aurelien.annuaire.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service(value = "annuaire")
public class Annuaire implements AnnuaireItf {

    @Autowired
    PersonRepository personRepository;

    /*
     *  ICI on cree une fonction qui ajoute 3 personnes dans la BDD
     */
    
    @Override
    public ResponseEntity<?> createPersons() {
        Person person = new Person("Aurelien", "Betourne", "0606060606", "VxConde");
        personRepository.save(person);
        person = new Person("Ziane", "Sofiane", "0606060606", "Valenciennes");
        personRepository.save(person);
        person = new Person("Emilie", "Crl", "0606060606", "Douchy");
        personRepository.save(person);

        return ResponseEntity.ok("3 exemples ajoutes dans la BDD");
    }
    
   

    @Override
    public Map<String, Person> getPersons() {

        Map<String, Person> personMap = new HashMap<>();

        List<Person> personList = new ArrayList<>();
        personList.addAll(personRepository.findAll());

        for (Person p : personList) {
            personMap.put(String.valueOf(p.getId()), p);
        }

        return personMap;
    }

    
    
    
    @Override
    public Person findById(int id) {

        // Initialisation de la map
        Map<String, Person> personMap = new HashMap<>();

        //  Récupération de la personne
        Optional<Person> p = personRepository.findById(id);

        // Si une personne de cet id est présente
        if (p.isPresent()) {
            return p.get();
        }

        return null;
    }
    
    
    
    
    

    @Override
    public Map<String, Person> findByName(String name) {
        // Initialisation de la map
        Map<String, Person> personMap = new HashMap<>();


        // Initialisation de la liste et récupération des personnes
        List<Person> personList = new ArrayList<>();
        personList.addAll(personRepository.findAllByName(name));

        // Insertion des personnes dans la map
        for (Person p : personList) {
            personMap.put(String.valueOf(p.getId()), p);
        }

        return personMap;
    }


    @Override
    public Person addPerson(String name, String surname, String phone, String city) {

        Person person = new Person(name, surname, phone, city);
        personRepository.save(person);

        return person;
    }

    @Override
    public void deletePerson(int id) {

        Optional<Person> p = personRepository.findById(id);

        if (p.isPresent()) {
            personRepository.delete(p.get());
        }
    }

    @Override
    public Person updatePerson(String id,String name, String surname, String phone, String city) {
        System.out.println("name = " + name);
        System.out.println("id = " + id);
        Optional<Person> p = personRepository.findById(Integer.parseInt(id));
        Person toUpdate = null;
        if (p.isPresent()) {
            System.out.println(toUpdate);
            toUpdate = p.get();
            toUpdate.setId(Integer.parseInt(id));
            toUpdate.setName(name);
            toUpdate.setSurname(surname);
            toUpdate.setPhone(phone);
            toUpdate.setCity(city);
            personRepository.save(toUpdate);
        }


        return toUpdate;
    }
    
    
    
    /**
     * 
     * MAINTENANT 
     * C'EST LE SERVICE REST
     * @return
     */
    
    
    public ResponseEntity<?> getPersonsREST() {
        if (personRepository.findAll().isEmpty()) { // si c'est vide, il n'y a pas de persons, on affiche un pb
        	 return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Aucune personnes...");
        }
        return ResponseEntity.status(HttpStatus.OK).body(personRepository.findAll()); 
       
    }
    
    public ResponseEntity<?> createPersonREST(CreatePerson createPerson) {

        return ResponseEntity.status(HttpStatus.CREATED).body(personRepository.save(new Person(
                        createPerson.getName(),
                        createPerson.getSurname(),
                        createPerson.getPhone(),
                        createPerson.getCity()
                      
                )
        ));
    }

    
    
    public ResponseEntity<?> deletePersonREST(int id) {
        Optional<Person> p = personRepository.findById(id);

        if (p.isPresent()) {
            personRepository.delete(p.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    
    
    @Override
    public ResponseEntity<?> findByIdREST(int id) {
        Optional<Person> p = personRepository.findById(id);
        if (p.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(p.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    
    public ResponseEntity<?> getPersonNameREST(String name) {

        List<Person> personList = personRepository.findAllByName(name);
        if (personList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(personList);
    }

}

