package com.hospitalx.sala.emergencias.enums;

public enum MenuOption {
	
	REGISTER_PATIENT((byte) 1,"Regitrar paciente",true),
	SHOW_REGISTERED_PATIENT_LIST((byte) 2,"Mostrar lista de registros",true),
	SHOW_TREATED_PATIENT_LIST((byte) 3, "Mostrar lista de pacientes atendidos",true ),
	BEGIN_TREAT_PATIENTS( (byte) 4, "Enviar pacientes registrados a médicos", true ),
	STOP_TREAT_PATIENTS( (byte) 5, "Detener el envio de pacientes a médicos", true ),
	EXIT((byte) 0,"Salir", true);
	
	private byte id;
	private String description;
	private boolean status;
	
	private MenuOption(byte id, String description, boolean status) {
		this.id = id;
		this.description = description;
		this.status = status;
	}
	public byte getId() {
		return id;
	}
	public void setId(byte id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
