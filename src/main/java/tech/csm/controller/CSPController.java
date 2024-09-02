package tech.csm.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpServletResponse;
import tech.csm.domain.Practice;
import tech.csm.domain.UserMaster;
import tech.csm.domain.UserRegistration;
import tech.csm.repository.PracticeRepo;
import tech.csm.service.PracticeService;
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
	
	@Autowired
	private PracticeService practiceService;

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
		userRegistration.setUserName(u.getUserName());
		userRegistration.setUId(u.getUId());
		UserRegistration u1 = registerService.registerUser(userRegistration);
		System.out.println(u1);
		return "redirect:./homepage";
	}

	@GetMapping("/practice")
	public String practice() {
		return "practice";
	}

	@PostMapping("/login") 
	public String login(@ModelAttribute UserMaster userMaster, Model model, RedirectAttributes rd ) {
		System.out.println(userMaster);
		String userName = userMaster.getUserName();
		String password = userMaster.getPassword();
		String res = userService.validateUser(userName, password);
	    model.addAttribute("msg", "Hello");
        return "HomePage";
	}
	
	@ResponseBody
	@GetMapping("/getdataByName")
	public String getDataByName(@RequestParam(value = "pageSize1", required = false) Integer pageSize,
			@RequestParam(value = "pageNumber1", required = false) Integer pageNumber,
			@RequestParam(value = "stateName", required = false) String stateName) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize - 1);
		return practiceService.getData(stateName,pageSize, pageNumber,pageable);
	}
}
