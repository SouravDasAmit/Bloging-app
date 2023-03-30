package com.blog;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blog.config.AppContants;
import com.blog.entities.Role;
import com.blog.entities.User;
import com.blog.repositories.RoleRepo;

@SpringBootApplication

public class BlogAppApisApplication implements CommandLineRunner{
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepo roleRepo;
	
	public static void main(String[] args) 
	{
		SpringApplication.run(BlogAppApisApplication.class, args);
	}
	@Bean
	public ModelMapper modelMappper()
	{
		return new ModelMapper();
	}
	@Override
	public void run(String... args) throws Exception {
		
		try 
		{
			Role role= new Role();
			role.setRoleId(AppContants.ROLE_ADMIN);
			role.setRoleName("ROLE_ADMIN");
			
			Role role1= new Role();
			role1.setRoleId(AppContants.ROLE_USER);
			role1.setRoleName("ROLE_USER");
			
			List<Role> roles= List.of(role, role1);
			List<Role> result= this.roleRepo.saveAll(roles);
			result.forEach(r->{
				System.out.println(r.getRoleName());
			});
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//System.out.println(this.passwordEncoder.encode("amitdas"));
		/*Admin user id 
		{
		    "email": "sourav@gmail.com",
		    "password": "sourav"
		}
		User user id
		{
		    "email": "amitDas@gmail.com",
		    "password": "sourav"
		}*/
		
	}
}
