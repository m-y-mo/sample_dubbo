package org.sample_web;

import org.service.api.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.data.User;

@Controller
public class UserController {
	
	@GetMapping("/details")
	public String getMyDetails(@RequestParam(name = "id") String id, @RequestParam(name = "pwd") String pwd, Model model) {
		authenticate(id, pwd);
		System.setProperty("java.net.preferIPv4Stack", "true");
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/user-consumer.xml"})) {
			context.start();
			UserService service = (UserService) context.getBean("userService");
			User user = service.getUser(id);
			model.addAttribute("id", user.getId());
			model.addAttribute("sensitiveData", user.getSensitiveData());
		}
		return "details";
	}

	@GetMapping("/search")
	public String searchUser(@RequestParam(name = "id") String id, @RequestParam(name = "field") String field, Model model) {
		System.setProperty("java.net.preferIPv4Stack", "true");
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/user-consumer.xml"})) {
			context.start();
			UserService service = (UserService) context.getBean("userService");
			User user = service.getUser(id);
			if (field.equals("sensitiveData")) {
				model.addAttribute("field", user.getSensitiveData());
			} else if (field.equals("nonSensitiveData")) {
				model.addAttribute("field", user.getNonSensitiveData());
			} else {
				throw new IllegalArgumentException("Page not found.");
			}
			return "search";
		}
	}
	
	private void authenticate(String id, String pwd) throws SecurityException {
		if (!pwd.equals("myPwd")) {
			throw new SecurityException("Unauthorize access.");
		}
	}
}
