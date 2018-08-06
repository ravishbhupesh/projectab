/**
 * 
 */
package com.projectab.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Bhupesh
 *
 */
@Entity
@Table(name="AIRPORTS")
public class Airports {

	@Id
	@Column(name="CODE")
	private String code;
	@Column(name="NAME")
	private String name;
	@Column(name="CITYCODE")
	private String citycode;
	@Column(name="CITYNAME")
	private String cityname;
	@Column(name="COUNTRY")
	private String country;
	@Column(name="COUNTRYCODE")
	private String countrycode;
	@Column(name="TIMEZONE")
	private String timezone;
	@Column(name="LAT")
	private String lat;
	@Column(name="LON")
	private String lon;
	@Column(name="CITY")
	private boolean city;
	@Column(name="NUMAIRPORTS")
	private int numairports;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Airport : " + getCode() + "\n");
		sb.append("Airport Name : " + getCode() + "\n");
		sb.append("City : " + getCityname() + "\n");
		sb.append("Country : " + getCountry());
		return sb.toString();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public boolean isCity() {
		return city;
	}

	public void setCity(boolean city) {
		this.city = city;
	}

	public int getNumairports() {
		return numairports;
	}

	public void setNumairports(int numairports) {
		this.numairports = numairports;
	}
}
