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
<<<<<<< HEAD
					
=======
					System.out.println("digite el tamano");
					int tamanoMuestra = reader.nextInt();
					modelo = new Modelo(tamanoMuestra);
>>>>>>> 6cdede3868dbd48c36232a6c9934c01b8eccb726
					view.displayOp0Menu(modelo.reque1());
					break;

				case 1:
					// Display option 1
<<<<<<< HEAD
					reader.nextLine();
					System.out.println("Ingrese una fecha");
				    String fecha = reader.nextLine();
				    System.out.println("Ingrese una clase de vehiculo");
				    String clasVeh = reader.nextLine();
				    System.out.println("Ingrese una infraccion");
				    String infraccion = reader.nextLine();
				    modelo.BuscarComparendosLP(fecha, clasVeh, infraccion);
=======
					view.displayOp2Menu();
					int n = reader.nextInt();
					view.displayOp18Menu();
					String vehiculo = reader.next();
					
					
					
>>>>>>> 6cdede3868dbd48c36232a6c9934c01b8eccb726
					break;

				case 2:
					
<<<<<<< HEAD
					reader.nextLine();
					System.out.println("Ingrese una fecha");
				    String fecha2 = reader.nextLine();
				    System.out.println("Ingrese una clase de vehiculo");
				    String clasVeh2 = reader.nextLine();
				    System.out.println("Ingrese una infraccion");
				    String infraccion2 = reader.nextLine();
				    modelo.BuscarComparendosSC(fecha2, clasVeh2, infraccion2);
				
				
=======
					view.displayOp2Menu();
					int n2 = reader.nextInt();
					view.displayOp18Menu();
					String vehiculo2 = reader.next();
					
>>>>>>> 6cdede3868dbd48c36232a6c9934c01b8eccb726
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
