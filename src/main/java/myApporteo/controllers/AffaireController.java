package myApporteo.controllers;

import java.io.IOException;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import myApporteo.Dto.AffaireDto;
import myApporteo.entities.Affaire;
import myApporteo.entities.Contrat;
import myApporteo.entities.User;
import myApporteo.payload.response.MessageResponse;
import myApporteo.repositories.AffaireRepository;
import myApporteo.repositories.ContratRepository;
import myApporteo.repositories.UsersRepository;
import myApporteo.services.AffaireService;
import myApporteo.services.ContratService;
import myApporteo.services.UserService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class AffaireController {
	@Autowired
	ContratRepository contratRepository;
	@Autowired
	UsersRepository userRepository;
	@Autowired
	AffaireService affaireService;
	@Autowired
	AffaireRepository affaireRepository;
	@Autowired
	  private ContratService storageService;
	@Autowired
	
	
	
	    public AffaireController(@RequestBody AffaireService affaireService) {
	        this.affaireService = affaireService;
	    }
	
	
	 @PostMapping("/affaire")
	 @PreAuthorize("hasRole('APPORTEUR')")
	 public ResponseEntity<?> AjouterAffaire(@RequestBody AffaireDto affaireDto){
		 
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 User user= userRepository.findByUsername(auth.getName()).orElse(null);
		 ModelMapper modelMapper = new ModelMapper();
		 modelMapper.getConfiguration().setAmbiguityIgnored(true);
		 Affaire affaire=modelMapper.map(affaireDto,Affaire.class);
		 affaire.setApporteur(user);
		 affaireRepository.save(affaire);
		 return ResponseEntity.ok(new MessageResponse("L'affaire a été bien ajouté !"));
	 }
	 
	 
	 
	 @GetMapping("/affaires")
	 @PreAuthorize("hasRole('ADMIN')")

	public List<AffaireDto> findAll() {
	    return affaireService.findAll();
	  }
	 @GetMapping("/affaire/contrat/{id}")
	 public String getContratId(@PathVariable long id) {
		 Affaire affaire=affaireRepository.findById(id).orElse(null);
		 Contrat contrat=affaire.getContrat();
		 return contrat.getId();
	 }
	 
	 
	 @GetMapping("/apporteur_affaires")
	 @PreAuthorize("hasRole('APPORTEUR')")
	 public List<AffaireDto> findByApporteur() {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 User user= userRepository.findByUsername(auth.getName()).orElse(null);
		    return affaireService.findApporteurAffaire(user);
		  }
	 
	 
	 
	 @GetMapping("/partenaire_affaires}")
	 @PreAuthorize("hasRole('PARTENAIRE')")
	 public List<AffaireDto> findByPartenaire() {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 User user= userRepository.findByUsername(auth.getName()).orElse(null);
		    return affaireService.findPartenaireAffaire(user);
		  }
	 @PreAuthorize("hasRole('PARTENAIRE')")
	 @DeleteMapping("/affaire/{id}")
	  public void deleteAffaire(@PathVariable("id") long id) {
	    affaireService.deleteAffaire(id);
	  }
	 @PreAuthorize("hasRole('ADMIN')")
	 @PutMapping("/affaireAddContrat/{id}")
	  public void addContrat(@PathVariable("id") long id, @RequestParam("file")  MultipartFile contrat) throws IOException {
		 	Affaire affaire=affaireRepository.findById(id).orElse(null);
		 	 String fileName = StringUtils.cleanPath(contrat.getOriginalFilename());
			 Contrat FileDB = new Contrat(fileName, contrat.getContentType(), contrat.getBytes());
		 	//contratRepository.save(FileDB);
		 	affaire.setContrat(FileDB);
		 	affaireService.updateAffaire(id,affaire);
		 	
		 	}
	 @PutMapping("/affaire/{id}")
	  public void updateAffiare(@PathVariable("id") long id, @RequestBody Affaire newAffaire) {
		 	affaireService.updateAffaire(id,newAffaire);
		 	}
}