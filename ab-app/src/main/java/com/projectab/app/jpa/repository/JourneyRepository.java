/**
 * 
 */
package com.projectab.app.jpa.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.projectab.app.model.Journey;

/**
 * @author Bhupesh
 *
 */
public interface JourneyRepository extends CrudRepository<Journey, Long>{

	List<Journey> findBySourceAndFromDateGreaterThanEqualAndDestinationAndToDateLessThanEqual(String source, OffsetDateTime fromDate, String destination, OffsetDateTime toDate);
}
