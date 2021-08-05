package myApporteo.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myApporteo.Dto.AffaireDto;
import myApporteo.entities.Affaire;
import myApporteo.payload.response.MessageResponse;
import myApporteo.repositories.AffaireRepository;
import myApporteo.services.AffaireService;
import myApporteo.services.UserService;

@RestController
@RequestMapping("/api/test")
public class AffaireController {
	@Autowired
	AffaireService affaireService;
	@Autowired
	AffaireRepository affaireRepository;
	 @Autowired
	    public AffaireController(@RequestBody AffaireService affaireService) {
	        this.affaireService = affaireService;
	    } 
	 @PostMapping("/affaire")
	 @PreAuthorize("hasRole('APPORTEUR')")
	 public ResponseEntity<?> AjouterAffaire(AffaireDto affaireDto){
		 ModelMapper modelMapper = new ModelMapper();
		 Affaire affaire=modelMapper.map(affaireDto,Affaire.class);
		 affaireRepository.save(affaire);
		 return ResponseEntity.ok(new MessageResponse("L'affairea été bien ajouté !"));
	 }
}
