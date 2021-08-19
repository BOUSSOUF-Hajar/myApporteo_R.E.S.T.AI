package myApporteo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.Random;

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
import myApporteo.Dto.UserDto;
import myApporteo.entities.Affaire;
import myApporteo.entities.Contrat;
import myApporteo.entities.ERole;
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
	UserService userService;
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
	 @GetMapping("/admin/contrat/{id}")
	 public String getAdminContratId(@PathVariable long id) {
		 Affaire affaire=affaireRepository.findById(id).orElse(null);
		 Contrat contrat=affaire.getContratAdmin();
		 return contrat.getId();
	 }
	 @GetMapping("/app/contrat/{id}")
	 public String getAppContratId(@PathVariable long id) {
		 Affaire affaire=affaireRepository.findById(id).orElse(null);
		 Contrat contrat=affaire.getContratApp();
		 return contrat.getId();
	 }
	 
	 
	 @GetMapping("/apporteur_affaires")
	 @PreAuthorize("hasRole('APPORTEUR')")
	 public List<AffaireDto> findByApporteur() {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 User user= userRepository.findByUsername(auth.getName()).orElse(null);
		    return affaireService.findApporteurAffaire(user);
		  }
	 
	 
	 
	 @GetMapping("/partenaire_affaires")
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
	 @PutMapping("/adminAddContrat/{id}")
	  public void adminAddContrat(@PathVariable("id") long id, @RequestParam("file")  MultipartFile contrat) throws IOException {
		 	Affaire affaire=affaireRepository.findById(id).orElse(null);
		 	 String fileName = StringUtils.cleanPath(contrat.getOriginalFilename());
			 Contrat FileDB = new Contrat(fileName, contrat.getContentType(), contrat.getBytes());
		 	//contratRepository.save(FileDB);
		 	affaire.setContratAdmin(FileDB);
		 	affaire.setStatut("En vente");
		 	affaireService.updateAffaire(id,affaire);
		 	
		 	}
	 @PreAuthorize("hasRole('PARTENAIRE')")
	 @PutMapping("/confirmerVente/{id}")
	  public void confirerVente(@PathVariable("id") long id)  {
		 	Affaire affaire=affaireRepository.findById(id).orElse(null);
		 	
		 	affaire.setStatut("Vendu");
		 	affaireService.updateAffaire(id,affaire);
		 	
		 	}
	 
	 @PreAuthorize("hasRole('APPORTEUR')")
	 @PutMapping("/appAddContrat/{id}")
	  public void appAddContrat(@PathVariable("id") long id, @RequestParam("file")  MultipartFile contrat) throws IOException {
		 	Affaire affaire=affaireRepository.findById(id).orElse(null);
		 	 String fileName = StringUtils.cleanPath(contrat.getOriginalFilename());
			 Contrat FileDB = new Contrat(fileName, contrat.getContentType(), contrat.getBytes());
		 	//contratRepository.save(FileDB);
		 	affaire.setContratApp(FileDB);
		 	affaire.setStatut("Contrat signé");
		 	affaireService.updateAffaire(id,affaire);
		 	
		 	}
	 @PutMapping("/addPartenaire")
	 public void affecterpartenaire(@RequestBody AffaireDto affaire) {
		 Affaire affairey=affaireRepository.getById(affaire.getId());
		 List<UserDto> userPart=new ArrayList<UserDto>();
		 List<UserDto> users=userService.findByVille(affairey.getVille());
		 for(int i=0;i<users.size();i++) {
			 if(users.get(i).getRoles().get(0).getName()==ERole.ROLE_PARTENAIRE) {
				 userPart.add(users.get(i));
			 }
			
		 }
		 if(userPart.size()==0) {
			 User user=userService.findByRoles(ERole.ROLE_PARTENAIRE);
			 
			 ModelMapper modelMapper = new ModelMapper();
			   UserDto userDto = modelMapper.map(user, UserDto.class);
					 userPart.add(userDto);
				
		 }
		 
		
		 Random rand = new Random();
		    UserDto randomElement = userPart.get(rand.nextInt(userPart.size()));
		    System.out.println("choosen  :"+ randomElement);
		    ModelMapper modelMapper = new ModelMapper();
		   User user = modelMapper.map(randomElement, User.class);
		    affairey.setPartenaire(user);
		    affaireService.updateAffaire(affairey.getId(),affairey);
	 }
	 @PutMapping("/affaire/{id}")
	  public void updateAffiare(@PathVariable("id") long id, @RequestBody Affaire newAffaire) {
		 	affaireService.updateAffaire(id,newAffaire);
		 	}
}