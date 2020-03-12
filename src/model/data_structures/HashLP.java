package model.data_structures;

import java.util.Iterator;

public class HashLP<K extends Comparable<K>, V> implements IHashTable<K, V>
{

	private int n; // numero de elementos en el arreglo
	private int m; //tamaño inicial del arreglo
	private K[] keys;
	private V[] vals;


	public public HashLP(int pM) 
	{
		m = pM;
		keys = (K[]) new Comparable[pM];
		vals = (V[]) new Object[pM];
		n = 0;
	}
	@Override
	public void put(K key, V val) 
	{
		int posicion = key.hashCode();
		double factorCarga = (double) n / (double) m;
		if(factorCarga >= 0.75)
		{
			resize();
		}
		if(keys[posicion] != null)
		{
			boolean encontro = false;
			while(!encontro)
			{
				if(keys[++posicion] == null)
				{
					encontro = true;
				}
			}
		}
		keys[posicion] = key;
		vals[posicion] = val;
		
			
		
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V delete(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	private int hash(K key) 
	{
		return (key.hashCode() & 0x7fffffff) % m;
	}

	private void resize()
	{
		m*=2;
		while(!esPrimo(m))
		{
			m++;
		}
		
		K[] nuevoKeys = (K[]) new Comparable[m]; 
		V[] nuevoVals = (V[]) new Object[m];
		
		for(int i = 0; i < m; i++)
		{
			nuevoKeys[i] = keys[i];
			nuevoVals[i] = vals[i];
		}
		keys = nuevoKeys;
		vals = nuevoVals;
	}
	
	private boolean esPrimo(int num)
	{
		boolean esPrimo = true;
		int otroNum = 2;
		while(otroNum <= Math.sqrt(num) && num != 2)
		{
			if(num % otroNum == 0  )
			{
				esPrimo = false; 
				break;
			}
			otroNum++;
		}
		return esPrimo;
	}


}
