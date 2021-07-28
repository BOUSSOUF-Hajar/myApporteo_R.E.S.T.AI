package myApporteo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import myApporteo.Dto.UserDto;
import myApporteo.entities.User;

public interface UserService {




  User save(UserDto dto);

  UserDto findById(Integer id);

  List<UserDto> findAll();

  void delete(Integer id);

  UserDto findByEmail(String email);



}
