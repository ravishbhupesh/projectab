/**
 * 
 */
package com.projectab.app.controllers;

import java.security.Principal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
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
import com.projectab.app.jpa.repository.AirportRepository;
import com.projectab.app.jpa.repository.JourneyRepository;
import com.projectab.app.jpa.repository.UserRepository;
import com.projectab.app.model.Airports;
import com.projectab.app.model.Journey;
import com.projectab.app.model.User;

/**
 * @author Bhupesh
 *
 */
@Controller
public class JourneyController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JourneyRepository journeyRepository;
	
	@Autowired
	AirportRepository airportRepository;

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
	public ModelAndView searchJourneys(@Valid @ModelAttribute("journeyDto") JourneyDto journeyDto, BindingResult bindingResult) {
		System.out.println("AppController::showJourneyPOST");
		if(bindingResult.hasErrors()) {
			return new ModelAndView("searchJourney");
		}
		List<Journey> journeys = journeyRepository
				.findBySourceAndFromDateGreaterThanEqualAndDestinationAndToDateLessThanEqual(getAirportCode(journeyDto.getSource()),
						OffsetDateTime.parse(journeyDto.getFromDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME),
						getAirportCode(journeyDto.getDestination()),
						OffsetDateTime.parse(journeyDto.getToDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME));
		return new ModelAndView("searchJourney", "journeys", convertJourneysToDto(journeys));
	}

	private List<JourneyDto> convertJourneysToDto(List<Journey> journeys) {
		List<JourneyDto> rtrnJourneys = new ArrayList<>();
		if (journeys != null && journeys.size() > 0) {
			for (Journey journey : journeys) {
				JourneyDto dto = new JourneyDto();
				dto.setTravellerName(journey.getTravellerName());
				dto.setSource(getAirportInformation(journey.getSource()));
				dto.setFromDate(journey.getFromDate().toString());
				dto.setDestination(getAirportInformation(journey.getDestination()));
				dto.setToDate(journey.getToDate().toString());
				Optional<User> user = userRepository.findById(journey.getUserId());
				if(user.isPresent())
					dto.setContact(user.get().getUsername());
				rtrnJourneys.add(dto);
			}
		}
		return rtrnJourneys;
	}

	@PostMapping("/project-ab/createJourney")
	public ModelAndView createJourney(@Valid @ModelAttribute("journey") JourneyDto journey, BindingResult bindingResult, HttpServletRequest request) {
		System.out.println("AppController::createJourney");
		System.out.println("journey:" + journey);
		if(bindingResult.hasErrors()) {
			return new ModelAndView("createJourney");
		}
		Journey j = new Journey();
		j.setTravellerName(journey.getTravellerName());
		//j.setSource(journey.getSource());
		j.setSource(getAirportCode(journey.getSource()));
		j.setFromDate(OffsetDateTime.parse(journey.getFromDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME));
		//j.setDestination(journey.getDestination());
		j.setDestination(getAirportCode(journey.getDestination()));
		j.setToDate(OffsetDateTime.parse(journey.getToDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME));
		Principal principal = request.getUserPrincipal();
		System.out.println("principal.getName()::"+principal.getName());
		Optional<User> user = userRepository.findByUsername(principal.getName());
		if(user.isPresent())
			j.setUserId(user.get().getUserId());
		journeyRepository.save(j);
		return new ModelAndView("base");
	}

	private String getAirportCode(String airportName) {
		Optional<Airports> airport = airportRepository.findByName(airportName);
		if(airport.isPresent())
			return airport.get().getCode();
		return airportName;
	}
	
	private String getAirportInformation(String airportCode) {
		Optional<Airports> airport = airportRepository.findByCode(airportCode);
		if(airport.isPresent())
			return airport.get().getCityname() + "-" + airport.get().getName() + "-" + airport.get().getCode();
		return airportCode;
	}
}
