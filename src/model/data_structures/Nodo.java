package model.data_structures;

import java.util.Iterator;

public class Nodo<K extends Comparable<K>> implements Iterator<Nodo<K>>
{

	private K key;
	private Nodo<K> siguiente;
	private Nodo<K> anterior;
	
	public Nodo(K pKey)
	{	
		siguiente = null;		
		key = pKey;
	}
	
	
	public Nodo<K> darSiguiente()
	{
		return  siguiente;
	}
	public Nodo<K> darAnterior()
	{
		return anterior;
	}
	public void cambiarSiguiente(Nodo<K> pSig)
	{
		siguiente = pSig;
	}
	
	public void cambiarAnterior(Nodo<K> pAnterior)
	{
		anterior = pAnterior;
	}
	
	public void desconectarSiguiente()
	{
		siguiente = null;
	}
	public void desconectarAnterior()
	{
		anterior = null;
	}
	
	public K getKey()
	{
		return key;
	}
	
	
	
	public boolean hasNext() 
	{
		return this.darSiguiente() != null;
	}


	
	public Nodo<K> next() 
	{
		return siguiente;
	}	

}
