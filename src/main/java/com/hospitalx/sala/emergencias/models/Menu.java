package com.hospitalx.sala.emergencias.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.hospitalx.sala.emergencias.enums.MenuOption;
import com.hospitalx.sala.emergencias.exceptions.InvalidOptionException;

public class Menu {
	private Scanner scanner = new Scanner(System.in);
	private static List<MenuOption> menuOptions = new ArrayList<MenuOption>();
	
	static {
		menuOptions.addAll(Arrays.asList(MenuOption.values()));
		menuOptions = menuOptions.stream()
				.filter(option -> option.isStatus())
				.collect(Collectors.toList());
	}
	
	public MenuOption requestOption() throws Exception {
		while( true ) {
			try {
				System.out.println("*Por favor ingrese una opción valida: ");
				String optionAsString = scanner.nextLine().trim();
				System.out.println("**Opción ingresada: " + optionAsString);
				byte option = Byte.parseByte(optionAsString);
				List<MenuOption> selectedOption = menuOptions.stream()
						.filter( optionElement -> optionElement.getId() == (byte) option )
						.collect(Collectors.toList());
				
				if( selectedOption == null || selectedOption.isEmpty() ) {
					throw new InvalidOptionException();
				}

				return selectedOption.get(0);
			}catch (NumberFormatException e) {
				System.out.println("La opción debe de ser numerica. ¡Vuelva a intentarlo!");
			}catch (InvalidOptionException e) {
				System.out.println("La opción indicada no es valida");
			}catch (Exception e) {
				System.out.println("Hubo un problema en el ingreso de la opción. ¡Vuelva a intantarlo!");
			}
		}
	}

	public void showMenu() throws Exception {
		System.out.println("\n**** MENU ****");
		menuOptions.forEach( option -> {
			System.out.println(option.getId() + " - " + option.getDescription());
		});
	}
	
	public void showWelcomeMessage() throws IOException {
		
		StringBuilder message = new StringBuilder();
		
		message.append("*************************************************\n" );
		message.append("Bienvenido/a, usted será el enfermero/a encargado\n");
		message.append("*************************************************" );
		
		System.out.println(message.toString());
	}
	
	public void showGoodbyeMessage(Nurse nurse) {
		System.out.println("\n");
		System.out.println("**********************");
		System.out.println("Fue un gusto atenderte " + nurse.getName());
		System.out.println("**********************");
		System.exit(0);
	}
	
	public Nurse requestNurseData() {
		System.out.print("Por favor, introduzca su nombre: ");
		String nurseName = scanner.nextLine();
		
		while(nurseName == null || nurseName.isEmpty()) {
			System.out.println("¡El nombre del encargado no puede estar vacio!" );
			System.out.print("Por favor, introduzca su nombre: ");
			nurseName = scanner.nextLine();
		}
		
		System.out.println("Gracias! " + nurseName);
		
		return new Nurse(nurseName);
	}
}
