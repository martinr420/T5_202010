package model.logic;




import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.data_structures.HashLP;
import model.data_structures.HashSC;
import model.data_structures.Nodo;
import model.data_structures.noExisteObjetoException;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private HashSC<Llave,ArrayList<Multa>> datosSC;
	private HashLP<Llave, ArrayList<Multa>> datosLP;
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo(int capacidad)
	{
<<<<<<< HEAD
		datosSC = new HashSC<Llave, ArrayList<Multa>>(10);
		datosLP = new HashLP<Llave, ArrayList<Multa>>(10);
=======
		datosSC = new HashSC<Llave, Multa>(capacidad);
		datosLP = new HashLP<Llave, Multa>(capacidad);
>>>>>>> 6cdede3868dbd48c36232a6c9934c01b8eccb726
	}



	public HashSC<Llave, ArrayList<Multa>> darDatosHeap()
	{
		return datosSC;
	}

	public HashLP<Llave, ArrayList<Multa>> darDatosCola()
	{
		return datosLP;
	}

	public void cargarDatos() throws noExisteObjetoException, ParseException 
	{
		String path = "./data/comparendos_dei_2018_small (1).geojson";
		JsonReader lector;


		try {
			lector = new JsonReader(new FileReader(path));
			JsonElement elem = JsonParser.parseReader(lector);
			JsonObject ja = elem.getAsJsonObject();
			JsonArray features = ja.getAsJsonArray("features");


			for(JsonElement e : features)
			{			
				JsonObject propiedades = (JsonObject) e.getAsJsonObject().get("properties");

				long id = propiedades.get("OBJECTID").getAsLong();
				String cadenaFecha = propiedades.get("FECHA_HORA").getAsString();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date fecha = sdf.parse(cadenaFecha.substring(0, 9));
				String medioDete = propiedades.getAsJsonObject().get("MEDIO_DETECCION").getAsString();
				String claseVehiculo = propiedades.getAsJsonObject().get("CLASE_VEHICULO").getAsString();
				String tipoServicio = propiedades.getAsJsonObject().get("TIPO_SERVICIO").getAsString();
				String infraccion = propiedades.getAsJsonObject().get("INFRACCION").getAsString();
				String descripcion = propiedades.getAsJsonObject().get("DES_INFRACCION").getAsString();
				String localidad = propiedades.getAsJsonObject().get("LOCALIDAD").getAsString();


				JsonObject geometry = (JsonObject) e.getAsJsonObject().get("geometry");

				String tipo = geometry.get("type").getAsString();

				double[] listaCoords = new double[3];
				JsonArray coordsJson = geometry.getAsJsonArray("coordinates");
				for(int i = 0; i < coordsJson.size(); i ++)
				{
					listaCoords[i] = coordsJson.get(i).getAsDouble();
				}

				Geo geometria = new Geo(tipo, listaCoords);

				Multa multa = new Multa(id, fecha, medioDete, claseVehiculo, tipoServicio, infraccion, descripcion, localidad, geometria);

				Llave llave = new Llave(fecha, claseVehiculo, infraccion);
				
				
				
				datosSC.put(llave, new ArrayList<>() );
				datosSC.get(llave).add(multa);
				datosLP.put(llave, new ArrayList<>() );
				datosLP.get(llave).add(multa);
				
				
			} //llave for grande
		}//llave try
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	} //llave metodo

	public String reque1()
	{
		String msj = "";
		try {
			cargarDatos();
		} catch (noExisteObjetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		msj += "primero: " + datosSC.primero() + " " + datosLP.primero() + "<br>";
		msj += "ultimo: " + datosSC.ultimo() + " " + datosLP.ultimo() + "<br>";
		msj += "duplas: " + datosSC.size();
		msj += "tama�o inicial: " + datosSC.tamanoInicial() + " " + datosLP.tamanoInicial() + "<br>";
		msj += "tama�o final: " + datosSC.tamanoFinal() + " " + datosLP.tamanoFinal() + "<br>";
		msj += "factor carga: " + datosSC.factorCarga() + " "+ datosLP.factorCarga() + "<br>";
		msj += "rehashes";
		
		return msj;
	}
	private Multa[] primYUlt() throws FileNotFoundException, ParseException
	{
		Multa[] retornar = new Multa[2];
		
		String path = "./data/comparendos.txt";
		JsonReader lector;


		try {
			lector = new JsonReader(new FileReader(path));
			JsonElement elem = JsonParser.parseReader(lector);
			JsonObject ja = elem.getAsJsonObject();
			JsonArray features = ja.getAsJsonArray("features");
			int cont = 0; 


			for(int i = 0; i < features.size(); i += features.size()-1)
			{	
				JsonElement e = features.get(i);
				JsonObject propiedades = (JsonObject) e.getAsJsonObject().get("properties");

				long id = propiedades.get("OBJECTID").getAsLong();
				String cadenaFecha = propiedades.get("FECHA_HORA").getAsString();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Date fecha = sdf.parse(cadenaFecha);
				String medioDete = propiedades.getAsJsonObject().get("MEDIO_DETE").getAsString();
				String claseVehiculo = propiedades.getAsJsonObject().get("CLASE_VEHI").getAsString();
				String tipoServicio = propiedades.getAsJsonObject().get("TIPO_SERVI").getAsString();
				String infraccion = propiedades.getAsJsonObject().get("INFRACCION").getAsString();
				String descripcion = propiedades.getAsJsonObject().get("DES_INFRAC").getAsString();
				String localidad = propiedades.getAsJsonObject().get("LOCALIDAD").getAsString();


				JsonObject geometry = (JsonObject) e.getAsJsonObject().get("geometry");

				String tipo = geometry.get("type").getAsString();

				double[] listaCoords = new double[3];
				JsonArray coordsJson = geometry.getAsJsonArray("coordinates");
				for(int j = 0; j < coordsJson.size(); j ++)
				{
					listaCoords[i] = coordsJson.get(i).getAsDouble();
				}

				Geo geometria = new Geo(tipo, listaCoords);

				Multa multa = new Multa(id, fecha, medioDete, claseVehiculo, tipoServicio, infraccion, descripcion, localidad, geometria);

				retornar[cont] = multa;
				++cont;
				
				
			} //llave for grande
		}//llave try
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
		return retornar;
	}
	
	public void BuscarComparendosLP(String fecha, String clasVeh, String infraccion)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date;
		try {
			date = sdf.parse(fecha);
			Llave llave = new Llave(date, clasVeh, infraccion);
			
			ArrayList<Multa> datos = datosLP.get(llave);
			Collections.sort(datos);
			
			System.out.println("Objectid | Fecha | Tipo de vehiculo | Infraccion ");
			for (Multa multa: datos)
			{
				System.out.println(multa.getId() + "|" + multa.getFecha() + "|" + multa.getVehiculo() + "|" + multa.getInfraccion());
			}
			
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void BuscarComparendosSC(String fecha, String clasVeh, String infraccion)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date;
		try {
			date = sdf.parse(fecha);
			Llave llave = new Llave(date, clasVeh, infraccion);
			
			ArrayList<Multa> datos = datosSC.get(llave);
			Collections.sort(datos);
			
			System.out.println("Objectid | Fecha | Tipo de vehiculo | Infraccion ");
			for (Multa multa: datos)
			{
				System.out.println(multa.getId() + "|" + multa.getFecha() + "|" + multa.getVehiculo() + "|" + multa.getInfraccion());
			}
			
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}//llave clase