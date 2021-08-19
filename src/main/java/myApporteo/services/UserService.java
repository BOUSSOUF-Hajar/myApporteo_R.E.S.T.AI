package myApporteo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import myApporteo.Dto.UserDto;
import myApporteo.entities.ERole;
import myApporteo.entities.User;

public interface UserService {




  User save(UserDto dto);

  UserDto findById(Integer id);

  List<UserDto> findAll();
  List<UserDto> findByVille(String ville);

  void delete(Integer id);
  String findByEmail(String email);
  UserDto findByUsername(String username);

  User findByRoles(ERole rolePartenaire);



}
