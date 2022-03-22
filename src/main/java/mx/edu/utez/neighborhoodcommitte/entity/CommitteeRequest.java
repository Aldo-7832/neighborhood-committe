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
@Table(name = "committee_request")
public class CommitteeRequest implements Serializable{
    
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

    @ManyToOne
	@JoinColumn(name = "requests_category", nullable = false)
    private RequestsCategory requestsCategory;

    @Column(name="commentary", nullable = false, length = 255)
    @NotBlank(message="El comentario no puede estar vacio")
    private String commentary;

    @Column(name="evidence", nullable = false)
    private String evidence;

    @Column(name="status", nullable = false)
    private Integer status;

    @Column(name="payment_status", nullable = false)
    private Integer payment_status;

    @Column(name="payment_count", nullable = false)
    private Double payment_count;

    @ManyToOne
	@JoinColumn(name = "user", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RequestsCategory getRequestsCategory() {
        return requestsCategory;
    }

    public void setRequestsCategory(RequestsCategory requestsCategory) {
        this.requestsCategory = requestsCategory;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(Integer payment_status) {
        this.payment_status = payment_status;
    }

    public Double getPayment_count() {
        return payment_count;
    }

    public void setPayment_count(Double payment_count) {
        this.payment_count = payment_count;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
}
