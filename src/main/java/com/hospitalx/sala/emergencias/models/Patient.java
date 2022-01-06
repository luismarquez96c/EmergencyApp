package com.hospitalx.sala.emergencias.models;

import com.hospitalx.sala.emergencias.enums.SeveritiesPatientEnum;

public class Patient extends Person implements Comparable<Patient> {
	
	private int pressure;
	private int temperature;
	private SeveritiesPatientEnum severity;

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Patient(String name) {
		super(name);
	}
	public Patient(String name, int pressure, int temperature, SeveritiesPatientEnum severity) {
		super(name);
		this.pressure = pressure;
		this.temperature = temperature;
		this.severity = severity;
	}
	public int getPressure() {
		return pressure;
	}
	public void setPressure(int pressure) {
		this.pressure = pressure;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public SeveritiesPatientEnum getSeverity() {
		return severity;
	}
	public void setSeverity(SeveritiesPatientEnum severity) {
		this.severity = severity;
	}

	
	
	@Override
	public String toString() {
		return "Patient [pressure=" + pressure + ", temperature=" + temperature + ", severity=" + severity + ", name="
				+ name + "]";
	}
	@Override
	public int compareTo(Patient o) {
		int severityToCompare = o.getSeverity().getScale();
		int severityCompared = this.getSeverity().getScale();
		
		return severityToCompare - severityCompared;
	}
	
}
