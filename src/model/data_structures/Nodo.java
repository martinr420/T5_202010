package model.data_structures;

import java.util.Iterator;

public class Nodo<K extends Comparable<K>, V > implements Iterator<Nodo<K,V>>
{

	private K key;
	private V val;
	private Nodo<K, V> siguiente;
	
	public Nodo(K pKey, V pVal)
	{	
		siguiente = null;		
		key = pKey;
		val = pVal;
	}
	
	
	public Nodo<K,V> darSiguiente()
	{
		return  siguiente;
	}
	
	public void cambiarSiguiente(Nodo<K,V> pSig)
	{
		siguiente = pSig;
	}
	
	public void desconectarSiguiente()
	{
		siguiente = null;
	}
	
	public K getKey()
	{
		return key;
	}
	
	public V getVal()
	{
		return val;
	}

	@Override
	public boolean hasNext() 
	{
		return this.darSiguiente() != null;
	}


	@Override
	public Nodo<K,V> next() 
	{
		return siguiente;
	}	

}
