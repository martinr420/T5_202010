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
			try {
				resize();
			} catch (noExisteObjetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public V get(K key)  {

		NodoHash<K, V> retornar = null;
		int pos = hash(key);
		retornar = st[pos];
		while(retornar.getKey() != key && retornar != null)
		{
			retornar = retornar.darSiguiente();
		}
		if(retornar == null)
		{
			return null;
		}
		return retornar.getVal();
	}

	@Override
	public V delete(K key)
	{
		V retornar = null;
		int pos = hash(key);
		if(st[pos] == key)
		{
			if(st[pos].darSiguiente() == null)
			{
				retornar = st[pos].getVal(); 
				st[pos] = null;
			}
			else
			{
				retornar = st[pos].getVal(); 
				st[pos] = st[pos].darSiguiente();
			}
			n--;
		}
		else
		{
			NodoHash<K, V> actual = st[pos];
			while(actual.darSiguiente() != null)
			{
				if(actual.darSiguiente().getKey() == key)
				{
					retornar = actual.darSiguiente().getVal();
					if(actual.darSiguiente().darSiguiente() != null)
					{
						actual.cambiarSiguiente(actual.darSiguiente().darSiguiente());
						n--;	
					}
					else
					{
						actual.desconectarSiguiente();
					}
					
					break;
				}
				actual = actual.darSiguiente();
			}
		}
		
		return retornar;
	}



	@Override
	public Iterable<K> keys() {
		LinkedQueue<NodoHash<K, V>> queue = new LinkedQueue<NodoHash<K, V>>();
		for (int i = 0; i < m; i++) 
		{
			if(st[i] != null)
			{
				LinkedQueue<NodoHash<K, V>> miniQueue = st[i].keys();
				for(int j = 0; j < miniQueue.size(); j++)
				{
					try {
						queue.enqueue(miniQueue.dequeue());
					} catch (noExisteObjetoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
			}


		}
		return (Iterable<K>) queue;
	} 


	private void resize() throws noExisteObjetoException
	{
		m*=2;
		while(!esPrimo(m))
		{
			m++;
		}

		LinkedQueue<NodoHash<K,V>> queue = (LinkedQueue<NodoHash<K, V>>) this.keys();
		st = (NodoHash<K, V>[]) new NodoHash[m];
		for(int i = 0; i < queue.size(); i++)
		{
			NodoHash<K, V> insertar = queue.dequeue();
			int pos = hash(insertar.getKey());
			if(st[pos] == null)
			{
				st[pos] = insertar;
			}
			else
			{
				NodoHash<K, V> actual = st[pos];
				while(actual != null)
				{
					if(actual.getKey() == insertar.getKey())
					{
						actual.setVal(insertar.getVal());
						break;
					}
					else if(actual.darSiguiente() == null)
					{
						actual.cambiarSiguiente(insertar);
					}
				}
			}
		}



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
