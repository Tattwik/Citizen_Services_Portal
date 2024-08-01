package tech.csm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CSPController {

	@GetMapping("/homepage")
	public String getHomePage() {
		return "HomePage";
	}
	
	
}
