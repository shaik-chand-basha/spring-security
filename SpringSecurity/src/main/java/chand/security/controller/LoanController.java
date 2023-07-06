package chand.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

	@GetMapping("/myLoans")
	public String getMyLoadDetails() {
		return "My load details";
	}
}
