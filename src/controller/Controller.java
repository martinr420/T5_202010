package controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.data_structures.Nodo;
import model.data_structures.noExisteObjetoException;
import model.logic.Modelo;
import model.logic.Multa;
import view.View;

public class Controller {


	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------

	/**
	 * A model.
	 */
	private Modelo modelo;

	/**
	 * A view.
	 */
	private View view;

	// -------------------------------------------------------------
	// Constructor
	// -------------------------------------------------------------

	/**
	 * Creates the project view and the project model
	 */
	public Controller() {
		modelo = new Modelo();
		view = new View();
	}

	// -------------------------------------------------------------
	// Methods
	// -------------------------------------------------------------

	/**
	 * Prints the user options and updates the view using the model.
	 *
	 * @throws InputMismatchException If the user inputs an incorrect number sequence.
	 * @throws noExisteObjetoException 
	 */
	public void run() throws InputMismatchException, noExisteObjetoException {
		try {
			Scanner reader = new Scanner(System.in);
			boolean end = false;

			while (!end) {
				view.displayMenu();
				int option = reader.nextInt();
				switch (option) {

				case 0:
					
					view.displayOp0Menu(modelo.reque1());
					break;

				case 1:
					// Display option 1
					reader.nextLine();
					System.out.println("Ingrese una fecha");
				    String fecha = reader.nextLine();
				    System.out.println("Ingrese una clase de vehiculo");
				    String clasVeh = reader.nextLine();
				    System.out.println("Ingrese una infraccion");
				    String infraccion = reader.nextLine();
				    modelo.BuscarComparendosLP(fecha, clasVeh, infraccion);
					break;

				case 2:
					
					reader.nextLine();
					System.out.println("Ingrese una fecha");
				    String fecha2 = reader.nextLine();
				    System.out.println("Ingrese una clase de vehiculo");
				    String clasVeh2 = reader.nextLine();
				    System.out.println("Ingrese una infraccion");
				    String infraccion2 = reader.nextLine();
				    modelo.BuscarComparendosSC(fecha2, clasVeh2, infraccion2);
				
				
					break;
			
					// Invalid option
				default:
					view.badOption();
					end = true;
					break;
				}
			}
		} 
		catch (InputMismatchException e)
		{
			run();
		}
	}

}
