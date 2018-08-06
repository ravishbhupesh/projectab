/**
 * 
 */
package com.projectab.app.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Bhupesh
 *
 */
@Entity
@Table(name="JOURNEY")
public class Journey {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="JOURNEYID")
	private int journeyKey;
	@Column(name="TRAVELLER")
	private String travellerName;
	@Column(name="SOURCE")
	private String source;
	@Column(name="DESTINATION")
	private String destination;
	@Column(name="STARTDATE")
	private OffsetDateTime fromDate;
	@Column(name="ENDDATE")
	private OffsetDateTime toDate;
	@Column(name="OWNEDBY")
	private long userId;

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
	public OffsetDateTime getFromDate() {
		return fromDate;
	}
	public void setFromDate(OffsetDateTime fromDate) {
		this.fromDate = fromDate;
	}
	public OffsetDateTime getToDate() {
		return toDate;
	}
	public void setToDate(OffsetDateTime toDate) {
		this.toDate = toDate;
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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
