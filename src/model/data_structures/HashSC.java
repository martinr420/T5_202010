package model.data_structures;

import java.util.Iterator;

public class HashSC<K extends Comparable<K>, V> implements IHashTable<K, V>
{
	private int m; 
	private int n; //numero clave valor
	private NodoHash<K, V>[] st;

	public HashSC(int pM) {
		n= 0;
		m = pM;
		st = (NodoHash<K, V>[]) new NodoHash[m];
	} 

	@Override
	public void put(K key, V val)
	{
		NodoHash<K, V> insert = new NodoHash<K, V>(key, val);
		int pos = hash(key);
		if(st[pos] == null)
		{
			st[pos] = insert; 
		}
		else
		{
			NodoHash<K, V> actual = st[pos];
			boolean elNodoYaExiste = false;
			while(actual.darSiguiente() != null)
			{
				if(actual.getKey() == key)
				{
					actual.setVal(val);
					elNodoYaExiste = true;
					break;
				}		
				actual = actual.darSiguiente();
			}
			if(!elNodoYaExiste)
			{
				actual.cambiarSiguiente(insert);	
			}
		}
		n++;
		double total = (double)n / (double) m;
		if(total >= 0.5)
		{
			resize();
		}
	}

	@Override
	public V get(K key) throws noExisteObjetoException {

		NodoHash<K, V> retornar = null;
		int pos = hash(key);
		retornar = st[pos];
		while(retornar.getKey() != key && retornar != null)
		{
			retornar = retornar.darSiguiente();
		}
		if(retornar == null)
		{
			throw new noExisteObjetoException();
		}
		return retornar.getVal();
	}

	@Override
	public V delete(K key) throws noExisteObjetoException 
	{
		NodoHash<K, V> eliminar = null;
		int pos = hash(key);
		NodoHash<K, V> actual = st[pos];
		if(actual.getKey() == key )
		{
			if(actual.darSiguiente() != null)
			{
				st[pos] = actual.darSiguiente();
			}
			else
			{
				st[pos] = null;
			}
		}
		else
		{
			while(actual.darSiguiente().getKey() != key && actual != null)

			{
				actual = actual.darSiguiente();
			}
			eliminar = actual.darSiguiente();
			actual.cambiarSiguiente(eliminar.darSiguiente());
		}
		n--;
		return eliminar.getVal();
	}



	@Override
	public Iterable<K> keys() {
		
		LinkedQueue<K> queue = new LinkedQueue<>();
		for (int i = 0; i < st.length; i++) 
		{
			NodoHash<K, V> actual = st[i]; 
			while(actual != null)
			{
				queue.enqueue(actual.getKey());
				actual = actual.darSiguiente();
			}
			
		}
		
		return queue;
	}

	private void resize()
	{
		m*=2;
		while(!esPrimo(m))
		{
			m++;
		}

		NodoHash<K, V>[] temporal =(NodoHash<K,V>[]) new NodoHash[m];

		for(int i = 0; i < m; i++)
		{
			temporal[i] = st[i];
		}
		st = temporal;
	}
	/**
	 * crea una variable temporal llamada otroNum
	 * que comienza en dos y va dividiendo el numero ingresado por parametro
	 * si el cociente es entero entonces el numero es primo de lo contrario otroNum se 
	 * suma 1 a si mismo y repite el proceso hasta llegar a la raiz del numero ingresado por parametro
	 * @param num. el numeor para verificas si es primo
	 * @return true si es primo false de lo contrario
	 */

	private boolean esPrimo(int num)
	{
		boolean esPrimo = true;
		int otroNum = 2;
		if(num % 2 == 0 || num < 2 && num >= 0) // verifica si el numero es par
		{
			esPrimo = false;
		}
		while(otroNum <= Math.sqrt(num) && num > 2 && esPrimo) 
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

	private int hash(K key)
	{
		return (key.hashCode() & 0x7fffffff) % m;	
	}

}
