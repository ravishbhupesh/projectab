/**
 * 
 */
package com.projectab.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Bhupesh
 *
 */
public class JourneyDto {
	
	private int journeyKey;
	private String travellerName;
	@NotNull
	@Size(min=1, message="Please enter a valid value!")
	private String source;
	@NotNull
	@Size(min=1, message="Please enter a valid value!")
	private String destination;
	@NotNull
	@Size(min=1, message="Please enter a valid value!")
	private String fromDate;
	@NotNull
	@Size(min=1, message="Please enter a valid value!")
	private String toDate;
	private String contact;
	

	public int getJourneyKey() {
		return journeyKey;
	}
	public void setJourneyKey(int journeyKey) {
		this.journeyKey = journeyKey;
	}
	public String getTravellerName() {
		return travellerName;
	}
	public void setTravellerName(String travellerName) {
		this.travellerName = travellerName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Traveller Name : " + getTravellerName() + "\n");
		sb.append("Source : " + getSource() + "\n");
		sb.append("Destination : " + getDestination() + "\n");
		sb.append("Start Date : " + getFromDate() + "\n");
		sb.append("To Date : " + getToDate() + "\n");
		return sb.toString();
	}

}
