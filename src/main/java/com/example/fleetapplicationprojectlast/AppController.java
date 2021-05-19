package com.example.fleetapplicationprojectlast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private DriverInfoRepository drivRepo;
	@Autowired
	private FleetInfoRepository fleetRepo;
	 @Autowired
	    private EmailService emailService;
	@GetMapping("/")
	public String index()
	{
		return "index";
	}
	@Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
//	@PostMapping("/register")
//    public String create(@ModelAttribute("admin") Admin admin) {
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//	    String encodedPassword = passwordEncoder.encode(admin.getPassword());
//	    admin.setPassword(encodedPassword);
//		adminRepo.save(admin);
//		return "successfulRegistration";
//			
//	}
	@GetMapping("/successfulRegistration")
	public String successfulRegistration() {
		return "successfulRegistration";
	}
	@GetMapping("/trial")
	public String trial()
	{
		return "trial";
	}
	@GetMapping("/table")
		public String table() {
		return "table";
	}
	@PostMapping("/driverinfo")
	public String create(@ModelAttribute("driver") DriverInfo driverinfo) {
		drivRepo.save(driverinfo);
		return "table";
	}
	@GetMapping("/tableforfleet")
	public String tableforfleet() {
		return "tableforfleet";
	}
	@PostMapping("/fleetinfo")
	public String create(@ModelAttribute("fleet") FleetInfo fleetinfo) {
		fleetRepo.save(fleetinfo);
		return "tableforfleet";
	}
	
	@GetMapping("/insurance")
	public String insurance() {
		return "insurance";
	}
	@GetMapping("/service")
	public String service() {
		return "service";
	}
	@GetMapping("/reports")
    public String reports() {
		return "reports";
	}
	public EmailService getEmailService() {
		return emailService;
	}
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
	public ConfirmationTokenRepository getConfirmationTokenRepository() {
		return confirmationTokenRepository;
	}
	public void setConfirmationTokenRepository(ConfirmationTokenRepository confirmationTokenRepository) {
		this.confirmationTokenRepository = confirmationTokenRepository;
	}
	 @RequestMapping(value="/register", method = RequestMethod.POST)
	    public ModelAndView registerUser(ModelAndView modelAndView, Admin admin)
	    {

	    	Admin existingUser = adminRepo.findByEmail(admin.getEmail());
	        if(existingUser != null)
	        {
	            modelAndView.addObject("message","This email already exists!");
	            modelAndView.setViewName("error");
	        }
	        else
	        {
	            adminRepo.save(admin);

	            ConfirmationToken confirmationToken = new ConfirmationToken(admin);

	            confirmationTokenRepository.save(confirmationToken);

	            SimpleMailMessage mailMessage = new SimpleMailMessage();
	            mailMessage.setTo(admin.getEmail());
	            mailMessage.setSubject("Complete Registration!");
	            mailMessage.setFrom("packagetracker6@gmail.com");
	            mailMessage.setText("To confirm your account, please click here : "
	            +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());

	            emailService.sendEmail(mailMessage);

	            modelAndView.addObject("emailId", admin.getEmail());

	            modelAndView.setViewName("successfulRegistration");
	        }

	        return modelAndView;
	    }
	    

	    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
	    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
	    {
	        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

	        if(token != null)
	        {
	        	Admin admin = adminRepo.findByEmail(token.getAdmin().getEmail());
	            admin.setEnabled(true);
	            adminRepo.save(admin);
	            modelAndView.setViewName("accountVerified");
	        }
	        else
	        {
	            modelAndView.addObject("message","The link is invalid or broken!");
	            modelAndView.setViewName("error");
	        }

	        return modelAndView;
	    }

}
