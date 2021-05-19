package com.example.fleetapplicationprojectlast;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DriverInfo {
	@Id
	private String login_id;
	private String driver_name;
	private String fleet_assigned;
	private String password;
	public String getDriver_name() {
		return driver_name;
	}
	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
	public String getFleet_assigned() {
		return fleet_assigned;
	}
	public void setFleet_assigned(String fleet_assigned) {
		this.fleet_assigned = fleet_assigned;
	}
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
