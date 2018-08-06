/**
 * 
 */
package com.projectab.app.controllers;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projectab.app.dto.JourneyDto;
import com.projectab.app.jpa.repository.JourneyRepository;
import com.projectab.app.model.Journey;

/**
 * @author Bhupesh
 *
 */
public class JourneyController {
	
	@Autowired
	JourneyRepository journeyRepository;

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
		List<Journey> journeys = journeyRepository
				.findBySourceAndFromDateGreaterThanEqualAndDestinationAndToDateLessThanEqual(journeyDto.getSource(),
						OffsetDateTime.parse(journeyDto.getFromDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME),
						journeyDto.getDestination(),
						OffsetDateTime.parse(journeyDto.getToDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME));
		return new ModelAndView("searchJourney", "journeys", convertJourneysToDto(journeys));
	}

	private List<JourneyDto> convertJourneysToDto(List<Journey> journeys) {
		List<JourneyDto> rtrnJourneys = new ArrayList<>();
		if (journeys != null && journeys.size() > 0) {
			for (Journey journey : journeys) {
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
		System.out.println("journey:" + journey);
		Journey j = new Journey();
		j.setTravellerName(journey.getTravellerName());
		j.setSource(journey.getSource());
		j.setFromDate(OffsetDateTime.parse(journey.getFromDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME));
		j.setDestination(journey.getDestination());
		j.setToDate(OffsetDateTime.parse(journey.getToDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME));
		journeyRepository.save(j);
		return "base";
	}
}
