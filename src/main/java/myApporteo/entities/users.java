package myApporteo.entities;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
public class users extends AbstractEntity{
    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "motdepasse")
    private String moteDePasse;

    @Column(name = "adresse")
    private String adresse;
    @Column(name = "telephone")
    private String telephone;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
 
    private Set<Role> roles = new HashSet<>();
    public users(String nom, String email, String moteDePasse
    ) {
        this.username = nom;
        this.email = email;
        this.moteDePasse = moteDePasse;
    }

    public users() {

    }

    public users(String nom, String email, String moteDePasse, String adresse, String telephone, Set<Role> roles) {
        this.username = nom;
        this.email = email;
        this.moteDePasse = moteDePasse;
        this.adresse = adresse;
        this.telephone = telephone;
        this.roles = roles;
    }

    public void setUsername(String nom) {
        this.username = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMoteDePasse(String moteDePasse) {
        this.moteDePasse = moteDePasse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }



    public String getEmail() {
        return email;
    }

    public String getMoteDePasse() {
        return moteDePasse;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public Set<Role> getRoles() {
        return roles;
    }

  }
