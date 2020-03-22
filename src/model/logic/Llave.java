package model.logic;

import java.util.Date;

public class Llave 
{
private final static int R = 31;
	
	
	private Date fecha;
	private String vehiculo;
	private String infraccion;
	
	public Llave(Date pFecha, String pVehiculo, String pInfraccion)
	{
		fecha = pFecha;
		vehiculo = pVehiculo;
		infraccion = pInfraccion;
	}
	
	public int hashCode()
	{	
		return (int) R * fecha.hashCode() * vehiculo.hashCode() * infraccion.hashCode();
	}


}
