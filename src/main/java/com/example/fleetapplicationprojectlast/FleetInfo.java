package com.example.fleetapplicationprojectlast;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FleetInfo {
	@Id
	private String fleet_number;
	private String fleet_name;
	private String driver_assigned;
	private String status;
	private String nooftrips;
	public String getFleet_name() {
		return fleet_name;
	}
	public void setFleet_name(String fleet_name) {
		this.fleet_name = fleet_name;
	}
	public String getFleet_number() {
		return fleet_number;
	}
	public void setFleet_number(String fleet_number) {
		this.fleet_number = fleet_number;
	}
	public String getDriver_assigned() {
		return driver_assigned;
	}
	public void setDriver_assigned(String driver_assigned) {
		this.driver_assigned = driver_assigned;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNooftrips() {
		return nooftrips;
	}
	public void setNooftrips(String nooftrips) {
		this.nooftrips = nooftrips;
	}
	

}
