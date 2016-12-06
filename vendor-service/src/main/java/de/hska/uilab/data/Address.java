package de.hska.uilab.data;

public class Address {
	private String street;
	private String number;
	private String postal;
	private String city;
	private String country;
	
	public Address(){}
	public Address(String street, String number, String postal, String city, String country){
		this.street = street;
		this.number = number;
		this.postal = postal;
		this.city = city;
		this.country = country;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
