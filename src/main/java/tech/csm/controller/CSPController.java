package tech.csm.controller;

import java.io.IOException;
import java.text.Format;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tech.csm.domain.BirthCertificateApplicationMaster;
import tech.csm.domain.DistrictMaster;
import tech.csm.domain.Practice;
import tech.csm.domain.StateMaster;
import tech.csm.domain.UserMaster;
import tech.csm.domain.UserRegistration;
import tech.csm.repository.PracticeRepo;
import tech.csm.service.BirthCAService;
import tech.csm.service.DistrictService;
import tech.csm.service.PracticeService;
import tech.csm.service.RegisterService;
import tech.csm.service.StateService;
import tech.csm.service.UserService;

@Controller
public class CSPController {

	@Autowired
	private StateService stateService;
	
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RegisterService registerService;

	@Autowired
	private BirthCAService bCAService;
	
	@Autowired
	private PracticeRepo practiceRepo;

	@Autowired
	private PracticeService practiceService;

	@GetMapping("/practice")
	public String practice() {
		return "practice";
	}

	@GetMapping("/homepage")
	public String getHomePage() {
		return "HomePage";
	}

	@GetMapping("/apply")
	public String applyBirthCertificate(HttpSession sess, Model model) {
		  String userName = (String) sess.getAttribute("userName");
		    String user = (String) sess.getAttribute("user");
		    if (userName == null || user == null) {
		    	sess.invalidate();  
		        return "redirect:./logout";
		    }

		    List<StateMaster> stateList = stateService.getAllStates();
		    model.addAttribute("stateList", stateList);
		    model.addAttribute("userName", userName);
		    model.addAttribute("user", user);
		    
		return "applyBirthCertificate";
	}
	
	@GetMapping("/getDistrictByState")
	public void getDistrictByState(HttpServletResponse resp, @Param("stateId") Integer stateId) {
		List<DistrictMaster> districtList = districtService.getDistrictByStateId(stateId);
		System.out.println(districtList);
		String res = "<option value='0'>-select-</option>";
		for (DistrictMaster d : districtList) {
			res += "<option value='" + d.getDistrictId() + "'>" + d.getDistrictName() + "</option>";
		}
		try {
			resp.getWriter().print(res);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/signUp")
	public String signUp() {
		return "Registration";
	}

	@GetMapping("/dashboard") 
	public String dashboard(Model model, HttpSession sess, RedirectAttributes rd) {
	    String userName = (String) sess.getAttribute("userName");
	    String user = (String) sess.getAttribute("user");
	    if (userName == null || user == null) {
	    	sess.invalidate();  
	        return "redirect:./logout";
	    }
	    model.addAttribute("userName", userName);
	    model.addAttribute("user", user);
	    
	    return "dashboard";
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
		return "redirect:./dashboard";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute UserMaster userMaster, Model model, RedirectAttributes rd, HttpSession sess) {
		System.out.println(userMaster);
		String userName = userMaster.getUserName();
		String password = userMaster.getPassword();
		System.out.println("Username = " + userName + " password = " + password);
		String res = userService.validateUser(userName, password);
		if (res.equals("User name or password is incorrect") || res.equals("Password is incorrect")) {
			model.addAttribute("msg", res);
			return "HomePage";
		}
		sess.setAttribute("userName", userName);
		sess.setAttribute("user", res);
		return "redirect:./dashboard";
	}

	@PostMapping("/applyBC")
	public String applyBC(@ModelAttribute BirthCertificateApplicationMaster birth, RedirectAttributes rd, HttpSession sess) {
	   birth.setAddressProof(birth.getFile().getOriginalFilename());
	   UserRegistration reg = registerService.findUser((String) sess.getAttribute("userName"));
	   birth.setRId(reg.getRId());
	   LocalDate today = LocalDate.now();
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       String formattedDate = today.format(formatter);
	   birth.setAppliedOn(formattedDate);
	   BirthCertificateApplicationMaster bca = bCAService.saveBC(birth);
	   System.out.println(bca);
		return "redirect:./dashboard";
	}
	
	@GetMapping("/sessionLogout")
	public String sessionLogout(HttpSession sess, RedirectAttributes rd) {
	    sess.invalidate();  // Invalidate the session
	    rd.addFlashAttribute("msg", "You have been logged out due to inactivity.");
	    return "redirect:/homepage";  // Redirect to homepage or login page
	}

   @GetMapping("/logout") 
   public String logout(HttpSession sess, RedirectAttributes rd) {
	   sess.invalidate();
	   return "redirect:./homepage";
   }
	
	@ResponseBody
	@GetMapping("/getdataByName")
	public String getDataByName(@RequestParam(value = "pageSize1", required = false) Integer pageSize,
			@RequestParam(value = "pageNumber1", required = false) Integer pageNumber,
			@RequestParam(value = "stateName", required = false) String stateName) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize - 1);
		return practiceService.getData(stateName, pageSize, pageNumber, pageable);
	}

}
