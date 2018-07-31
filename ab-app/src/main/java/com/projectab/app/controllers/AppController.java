package com.projectab.app.controllers;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projectab.app.dto.UserDto;
import com.projectab.app.jpa.repository.JourneyRepository;
import com.projectab.app.jpa.repository.UserRepository;
import com.projectab.app.model.Journey;
import com.projectab.app.model.User;

/**
 * 
 * @author Bhupesh
 *
 */
@Controller
public class AppController {

	@Autowired
	JourneyRepository journeyRepository;

	@Autowired
	UserRepository userRepository;

	@GetMapping("/project-ab")
	public String welcome() {
		return "base";
	}

	@GetMapping("/project-ab/journey")
	public String showJourney(Model model) {
		model.addAttribute("journey", new Journey());
		return "createJourney";
	}

	@PostMapping("/project-ab/createJourney")
	public String createJourney(Journey journey) {
		System.out.println(journey);
		journeyRepository.save(journey);
		return "base";
	}

	@GetMapping("/project-ab/login")
	public String login(Model model) {
		model.addAttribute("user", new UserDto());
		return "login";
	}

	@PostMapping("/project-ab/login")
	public ModelAndView loginAuthenticate(UserDto userDto) {
		ModelAndView modelAndView = new ModelAndView("base", "user", userDto);
		Optional<User> user = userRepository.findByUsername(userDto.getUsername());
		if (user.isPresent() && user.get().isActive() && user.get().getPassword().equals(userDto.getPassword())) {
			modelAndView.setViewName("base");
		} else {
			modelAndView.setViewName("login");
			userDto.setStatus("Invalid User!");
		}
		return modelAndView;
	}

	@GetMapping("/project-ab/register")
	public String register(Model model) {
		model.addAttribute("user", new UserDto());
		return "register";
	}

	@PostMapping("/project-ab/register")
	public String registerUser(UserDto dto) {
		System.out.println("user :" + dto);
		User user = new User();
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setEmail(dto.getEmail());
		user.setActive(true);
		user.setPlatform("AB");
		user.setRegisteredDate(new Date().toString());

		userRepository.save(user);
		return "base";
	}

}
