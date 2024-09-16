package tech.csm.controller;

import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	@GetMapping("/view")
	public String viewBirthCertificate(HttpSession sess, Model model) {
		 String userName = (String) sess.getAttribute("userName");
		    String user = (String) sess.getAttribute("user");
		    if (userName == null || user == null) {
		    	sess.invalidate();  
		        return "redirect:./logout";
		    }
            UserRegistration  reg = registerService.findUser(userName);
		    List<BirthCertificateApplicationMaster> birthList = bCAService.getAllByrId(reg.getRId());
		    model.addAttribute("birthList", birthList);
		    model.addAttribute("userName", userName);
		    model.addAttribute("user", user);
		    
		return "viewBirthCertificate";
	}
	
	@GetMapping("/viewAdmin")
	public String viewAdmin(HttpSession sess, Model model) {
		return null;
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

    @GetMapping("/adminDashboard")
    public String adminDashboard(HttpSession sess, RedirectAttributes rd, Model model) {
    	String userName = (String) sess.getAttribute("userName");
	    String user = (String) sess.getAttribute("user");
	    if (userName == null || user == null) {
	    	sess.invalidate();  
	        return "redirect:./logout";
	    }
	    List<BirthCertificateApplicationMaster> birthList = bCAService.getAll();
	    System.out.println(birthList);
	    for (BirthCertificateApplicationMaster birth : birthList) {
			System.out.println(birth.getBId());
		}
	    model.addAttribute("birthList", birthList);
	    model.addAttribute("userName", userName);
	    model.addAttribute("user", user);
	    
	    return "adminDashboard";
    }
	
    @GetMapping("/downloadFile")
    public String downloadBC(@RequestParam("birth") String birth, @RequestParam("fileName") String file,
    		RedirectAttributes rd, Model model) throws ParseException {
    	Pattern pattern = Pattern.compile("bId=[^,]*");
        Matcher matcher = pattern.matcher(birth);
        if (matcher.find()) {
            System.out.println(matcher.group()); 
        }
        String s = matcher.group();
        String  s1 = s.substring(4);
        int bId =  Integer.parseInt(s1);
        BirthCertificateApplicationMaster bc = bCAService.getData(bId);
        String s2 = bc.getDob();

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MMM/yyyy");

        Date date = inputFormat.parse(s2);

        String formattedDob = outputFormat.format(date);
        model.addAttribute("bc", bc);
        model.addAttribute("dob", formattedDob);
        System.out.println(bc);
    	return "birthCertificatePdf";
    }
      
    @GetMapping("/approve")
    public String approveBC(@RequestParam("birth") String birth, RedirectAttributes rd, Model model, HttpSession sess) {
    	System.out.println(birth);  
    	Pattern pattern = Pattern.compile("bId=[^,]*");
        Matcher matcher = pattern.matcher(birth);
        if (matcher.find()) {
            System.out.println(matcher.group()); 
        }
        String s = matcher.group();
        String  s1 = s.substring(4);
       System.out.println(s1);
        int bId =  Integer.parseInt(s1);
        System.out.println(bId);
        String authority = (String) sess.getAttribute("userName");
        int approveBirth = bCAService.approveBirth(bId, authority);
    	if (approveBirth < 0) {
    		String msg = "Error! Something went wrong!";
    		model.addAttribute("msgN", msg);
    	    return "redirect:./adminDashboard";
    	}
    	String msg = "Approved successfully!";
    	model.addAttribute("msgP",msg);
    	 return "redirect:./adminDashboard";
    }
    
    @GetMapping("/reject")
    public String rejectBC(@RequestParam("birth") String birth, RedirectAttributes rd, Model model, HttpSession sess) {
    	Pattern pattern = Pattern.compile("bId=[^,]*");
        Matcher matcher = pattern.matcher(birth);
        if (matcher.find()) {
            System.out.println(matcher.group()); 
        }
        String s = matcher.group();
        String  s1 = s.substring(4);
       System.out.println(s1);
        int bId =  Integer.parseInt(s1);
        System.out.println(bId);
        int rejectBirth = bCAService.rejectBirth(bId);
        if (rejectBirth < 0) {
    		String msg = "Error! Something went wrong!";
    		model.addAttribute("msgN", msg);
    	    return "redirect:./adminDashboard";
    	}
    	String msg = "Rejected successfully!";
    	model.addAttribute("msgP",msg);
    	 return "redirect:./adminDashboard";
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
		if (res.equals("Admin")) {
			sess.setAttribute("userName", userName);
			sess.setAttribute("user", res);
			return "redirect:./adminDashboard";
		}
		sess.setAttribute("userName", userName);
		sess.setAttribute("user", res);
		return "redirect:./dashboard";
	}

	@PostMapping("/applyBC")
	public String applyBC(@ModelAttribute BirthCertificateApplicationMaster birth, RedirectAttributes rd, HttpSession sess) {
	   birth.setAddressProof(birth.getFile().getOriginalFilename());
	   UserRegistration reg = registerService.findUser((String) sess.getAttribute("userName"));
	   birth.setUserRegd(reg);
	   LocalDate today = LocalDate.now();
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       String formattedDate = today.format(formatter);
	   birth.setAppliedOn(formattedDate);
	   birth.setStatus("PENDING");
	   BirthCertificateApplicationMaster bca = bCAService.saveBC(birth);
	   System.out.println(bca);
		return "redirect:./view";
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
