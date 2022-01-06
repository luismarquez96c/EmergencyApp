package com.hospitalx.sala.emergencias.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.hospitalx.sala.emergencias.app.EmergencyRoomApp;
import com.hospitalx.sala.emergencias.enums.SeveritiesPatientEnum;
import com.hospitalx.sala.emergencias.enums.MenuOption;
import com.hospitalx.sala.emergencias.exceptions.InvalidOptionException;

public class Nurse extends Person {

	private Scanner scanner = new Scanner(System.in);
	private static List<SeveritiesPatientEnum> severitiesPatient = new ArrayList();
	private Menu menu;
	private static List<Doctor> doctorsList = new ArrayList<Doctor>();

	static {
		Doctor[] doctors =  {
				new Doctor("Luis"),
//				new Doctor("Guillermo"),
			};
		
		doctorsList.addAll(Arrays.asList(doctors));
		
		severitiesPatient.addAll( Arrays.asList(SeveritiesPatientEnum.values()) );
	}
	
	public Nurse() {}

	public Nurse(String name) {
		super(name);
	}

	public Nurse(String name, Menu menu) {
		super(name);
		this.menu = menu;
	}
	
	public void invokeOption(MenuOption selectedOption) throws Exception {
		
		switch (selectedOption){
		case REGISTER_PATIENT:
			Patient newPatient = requestPatientData();
			Board.getInstance().pushRegisteredPatient(newPatient);
			System.out.println("Paciente registrado exisosamente");
			break;
		case SHOW_REGISTERED_PATIENT_LIST:
			Board.showRegisteredPatients();
			break;
		case SHOW_TREATED_PATIENT_LIST: 
			Board.showHistoryTreatedPatient();
			break;
		case BEGIN_TREAT_PATIENTS:
			sendPatientsToDoctor();
			break;
		case STOP_TREAT_PATIENTS:
			stopSendingPatientsToDoctor();
			break;
		case EXIT:
			menu.showGoodbyeMessage( this );
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + selectedOption);
		}
		
	}
	
	public SeveritiesPatientEnum requestPatientSeverity() throws Exception {
		
		System.out.println("\n*Introduzca la gravedad del paciente: ");
		
		for( SeveritiesPatientEnum severityEnum : SeveritiesPatientEnum.values() ) {
			System.out.println("\n\t" + severityEnum.getScale() + " - " +  severityEnum.getDescription());
		}
		
		while(true) {
			System.out.println("Introduzca un opción valida: ");
			try {
				int severityScale = Integer.parseInt(scanner.nextLine());
				
				List<SeveritiesPatientEnum> selectedSeverity = severitiesPatient.stream()
					.filter( severity -> severity.getScale() == severityScale )
					.collect(Collectors.toList());
				
				if( selectedSeverity == null || selectedSeverity.isEmpty() ) {
					throw new InvalidOptionException();
				}
				
				return selectedSeverity.get(0);
				
			}catch (NumberFormatException e) {
				System.out.println("La opción debe de ser numerica. ¡Vuelva a intentarlo!");
			}catch (InvalidOptionException e) {
				System.out.println("El numero de gravedad indicado no es valido");
			}catch (Exception e) {
				System.out.println("Hubo un problema en la obtencion de la gravedad del paciente. ¡Vuelva a intantarlo!");
			}
		}
	}
	
	public Patient requestPatientData() throws Exception {
		
		while(true) {
			
			try {
				System.out.println("-------------------------------------");
				System.out.println("* Introduzca el nombre del paciente: ");
				String name = scanner.nextLine();
				
				System.out.println("\n*Introduzca la presión del paciente: ");
				int pressure = Integer.parseInt(scanner.nextLine());
				
				System.out.println("\nIntroduzca la temperatura del paciente: ");
				int temperature = Integer.parseInt(scanner.nextLine());
				
				SeveritiesPatientEnum severity = requestPatientSeverity();
				
				Patient patient = new Patient(name, pressure, temperature, severity);
				System.out.println("-------------------------------------");
				return patient;
				
			}catch (NumberFormatException e) {
				System.out.println("El dato debe de ser numerico");
			}catch (Exception e) {
				System.out.println("Hubo un error en el registro del paciente. ¡Vuelva a intentarlo!");
			}
		}
	}
	
	public void sendPatientsToDoctor() {
		doctorsList.forEach( doctor -> ( new Thread(doctor) ).start() );
	}
	
	public void stopSendingPatientsToDoctor() {
		try {
			doctorsList.forEach( doctor -> {
				Thread doctorThreat = ( new Thread(doctor) );
				
				if(doctorThreat.isAlive() || !doctorThreat.isInterrupted() ) {
					doctorThreat.interrupt();
					System.out.println("El doctor " + doctor.getName() + " dejará de atender");
				}
				
			});
		}catch (Exception e) {
			e.printStackTrace();
		}
			
	}
}
