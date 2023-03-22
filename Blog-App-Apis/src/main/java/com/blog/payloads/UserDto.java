package com.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int u_id;
	@NotEmpty
	@Size(min=4, message= "Your name must be at least 4 charecter.")
	private String u_name;
	@Email(message= "Email not valid!")
	private String u_gmail;
	@NotEmpty
	@Size(min=4, max=10, message="your password must be minimum 3 charecter and maximum 10 charecter.")
	private String password;
	

}
