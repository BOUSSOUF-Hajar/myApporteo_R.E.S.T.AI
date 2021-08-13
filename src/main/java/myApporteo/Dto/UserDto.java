package myApporteo.Dto;

import myApporteo.entities.Role;
import  myApporteo.entities.User;
import myApporteo.entities.Affaire;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto  implements Serializable {


  private Long id;
  private String username;
  private String email;
  private String adresse;
  private String telephone;
  private  String type;
  private String password;
  private Instant dateDeNaissance;
  @JsonIgnore
  private List<AffaireDto> affairesApporteur;
  @JsonIgnore
  private List<AffaireDto> affairesPrtenaire;
  private String nomAgence;

  private String nomSociete;

  private String Siret;
  private Long numCarteT;

  private String CCI;

  private String ville;

  private String codePostal;
  private String moteDePasse;
  private List<RoleDto> roles;

 /* public static UserDto fromEntity(User utilisateur) {
    if (utilisateur == null) {
      return null;
    }
    ModelMapper mapper=new ModelMapper();
    mapper.map(utilisateur, UserDto.class);
    mapper.map(utilisateur, UserDto.class);
    
  }

  public static User toEntity(UserDto dto) {
    if (dto == null) {
      return null;
    }

    User utilisateur = new User();
    utilisateur.setId(dto.getId());
    utilisateur.setUsername(dto.getUsername());
    utilisateur.setEmail(dto.getEmail());
    utilisateur.setMoteDePasse(dto.getMoteDePasse());
   

    return utilisateur;
  }*/
}
