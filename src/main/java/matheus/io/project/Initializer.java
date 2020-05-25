package matheus.io.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import matheus.io.project.entity.*;
import matheus.io.project.repository.*;

@Component
public class Initializer implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		//exampleOne_Relationship_Enums_Hibernate();
		//exampleTwo_Pageable();
exampleThree();
	}
	
	public void exampleOne_Relationship_Enums_Hibernate() {
		Role role = new Role();

		role.setName("Admin");
		role.setStatus(StatusRole.ATIVO);

		Role role2 = new Role();

		role2.setName("Aluno");
		role2.setStatus(StatusRole.INATIVO);

		this.roleRepository.save(role);
		this.roleRepository.save(role2);

		User user = new User();

		user.setName("Matheus");
		user.setEmail("Matheus.Muniz@gmail.com");
		user.setRole(role);

		User user2 = new User();

		user2.setName("Henrique");
		user2.setEmail("Henrique.Muniz@gmail.com");
		user2.setRole(role);

		this.userRepository.save(user);
		this.userRepository.save(user2);

		List<Role> roles = this.roleRepository.findByStatus(StatusRole.ATIVO);

		for (Role r : roles) {
			System.out.println(r.getName());
		}
	}
	
	public void exampleTwo_Pageable() {
		
		for(int i=0; i<1000; i++) {
			saveRole("Role"+i,StatusRole.ATIVO);
		}
		
		for(int i=1000; i<1250; i++) {
			saveRole("Role"+i,StatusRole.INATIVO);
		}
		
		PageRequest pageable = PageRequest.of(3, 10);
		
		Page<Role> roles = this.roleRepository.findAll(pageable);
		 
		for (Role r : roles) {
			System.out.println(r.getName());
		}

	}
	
	public void saveRole(String name, StatusRole status) {
		Role role = new Role(name, status);
		this.roleRepository.save(role);
		
	}
	
	public void exampleThree() {
		
		Role role = new Role();

		role.setName("Admin");
		role.setStatus(StatusRole.ATIVO);
		
		User user = new User();

		user.setName("Matheus");
		user.setEmail("Matheus.Muniz@gmail.com");
		user.setRole(role);
		
		this.userRepository.save(user);
		
		List<User> users = this.userRepository.findAll();
		
		for (User user2 : users) {
			System.out.println(user2.getRole().getName());
		}
		
	}
		

}
