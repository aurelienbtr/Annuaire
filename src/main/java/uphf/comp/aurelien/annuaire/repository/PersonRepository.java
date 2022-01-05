package uphf.comp.aurelien.annuaire.repository;

import uphf.comp.aurelien.annuaire.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository pour les personnes
**/

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    // Trouver une personne avec son ID
    Optional<Person> findById(Integer id);

    // Recup toutes les personnes
    List<Person> findAll();

    // Recup personnes en fonction de leurs noms
    List<Person> findAllByName(String name);
}
