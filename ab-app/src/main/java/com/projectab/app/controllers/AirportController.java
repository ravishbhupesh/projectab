/**
 * 
 */
package com.projectab.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projectab.app.dto.AirportDto;
import com.projectab.app.jpa.repository.AirportRepository;
import com.projectab.app.model.Airports;

/**
 * @author Bhupesh
 *
 */
@Controller
public class AirportController {
	
	@Autowired
	private AirportRepository airportRepository; 

	@RequestMapping(value="/project-ab/airports", method=RequestMethod.GET)
	public @ResponseBody List<AirportDto> getAirports(@RequestParam String name) {
		System.out.println("AirportController::getAirports");
		List<Airports> matchingAirports = airportRepository.findByCitynameIgnoreCaseStartingWith(name);
		return convertToAirportDto(matchingAirports);
	}

	private List<AirportDto> convertToAirportDto(List<Airports> matchingAirports) {
		System.out.println("AirportController::convertToAirportDto");
		System.out.println("Airports found : " + matchingAirports.size());
		List<AirportDto> airports = new ArrayList<>();
		for(Airports a : matchingAirports) {
			AirportDto dto = new AirportDto();
			dto.setCode(a.getCode());
			dto.setCityname(a.getCityname());
			dto.setAirportName(a.getName());
			airports.add(dto);
		}
		return airports;
	}
}
