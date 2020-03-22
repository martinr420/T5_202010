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
	private HashSC<Llave, Multa> datosSC;
	private HashLP<Llave, Multa> datosLP;
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		datosSC = new HashSC<Llave, Multa>(10);
		datosLP = new HashLP<Llave, Multa>(10);
	}



	public HashSC<Llave, Multa> darDatosHeap()
	{
		return datosSC;
	}

	public HashLP<Llave, Multa> darDatosCola()
	{
		return datosLP;
	}

	public void cargarDatos() throws noExisteObjetoException, ParseException 
	{
		String path = "./data/comparendos.txt";
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
				for(int i = 0; i < coordsJson.size(); i ++)
				{
					listaCoords[i] = coordsJson.get(i).getAsDouble();
				}

				Geo geometria = new Geo(tipo, listaCoords);

				Multa multa = new Multa(id, fecha, medioDete, claseVehiculo, tipoServicio, infraccion, descripcion, localidad, geometria);

				Llave llave = new Llave(fecha, claseVehiculo, infraccion);
				
				datosSC.put(llave, multa);
				datosLP.put(llave, multa);
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
		msj += "tamaño inicial: " + datosSC.tamanoInicial() + " " + datosLP.tamanoInicial() + "<br>";
		msj += "tamaño final: " + datosSC.tamanoFinal() + " " + datosLP.tamanoFinal() + "<br>";
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

}//llave clase