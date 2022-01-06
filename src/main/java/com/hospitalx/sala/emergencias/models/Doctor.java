package com.hospitalx.sala.emergencias.models;

public class Doctor extends Person implements Runnable {

	private boolean busy = false;
	
	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}

	public Doctor(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "Doctor [ocupado=" + busy + "]";
	}
	
	public void treatPatient( Patient patient ) throws InterruptedException {
		System.out.println("Atendiendo Paciente " + patient + " ...");
		Thread.sleep(900);
		this.setBusy(true);
		dismissPatient(patient);
	}
	
	private void dismissPatient( Patient paciente ) {
		System.out.println("\t*Despachando Paciente " + paciente + " ...");
		this.setBusy(false);
	}

	@Override
	public void run() {
		
		while(true) {
			try {
				Patient patient;
				try {
					patient = Board.getInstance().getRegisteredPatient(this);
					treatPatient(patient);
					Board.getInstance().pushTreatedPatient(patient);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
