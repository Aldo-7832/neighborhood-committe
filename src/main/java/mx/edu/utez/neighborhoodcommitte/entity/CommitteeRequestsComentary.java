package mx.edu.utez.neighborhoodcommitte.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "committee_request_comentary")
public class CommitteeRequestsComentary implements Serializable{

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

    @Column(name="comentary", length = 255)
    private String comentary;

    @Column(name="date_registered", nullable = false, length = 255)
    private Date dateRegistered;

    @ManyToOne
	@JoinColumn(name = "committee_request", nullable = false)
    private CommitteeRequest committeeRequest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentary() {
        return comentary;
    }

    public void setComentary(String comentary) {
        this.comentary = comentary;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public CommitteeRequest getCommitteeRequest() {
        return committeeRequest;
    }

    public void setCommitteeRequest(CommitteeRequest committeeRequest) {
        this.committeeRequest = committeeRequest;
    }

    
}
