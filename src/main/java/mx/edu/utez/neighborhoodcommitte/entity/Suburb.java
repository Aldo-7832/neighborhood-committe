package mx.edu.utez.neighborhoodcommitte.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "suburb")
public class Suburb implements Serializable{

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

    @Column(name="name", nullable = false, length = 50)
    @NotBlank(message="El nombre no puede estar vacio")
	private String name;

    @Column(name="postal_code", nullable = false, length = 15)
    @NotBlank(message="El código postal no puede estar vacio")
	private String postalCode;

    @ManyToOne
	@JoinColumn(name = "city", nullable = false)
    private City city;

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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    
}
