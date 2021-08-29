package myApporteo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import myApporteo.entities.ERole;
import myApporteo.entities.Role;
import myApporteo.repositories.RoleRepository;

@SpringBootApplication
@EnableJpaAuditing
public class myApporteoApplication {

	public static void main(String[] args) {
		SpringApplication.run( myApporteoApplication.class, args);
	}
	 public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 @Bean
		public CommandLineRunner demo(RoleRepository repository) {
			return (args) -> {
				/*Role roleapp=new Role();
				roleapp.setName(ERole.ROLE_APPORTEUR);	
				repository.save(roleapp);
				Role rolepart=new Role();
				rolepart.setName(ERole.ROLE_PARTENAIRE);
				repository.save(rolepart);
				Role roleadmin=new Role();
				roleadmin.setName(ERole.ROLE_ADMIN);
				repository.save(roleadmin);*/
			};
		}
	
}
