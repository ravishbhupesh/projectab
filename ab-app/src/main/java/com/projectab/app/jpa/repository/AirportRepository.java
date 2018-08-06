/**
 * 
 */
package com.projectab.app.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.projectab.app.model.Airports;

/**
 * @author Bhupesh
 *
 */
public interface AirportRepository extends CrudRepository<Airports, Long> {

	List<Airports> findByCitynameIgnoreCaseStartingWith(String cityname);
	
	Optional<Airports> findByName(String name);
	
	Optional<Airports> findByCode(String code);
}
