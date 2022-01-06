package com.hospitalx.sala.emergencias.models;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Board {
	
	private static final Queue<Patient> REGISTERED_PATIENTS = new PriorityQueue();
	private static final Queue<String> TREATED_PATIENS = new LinkedList();
	
	private static Board instance = null;
	
	private Board() {}
	
	public static Board getInstance() {
		if( instance == null ) {
			instance = new Board();
		}
		return instance;
	}
	
	public synchronized void pushRegisteredPatient(Patient patient) {
		REGISTERED_PATIENTS.add(patient);
		notifyAll();
	}
	
	synchronized Patient getRegisteredPatient(Doctor doctor) {
		
		while( REGISTERED_PATIENTS.isEmpty() ) {
			try {
				System.out.println("Doctor " + doctor.getName() + " A la espera de pacientes!!");
				wait();
			}catch (Exception e) {
			}
		}
		notifyAll();
		return REGISTERED_PATIENTS.poll();
	}
	
	public synchronized void pushTreatedPatient(Patient patient) {
		TREATED_PATIENS.add(patient.toString());
	}
	
	public static void showRegisteredPatients() {
		
		if(REGISTERED_PATIENTS.isEmpty()) {
			System.out.println("¡Lista vacía!");
			return;
		}
		
		REGISTERED_PATIENTS.
			forEach( patient -> System.out.println(patient));
		
	}
	
	public static void showHistoryTreatedPatient() {
		if(TREATED_PATIENS.isEmpty()) {
			System.out.println("¡Lista vacía!");
			return;
		}
		TREATED_PATIENS.
			forEach(patient -> System.out.println(patient));
	}
}
