package com.projectab.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.projectab.app.jpa.repository.JourneyRepository;
import com.projectab.app.model.Journey;

/**
 * 
 * @author Bhupesh
 *
 */
@Controller
public class AppController {
	
	@Autowired
	JourneyRepository journeyRepository;

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
}
