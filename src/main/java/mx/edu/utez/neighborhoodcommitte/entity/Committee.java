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
@Table(name = "committee")
public class Committee implements Serializable{
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

    @Column(name="name", nullable = false, length = 150)
    @NotBlank(message="Este campo no puede estar vacio")
	private String name;

    @ManyToOne
	@JoinColumn(name = "suburb", nullable = false)
    private Suburb suburb;

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

    public Suburb getSuburb() {
        return suburb;
    }

    public void setSuburb(Suburb suburb) {
        this.suburb = suburb;
    }

    
}
