package chand.security.payload.request;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
public class UserResponse {

	private String email;

	private String username;

	private Set<String> roles;

}
