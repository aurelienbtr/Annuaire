package uphf.comp.aurelien.annuaire.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;

// table de person

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String surname;
    private String phone;
    private String city;

    public Person() {
    }

    public Person(String name, String surname, String phone, String city) {
        super();
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
