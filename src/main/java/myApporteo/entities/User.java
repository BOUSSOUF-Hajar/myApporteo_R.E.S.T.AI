package myApporteo.entities;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
public class User extends AbstractEntity{
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
    private String nomAgence;

    private String nomSociete;

    private String Siret;

    private Long numCarteT;

    private String CCI;

    private String ville;

    private String codePostal;

    @OneToMany(mappedBy = "partenaire")
    private List<affaire> affairesPartenaire;

    private  String type;
    @Column(name = "datedenaissance")
    private Instant dateDeNaissance;
    
    @OneToMany(mappedBy = "apporteur")
    private List<affaire> affairesApporteur;
    
    
    
    public User(String nom, String email, String moteDePasse
    ) {
        this.username = nom;
        this.email = email;
        this.moteDePasse = moteDePasse;
    }

    public User() {

    }

    public User(String nom, String email, String moteDePasse, String adresse, String telephone, Set<Role> roles) {
        this.username = nom;
        this.email = email;
        this.moteDePasse = moteDePasse;
        this.adresse = adresse;
        this.telephone = telephone;
        this.roles = roles;
    }
    
    public String getNomAgence() {
		return nomAgence;
	}

	public void setNomAgence(String nomAgence) {
		this.nomAgence = nomAgence;
	}

	public String getNomSociete() {
		return nomSociete;
	}

	public void setNomSociete(String nomSociete) {
		this.nomSociete = nomSociete;
	}

	public String getSiret() {
		return Siret;
	}

	public void setSiret(String siret) {
		Siret = siret;
	}

	public Long getNumCarteT() {
		return numCarteT;
	}

	public void setNumCarteT(Long numCarteT) {
		this.numCarteT = numCarteT;
	}

	public String getCCI() {
		return CCI;
	}

	public void setCCI(String cCI) {
		CCI = cCI;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public List<affaire> getAffairesPartenaire() {
		return affairesPartenaire;
	}

	public void setAffairesPartenaire(List<affaire> affairesPartenaire) {
		this.affairesPartenaire = affairesPartenaire;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Instant getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(Instant dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public List<affaire> getAffairesApporteur() {
		return affairesApporteur;
	}

	public void setAffairesApporteur(List<affaire> affairesApporteur) {
		this.affairesApporteur = affairesApporteur;
	}


	public User(String username, String email, String moteDePasse, String adresse, String telephone,
			String nomAgence, String nomSociete, String siret, Long numCarteT, String cCI, String ville,
			String codePostal, List<affaire> affairesPartenaire, String type, Instant dateDeNaissance,
			List<affaire> affairesApporteur) {
		super();
		this.username = username;
		this.email = email;
		this.moteDePasse = moteDePasse;
		this.adresse = adresse;
		this.telephone = telephone;
		this.roles = roles;
		this.nomAgence = nomAgence;
		this.nomSociete = nomSociete;
		Siret = siret;
		this.numCarteT = numCarteT;
		CCI = cCI;
		this.ville = ville;
		this.codePostal = codePostal;
		this.affairesPartenaire = affairesPartenaire;
		this.type = type;
		this.dateDeNaissance = dateDeNaissance;
		this.affairesApporteur = affairesApporteur;
	}

	public User(String username, String email, String moteDePasse, String adresse, String telephone, Set<Role> roles,
			Instant dateDeNaissance, List<affaire> affairesApporteur) {
		super();
		this.username = username;
		this.email = email;
		this.moteDePasse = moteDePasse;
		this.adresse = adresse;
		this.telephone = telephone;
		this.roles = roles;
		this.dateDeNaissance = dateDeNaissance;
		this.affairesApporteur = affairesApporteur;
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
