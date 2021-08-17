package myApporteo.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.SecondaryTable;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import myApporteo.Dto.AffaireDto;
import myApporteo.entities.Affaire;
import myApporteo.entities.User;
import myApporteo.repositories.AffaireRepository;
import myApporteo.services.AffaireService;

@Service
public class AffaireServiceImpl implements AffaireService {
	@Autowired
	AffaireRepository affaireRepository;
	@Autowired
	AffaireService affaireService;
	@Override
	    public AffaireDto save(AffaireDto affaireDto) {
		ModelMapper modelMapper = new ModelMapper();
		Affaire affaire=modelMapper.map(affaireDto,Affaire.class);
	affaireRepository.save(affaire);
	AffaireDto affaireSaved=modelMapper.map(affaire,AffaireDto.class);
	        return affaireSaved;
	    }

	    @Override
	    public List<AffaireDto> findAll() {
	    	ModelMapper modelMapper = new ModelMapper();
	    	modelMapper.getConfiguration().setAmbiguityIgnored(true);
	    	List<Affaire> affaires=affaireRepository.findAll();
	    	List<AffaireDto> dtos=new ArrayList<AffaireDto>();
	    	for(Affaire affaire : affaires) {
	    		
	    		dtos.add(modelMapper.map(affaire,AffaireDto.class));
	    	}
	    	
	    	      return dtos;  
	    	  }

		@Override
		public AffaireDto findById(Integer id) {
			// TODO Auto-generated method stub
			return null;
		}
	       
	    
		
		@Override
	    public List<AffaireDto> findApporteurAffaire(User user) {
	    	ModelMapper modelMapper = new ModelMapper();
	    	modelMapper.getConfiguration().setAmbiguityIgnored(true);
	    	List<Affaire> affaires=affaireRepository.findByApporteur(user);
	    	List<AffaireDto> dtos=new ArrayList<AffaireDto>();
	    	for(Affaire affaire : affaires) {
	    		
	    		dtos.add(modelMapper.map(affaire,AffaireDto.class));
	    	}
	    	      return dtos;  
	    	  }

		@Override
		public List<AffaireDto> findPartenaireAffaire(User user) {
			
			ModelMapper modelMapper = new ModelMapper();
	    	modelMapper.getConfiguration().setAmbiguityIgnored(true);
	    	List<Affaire> affaires=affaireRepository.findByPartenaire(user);
	    	List<AffaireDto> dtos=new ArrayList<AffaireDto>();
	    	for(Affaire affaire : affaires) {
	    		
	    		dtos.add(modelMapper.map(affaire,AffaireDto.class));
	    		
	    	}
	    	
	    	
	    	      return dtos;  
			
		}

		@Override
		public void updateAffaire(long id, Affaire newAffaire) {
			Affaire affaire=affaireRepository.getById(id);
			affaire.setAdresse(newAffaire.getAdresse());
			affaire.setApporteur(newAffaire.getApporteur());
			affaire.setEmailProp(newAffaire.getEmailProp());
			affaire.setCodePostal(newAffaire.getCodePostal());
			affaire.setNomPrenom(newAffaire.getNomPrenom());
			affaire.setPartenaire(newAffaire.getPartenaire());
			affaire.setTelephoneProp(newAffaire.getTelephoneProp());
			affaire.setType(newAffaire.getType());
			affaire.setContrat(newAffaire.getContrat());
			affaire.setVille(newAffaire.getVille());
			affaireRepository.save(affaire);
			
		}

		@Override
		public void deleteAffaire(long id) {
			affaireRepository.deleteById(id);
			;
			
		}
	   

}
