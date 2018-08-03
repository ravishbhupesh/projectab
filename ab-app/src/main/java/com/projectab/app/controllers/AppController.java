package com.projectab.app.controllers;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projectab.app.dto.JourneyDto;
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
	
	private final DateTimeFormatter DATETIMEFORMATTER = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm:ss a");

	@Autowired
	JourneyRepository journeyRepository;

	@Autowired
	UserRepository userRepository;

	@GetMapping("/project-ab")
	public String welcome() {
		System.out.println("AppController::welcome");
		return "base";
	}

	@GetMapping("/project-ab/journey")
	public String showJourney(Model model) {
		System.out.println("AppController::showJourney");
		model.addAttribute("journey", new JourneyDto());
		return "createJourney";
	}
	
	@GetMapping("/project-ab/searchJourney")
	public String searchJourney(Model model) {
		System.out.println("AppController::showJourneyGET");
		model.addAttribute("journeyDto", new JourneyDto());
		return "searchJourney";
	}
	
	@PostMapping("/project-ab/searchJourney")
	public ModelAndView searchJourneys(JourneyDto journeyDto) {
		System.out.println("AppController::showJourneyPOST");
		List<Journey> journeys = journeyRepository.findBySourceAndFromDateGreaterThanEqualAndDestinationAndToDateLessThanEqual(
				journeyDto.getSource(),
				OffsetDateTime.parse(journeyDto.getFromDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME),
				journeyDto.getDestination(),
				OffsetDateTime.parse(journeyDto.getToDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME));
		return new ModelAndView("searchJourney", "journeys", convertJourneysToDto(journeys));
	}

	private List<JourneyDto> convertJourneysToDto(List<Journey> journeys) {
		List<JourneyDto> rtrnJourneys = new ArrayList<>();
		if(journeys != null && journeys.size() > 0) {
			for(Journey journey : journeys) {
				JourneyDto dto = new JourneyDto();
				dto.setTravellerName(journey.getTravellerName());
				dto.setSource(journey.getSource());
				dto.setFromDate(journey.getFromDate().toString());
				dto.setDestination(journey.getDestination());
				dto.setToDate(journey.getToDate().toString());
				
				rtrnJourneys.add(dto);
			}
		}
		return rtrnJourneys;
	}

	@PostMapping("/project-ab/createJourney")
	public String createJourney(JourneyDto journey) {
		System.out.println("AppController::createJourney");
		System.out.println("journey:"+journey);
		Journey j = new Journey();
		j.setTravellerName(journey.getTravellerName());
		j.setSource(journey.getSource());
		j.setFromDate(OffsetDateTime.parse(journey.getFromDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME));
		j.setDestination(journey.getDestination());
		j.setToDate(OffsetDateTime.parse(journey.getToDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME));
		journeyRepository.save(j);
		return "base";
	}
	
	/*@GetMapping("/project-ab/logout")
	public String logout() {
		System.out.println("AppController::logout");
		return "base";
	}*/

	@GetMapping("/project-ab/login")
	public String login(Model model) {
		System.out.println("AppController::login");
		model.addAttribute("user", new UserDto());
		return "login";
	}

	/*
	 * This method is not required after Spring security configuration
	 * 
	@PostMapping("/project-ab/loginAuthenticate")
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

	@GetMapping("/project-ab/register")
	public String register(Model model) {
		System.out.println("AppController::register");
		model.addAttribute("user", new UserDto());
		return "register";
	}

	@PostMapping("/project-ab/register")
	public String registerUser(@Valid @ModelAttribute("user") UserDto dto, BindingResult bindingResult) {
		System.out.println("AppController::registerUser");
		System.out.println("user :" + dto);
		if(bindingResult.hasErrors()) {
			return "register";
		}
		if(!dto.getPassword().equals(dto.getMatchingPassword())) {
			bindingResult.rejectValue("matchingPassword", "error.password.mismatch");
			return "register";
		}
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
