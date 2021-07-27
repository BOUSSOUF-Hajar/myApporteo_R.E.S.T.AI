package myApporteo.security.services;

import myApporteo.entities.users;
import myApporteo.repositories.UsersRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UsersRepository userRepository;

    @SneakyThrows
    @Override
    @Transactional
    public UsersDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User Not Found with username: " + username));

        return UsersDetails.build(user);
    }

}
