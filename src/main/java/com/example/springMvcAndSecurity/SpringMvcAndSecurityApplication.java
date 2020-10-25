package com.example.springMvcAndSecurity;

import com.example.springMvcAndSecurity.entities.Role;
import com.example.springMvcAndSecurity.entities.User;
import com.example.springMvcAndSecurity.repositories.RoleRepository;
import com.example.springMvcAndSecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.Arrays;

@SpringBootApplication
public class SpringMvcAndSecurityApplication {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	public static void main(String[] args) {
		SpringApplication.run(SpringMvcAndSecurityApplication.class, args);

	}

//	@Override
//	public void run(String... args) throws Exception {
//		Role r = new Role();
//		r.setName("ROLE_ADMIN");
//		Role r2 = new Role();
//		r2.setName("ROLE_USER");
//		User user = new User("admin","admin@gmail.com","1234");
//		roleRepository.save(r);
//		roleRepository.save(r2);
//
//		user.getRoles().add(roleRepository.findByName("ROLE_ADMIN"));
//		userRepository.save(user);
//	}
}
