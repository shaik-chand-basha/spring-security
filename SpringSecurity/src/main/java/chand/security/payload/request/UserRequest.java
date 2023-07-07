package chand.security.payload.request;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
public class UserRequest {

	@NotBlank
	private String email;
	
	@NotBlank
	@Size(min = 6,max = 30,message = "Password should be minimum 6 characters and maximum 30 characters")
	private String password;
	
	@NotBlank
	private String username;
	
	@Size(min = 1,message="Atleast one role required")
	private Set<String> roles;
	
	@JsonProperty(defaultValue = "true")
	private boolean enabled;
	
}
