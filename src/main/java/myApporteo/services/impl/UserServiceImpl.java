package myApporteo.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myApporteo.Dto.UserDto;
import myApporteo.entities.ERole;
import myApporteo.entities.User;
import myApporteo.repositories.RoleRepository;
import myApporteo.repositories.UsersRepository;
import myApporteo.services.UserService;
@Service
public class UserServiceImpl implements UserService  {
	@Autowired
	private UsersRepository usersRepository ;
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public User save(UserDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	 @Override
	    public List<UserDto> findAll() {
	        ModelMapper modelMapper = new ModelMapper();
	        List<User> users = usersRepository.findAll();
	        List<UserDto> userDtos = new ArrayList<>();
	        users.forEach(el -> {
	            UserDto user = modelMapper.map(el, UserDto.class);
	            userDtos.add(user);
	        });
	        return userDtos;
	    }

	public UserServiceImpl(UsersRepository usersRepository) {
		super();
		this.usersRepository = usersRepository;
	}

	

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	 public UserDto findByUsername(String username) {
        ModelMapper modelMapper = new ModelMapper();
       
        User user = usersRepository.findByUsername(username).orElse(null);
        UserDto userDto = new UserDto();
        
            userDto= modelMapper.map(user, UserDto.class);
           
        
        return userDto;
    }

	@Override
	public String findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> findByVille(String ville) {
		ModelMapper modelMapper = new ModelMapper();
		List<User> users=usersRepository.findByVille(ville);
		List<UserDto> userDtos = new ArrayList<>();
		 users.forEach(el -> {
	            UserDto user = modelMapper.map(el, UserDto.class);
	            userDtos.add(user);
	            
	        });
	        return userDtos;
		
	}

	@Override
	public User findByRoles(ERole rolePartenaire) {
		List<User> users=usersRepository.findByRoles(roleRepository.findByName(rolePartenaire).orElse(null));
		 Random rand = new Random();
		    User user = users.get(rand.nextInt(users.size()));
		return user;
	}

}
