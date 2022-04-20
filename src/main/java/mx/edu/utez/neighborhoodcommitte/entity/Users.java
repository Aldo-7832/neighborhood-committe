package mx.edu.utez.neighborhoodcommitte.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class Users implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    @NotBlank(message="El nombre no puede estar vacio")
    private String name;

    @Column(name = "lastname", nullable = false, length = 150)
    @NotBlank(message="El apellido no puede estar vacio")
    private String lastName;

    @Column(name = "surname", nullable = true, length = 150)
    @NotBlank(message="El apellido no puede estar vacio")
    private String surname;

    @Column(name = "username", nullable = false, length = 150, unique = true)
    @NotBlank(message="El nombre de usuario no puede estar vacio")
    private String username;

    @Column(name = "phone", nullable = false, length = 20)
    @NotBlank(message="El número no puede estar vacio")
    private String phone;

    @Column(name = "password", nullable = false, length = 255)
    @NotBlank(message="La contraseña no puede estar vacia")
    private String password;

    @Column(name = "enabled", nullable = false)
    private int enabled;

    @Column(name = "profile_picture", nullable = false, length = 50)
    private String profilePicture;

    @ManyToOne
    @JoinColumn(name = "committee", nullable = false)
    private Committee committee;

    @Column(name = "email", nullable = false, length = 150, unique = true)
    @NotBlank(message="El Correo electronico no puede estar vacio")
    private String email;

    @Column(name = "employee_number", nullable = true, length = 100)
    private String employeeNumber;

    @Column(name = "registered_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredDate;

    @Column(name = "login_date",nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginDate;

    @Column(name = "logout_date",nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date logoutDate;

    @ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user"), inverseJoinColumns = @JoinColumn(name = "role"))
	private Set<Roles> roles;

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Committee getCommittee() {
        return committee;
    }

    public void setCommittee(Committee committee) {
        this.committee = committee;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Date getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(Date logoutDate) {
        this.logoutDate = logoutDate;
    }

    // Metodo para agregar roles
    public void agregarRol(Roles role) {
        if (roles == null) {
            roles = new HashSet<Roles>();
        }
        roles.add(role);
    }

    @Override
    public String toString() {
        return "Users [committee=" + committee + ", email=" + email + ", employeeNumber=" + employeeNumber
                + ", enabled=" + enabled + ", id=" + id + ", lastName=" + lastName + ", loginDate=" + loginDate
                + ", logoutDate=" + logoutDate + ", name=" + name + ", password=" + password + ", phone=" + phone
                + ", profilePicture=" + profilePicture + ", registeredDate=" + registeredDate + ", roles=" + roles
                + ", surname=" + surname + ", username=" + username + "]";
    } 

    
}
