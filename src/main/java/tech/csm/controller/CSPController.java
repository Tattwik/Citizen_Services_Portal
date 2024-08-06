package tech.csm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tech.csm.domain.UserRegistration;

@Controller
public class CSPController {

	@GetMapping("/homepage")
	public String getHomePage() {
		return "HomePage";
	}
	
	@GetMapping("/signUp")
	public String signUp() {
		return "Registration";
	}
	
	@PostMapping("/register")
	public String register(Model mode, @ModelAttribute UserRegistration userRegistration, RedirectAttributes rd) {
		System.out.println(userRegistration);
		return "redirect:./homepage";
	}
	
}
