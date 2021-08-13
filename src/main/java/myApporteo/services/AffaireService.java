package myApporteo.services;




import java.util.List;

import org.springframework.http.ResponseEntity;

import myApporteo.Dto.AffaireDto;
import myApporteo.entities.Affaire;
import myApporteo.entities.User;

public interface AffaireService extends BaseInterface<AffaireDto>{

	List<AffaireDto> findApporteurAffaire(User user);

	List<AffaireDto> findPartenaireAffaire(User user);

	void  updateAffaire(long id, Affaire newAffaire);

	void deleteAffaire(long id);


}
