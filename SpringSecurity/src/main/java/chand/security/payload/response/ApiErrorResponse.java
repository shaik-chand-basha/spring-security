package chand.security.payload.response;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class ApiErrorResponse {

	public ApiErrorResponse() {
		errors = new ArrayList<>();
		timestamp =new Date();
	}

	private Date timestamp;

	private Integer status;

	private String error;

	private String message;

	private List<Map<String, Object>> errors;

	public void addError(String field, String message) {
		Map<String, Object> error = new HashMap<>();
		error.put("field", field);
		error.put("messages", message);
		this.errors.add(error);
	}

}
