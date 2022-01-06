package com.hospitalx.sala.emergencias.enums;

public enum SeveritiesPatientEnum {
	
	MINOR("Gravedad menor",1),
	MODERATE("Gravedad moderada",2),
	SEVERATE("Gravedad severa",3),
	VERY_SEVERATE("Gravedad muy servera",4);
	
	private String description;
	private int scale;
	
	private SeveritiesPatientEnum(String description, int i) {
		this.description = description;
		this.scale = i;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
}