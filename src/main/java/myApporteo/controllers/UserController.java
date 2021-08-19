package myApporteo.controllers;

import myApporteo.services.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myApporteo.Dto.*;
import myApporteo.entities.User;
import myApporteo.payload.response.*;
import myApporteo.repositories.*;
import myApporteo.response.UserResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/test")
@RestController
public class UserController {
	private final UserService userService;
	@Autowired
	UsersRepository userRepository;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    } 
   
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> findAll() {
        List<UserDto> userDtos = userService.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        userDtos.forEach(el -> {
            userResponses.add(modelMapper.map(el, UserResponse.class));
        });
        return new ResponseEntity<List<UserResponse>>(userResponses, HttpStatus.OK);
    }
    @GetMapping("/user/{email}")
    public boolean findUser(@PathVariable String email) {
    	
        User user =  userRepository.findByEmail(email).orElse(null);
        if (user==null) {
        	return true;
        }
        
        return false;
    }
    @GetMapping("/username/{username}")
    public boolean findUsername(@PathVariable String username) {
    	
        User user =  userRepository.findByUsername(username).orElse(null);
        if (user==null) {
        	return true;
        }
        
        return false;
    }
    @PreAuthorize("hasRole('APPORTEUR') or hasRole('PARTENAIRE')")
    @GetMapping("/currentuser")
    public UserDto findbyid(){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 User user= userRepository.findByUsername(auth.getName()).orElse(null);
    	
    			ModelMapper modelMapper = new ModelMapper();
        
         UserDto dto=modelMapper.map(user, UserDto.class);
        return dto;
    	}
    @PutMapping("/user/update")
    public UserDto updateUser(@RequestBody UserDto userDto) {
    	User user=userRepository.findById(userDto.getId()).orElse(null);
    	user.setAdresse(userDto.getAdresse());
    	user.setCCI(userDto.getCCI());
    	user.setCodePostal(userDto.getCodePostal());
    	user.setEmail(userDto.getEmail());
    	user.setMoteDePasse(userDto.getMoteDePasse());
    	user.setNomAgence(userDto.getNomAgence());
    	user.setNomSociete(userDto.getNomSociete());
    	user.setNumCarteT(userDto.getNumCarteT());
    	user.setSiret(userDto.getSiret());
    	user.setTelephone(userDto.getTelephone());
    	user.setType(userDto.getType());
    	user.setUsername(userDto.getUsername());
    	user.setVille(userDto.getVille());
    	userRepository.save(user);
    	ModelMapper modelMapper = new ModelMapper();
    	UserDto dto=modelMapper.map(user, UserDto.class);
    	return dto;
    	
    	
    }
    	
    
    	

}
