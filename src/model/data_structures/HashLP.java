package model.data_structures;

import java.util.Iterator;

public class HashLP<K extends Comparable<K>, V> implements IHashTable<K, V>
{

	private int n; // numero de elementos en el arreglo
	private int m; //tamaño inicial del arreglo
	private K[] keys;
	private V[] vals;


	public HashLP(int pM) 
	{
		m = pM;
		keys = (K[]) new Comparable[pM];
		vals = (V[]) new Object[pM];
		n = 0;
	}
	@Override
	public void put(K key, V val) 
	{
		int posicion = hash(key);
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
		int posicion = hash(key);
		if(keys[posicion] != key)
		{
			boolean encontro = false;
			while(!encontro)
			{
				posicion++;
				if(keys[posicion] == key)
				{
					encontro = true;
				}
				if(keys[posicion] == null)
				{
					break;
				}
			}
		}

		return vals[posicion];
	}

	@Override
	public V delete(K key) throws noExisteObjetoException
	{
		if (key == null || !contains(key))
		{
			throw new noExisteObjetoException();
		}
		int i = hash(key);
		while (!key.equals(keys[i]))
		{
			i = (i + 1) % m;
		}
		keys[i] = null;
		V borrar = vals[i];
		vals[i] = null;
		i = (i + 1) % m;
		while (keys[i] != null) 
		{
			// delete keys[i] an vals[i] and reinsert
			K keyToRehash = keys[i];
			V valToRehash = vals[i];
			keys[i] = null;
			vals[i] = null;
			n--;
			put(keyToRehash, valToRehash);
			i = (i + 1) % m;
		}
		n--;
		return  borrar;
	}



	@Override
	public Iterator<K> keys() 
	{
		LinkedQueue<K> queue = new LinkedQueue<K>();
		for (int i = 0; i < m; i++)
		{
			if (keys[i] != null) 
			{
				queue.enqueue(keys[i]);
			}
		}
		return queue.iterator();
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
	public boolean contains(K key) {
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}


}
