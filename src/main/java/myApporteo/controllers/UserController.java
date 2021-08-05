package myApporteo.controllers;

import myApporteo.services.UserService;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myApporteo.Dto.*;
import myApporteo.entities.User;
import myApporteo.payload.response.*;
import myApporteo.repositories.*;
import myApporteo.response.UserResponse;


@RequestMapping("/")
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
    public String findUsername(@PathVariable String email) {
    	
        User user =  userRepository.findByEmail(email).orElse(null);
       
        return user.getUsername();
    }

}
