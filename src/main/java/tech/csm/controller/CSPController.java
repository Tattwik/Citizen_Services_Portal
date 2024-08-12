package tech.csm.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpServletResponse;
import tech.csm.domain.Practice;
import tech.csm.domain.UserMaster;
import tech.csm.domain.UserRegistration;
import tech.csm.repository.PracticeRepo;
import tech.csm.service.RegisterService;
import tech.csm.service.UserService;

@Controller
public class CSPController {

	@Autowired
	private UserService userService;

	@Autowired
	private RegisterService registerService;

	@Autowired
	private PracticeRepo practiceRepo;

	@GetMapping("/homepage")
	public String getHomePage() {
		return "HomePage";
	}

	@GetMapping("/signUp")
	public String signUp() {
		return "Registration";
	}

	@PostMapping("/register")
	public String register(Model model, @ModelAttribute UserRegistration userRegistration, RedirectAttributes rd) {
		System.out.println(userRegistration);
		UserMaster user = new UserMaster();
		user.setUserName(userRegistration.getUserName());
		user.setPassword(userRegistration.getPassword());
		user.setUserRole("User");
		UserMaster u = userService.saveUser(user);
		System.out.println(u);
		userRegistration.setUserMaster(u);
		UserRegistration u1 = registerService.registerUser(userRegistration);
		System.out.println(u1);
		return "redirect:./homepage";
	}

	@GetMapping("/practice")
	public String practice() {
		return "practice";
	}

	@ResponseBody
	@GetMapping("/getdata")
	public Map<String, Object> getData(HttpServletResponse resp) throws IOException {
		List<Practice> data = practiceRepo.findAll();
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("data", data);
		return dataMap;

	}
}
