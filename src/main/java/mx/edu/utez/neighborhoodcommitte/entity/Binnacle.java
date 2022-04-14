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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "binnacle")
public class Binnacle implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "activity", nullable = false, length = 50)
    private String activity;

    @Column(name = "activity_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date activityDate;

    @Column(name = "affected_table", nullable = false, length = 100)
    private String affectedTable;

    @ManyToOne
    @JoinColumn(name = "responsible", nullable = false)
    private Users responsible;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public String getAffectedTable() {
        return affectedTable;
    }

    public void setAffectedTable(String affectedTable) {
        this.affectedTable = affectedTable;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

}
