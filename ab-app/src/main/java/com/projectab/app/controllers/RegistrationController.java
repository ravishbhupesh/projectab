/**
 * 
 */
package com.projectab.app.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.projectab.app.dto.UserDto;
import com.projectab.app.jpa.repository.UserRepository;
import com.projectab.app.model.User;
import com.projectab.app.recaptcha.RecaptchaService;

/**
 * @author Bhupesh
 *
 */
@Controller
public class RegistrationController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private RecaptchaService captchaService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/project-ab/register")
	public String register(Model model) {
		System.out.println("AppController::register");
		model.addAttribute("user", new UserDto());
		return "register";
	}

	@PostMapping("/project-ab/register")
	public ModelAndView registerUser(@Valid @ModelAttribute("user") UserDto dto, BindingResult bindingResult,
			@RequestParam(name = "g-recaptcha-response") String recaptchaResponse, HttpServletRequest request) {
		System.out.println("AppController::registerUser");
		System.out.println("user :" + dto);
		ModelAndView modelAndView = new ModelAndView("register");
		if (bindingResult.hasErrors()) {
			// return "register";
			return modelAndView;
		}
		if (!dto.getPassword().equals(dto.getMatchingPassword())) {
			bindingResult.rejectValue("matchingPassword", "error.password.mismatch");
			// return "register";
			return modelAndView;
		}

		String ip = request.getRemoteAddr();
		String captchaVerifyMessage = captchaService.verifyRecaptcha(ip, recaptchaResponse);
		if (StringUtils.isNotEmpty(captchaVerifyMessage)) {
			System.out.println("Recaptcha Error! " + captchaVerifyMessage);
			// Map<String, Object> response = new HashMap<>();
			// response.put("message", "Please select the reCAPTCHA!");
			// return "register";
			modelAndView.addObject("message", "Please select the reCAPTCHA!");
			return modelAndView;
		}

		User user = new User();
		user.setUsername(dto.getUsername());
		try {
			//user.setPassword(PasswordUtil.getInstance().encyrpt(dto.getPassword()));
			user.setPassword(passwordEncoder.encode(dto.getPassword()));
		} catch (Exception e) {
			user.setPassword(dto.getPassword());
		}
		user.setEmail(dto.getEmail());
		user.setActive(true);
		user.setPlatform("AB");
		user.setRegisteredDate(new Date().toString());
		user.setFirstname(dto.getFirstname());
		user.setLastname(dto.getLastname());

		userRepository.save(user);
		// return "base";
		modelAndView.setViewName("base");
		return modelAndView;
	}
}
