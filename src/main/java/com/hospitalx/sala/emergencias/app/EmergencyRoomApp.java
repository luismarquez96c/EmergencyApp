package com.hospitalx.sala.emergencias.app;

import java.util.Scanner;

import com.hospitalx.sala.emergencias.enums.MenuOption;
import com.hospitalx.sala.emergencias.models.Nurse;
import com.hospitalx.sala.emergencias.models.Menu;

public class EmergencyRoomApp {

	private final static Scanner scanner = new Scanner(System.in);
	private final static Menu menu = new Menu();

	public static void main(String [] args) {
		try {
			menu.showWelcomeMessage();
			Nurse nurse = menu.requestNurseData();
			
			MenuOption selectedOption = null;
			do {
				menu.showMenu();
				selectedOption = menu.requestOption();
				nurse.invokeOption(selectedOption);
				
			}while(selectedOption != MenuOption.EXIT);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
