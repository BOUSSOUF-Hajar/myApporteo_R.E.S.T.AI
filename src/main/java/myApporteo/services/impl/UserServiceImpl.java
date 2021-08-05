package myApporteo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myApporteo.Dto.UserDto;
import myApporteo.entities.User;
import myApporteo.repositories.UsersRepository;
import myApporteo.services.UserService;
@Service
public class UserServiceImpl implements UserService  {
	@Autowired
	private UsersRepository usersRepository ;
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

}
