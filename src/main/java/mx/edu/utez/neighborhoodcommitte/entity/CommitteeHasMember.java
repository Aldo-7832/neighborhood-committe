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

@Entity
@Table(name = "committe_has_member")
public class CommitteeHasMember implements Serializable{
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

    @ManyToOne
	@JoinColumn(name = "person", nullable = false)
    private Person person;

    @ManyToOne
	@JoinColumn(name = "committee", nullable = false)
    private Committee committee;

    @Column(name="role_member_committee", nullable = false)
	private Integer rolMemberCommittee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Committee getCommittee() {
        return committee;
    }

    public void setCommittee(Committee committee) {
        this.committee = committee;
    }

    public Integer getName() {
        return rolMemberCommittee;
    }

    public void setName(Integer name) {
        this.rolMemberCommittee = name;
    }

    
}
