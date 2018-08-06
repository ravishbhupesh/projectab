package com.projectab.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.projectab.app.dto.UserDto;

/**
 * 
 * @author Bhupesh
 *
 */
@Controller
public class AppController {

	@GetMapping("/project-ab")
	public String welcome() {
		System.out.println("AppController::welcome");
		return "base";
	}

	/*
	 * @GetMapping("/project-ab/logout") public String logout() {
	 * System.out.println("AppController::logout"); return "base"; }
	 */

	@GetMapping("/project-ab/login")
	public String login(Model model) {
		System.out.println("AppController::login");
		model.addAttribute("user", new UserDto());
		return "login";
	}

	/*@PostMapping("/project-ab/loginAuthenticate")
	public ModelAndView loginAuthenticate(UserDto userDto) {
		System.out.println("AppController::loginAuthenticate");
		ModelAndView modelAndView = new ModelAndView("base", "user", userDto);
		Optional<User> user = userRepository.findByUsername(userDto.getUsername());
		if (user.isPresent() && user.get().isActive() && user.get().getPassword().equals(userDto.getPassword())) {
			modelAndView.setViewName("base");
			System.out.println("Valid User!");
		} else {
			modelAndView.setViewName("login");
			userDto.setStatus("Invalid User!");
			System.out.println("Invalid User!");
		}
		return modelAndView;
	}*/

	

}