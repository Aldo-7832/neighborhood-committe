package mx.edu.utez.neighborhoodcommitte.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "person")
public class Person implements Serializable{
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

    @Column(name="name", nullable = false, length = 30)
    @NotBlank(message="El nombre no puede estar vacio")
	private String name;

    @Column(name="lastname", nullable = false, length = 30)
    @NotBlank(message="El apellido no puede estar vacio")
	private String lastname;

    @Column(name="surname", length = 30)
	private String surname;

    @Column(name="phone", nullable = false, length = 15)
    @NotBlank(message="El teléfono no puede estar vacio")
	private String phone;

    @Column(name="identification", nullable = false)
	private String identification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    
}
