package ed.algebra;

import java.util.*;
import java.lang.reflect.Array;
public abstract class ColecciónAbstracta<E> implements Collection<E> {

    protected int tam=0;

    /**
     * Método que agrega un elemento a la colección
     * @param e Elemento por agregar
     * @return Devuelve verdadero si pudo agregarlo o false de lo contrario
     */
    public abstract boolean add(E e);

    /**
     * Método que devuelve un iterador para recorrer todos los elementos de la colección
     * @return Devuelve un objeto capaz de recorrer todos los elementos de la estructura
     */
    public abstract Iterator<E> iterator();

    /**
     * Método que determina si la colección esta vacía o no
     * @return Devuelve true si esta vacía o false de lo contrario
     */
    public boolean isEmpty() {
	return tam == 0;
    }

    /**
     * Método que regresa el tamaño de una colección
     * @return Devuelve el numero de elementos en esta colección
     */
    public int size() {
	return tam;
    }

    /**
     * Método que determina si esta colección contiene cierto objeto
     * @param o Objeto por comparar
     * @return Devuelve true si algun elemento es igual al objeto o false de lo contrario
     */
    public boolean contains(Object o) {
	if(this.isEmpty())
	    return false;
	Iterator<E> iterador = this.iterator();
	while(iterador.hasNext()) {
	    Object e = iterador.next();
	    if(Objects.equals(o,e)) {
		return true;
	    }
	}
	return false;
    }

    /**
     * Método que devuelve un arreglo de tipo Object con todos los elementos de la colección
     * @return Devuelve un arreglo de tipo Object con los elementos de la colección
     */
    public Object[] toArray() {
	Object[] arreglo = new Object[this.size()];
	Iterator<E> iterador = this.iterator();
	int i = 0;
	while(iterador.hasNext()) {
	    Object e = iterador.next();
	    arreglo[i] = e;
	    i++;
	}
	return arreglo;
    }

    /**
     * Metodo que devuelve un arreglo genérico con los elementos de la colección
     * @param a Arreglo de referencia
     * @return Devuelve un arreglo genérico con los elementos de la colección
     */
    public <T> T[] toArray(T[] a) {
	if(a == null)
	    throw new NullPointerException();
	Iterator<E> iterador = this.iterator();
	if(a.length == this.size()) {
	    for(int i=0;i < a.length;i++) {
		a[i] = (T) iterador.next();
	    }
	    return a;
	}
        else if(a.length > this.size()) {
	    for(int i=0;i<a.length;i++) {
		if(iterador.hasNext())
		    a[i] = (T) iterador.next();
		else
		    a[i] = null;
	    }
	    return a;
	}
	else {
	    a = (T[]) Array.newInstance(a.getClass().getComponentType(), this.size());
	    for(int i=0;i < a.length;i++){
		a[i] = (T) iterador.next();
	    }
	    return a;
	}
    }

    /**
     * Método que determina si los elementos de una colección estan contenidos en esta colección
     * @param c Colección de elementos por comparar
     * @return Devuelve true si todos los elementos de c estan en esta colección o false de lo contrario
     * @throws NullPointerException En caso de recibir un parámetro null
     */
    public boolean containsAll(Collection<?> c) {
	if(c == null)
	    throw new NullPointerException();
	if(this.equals(c))
	    return true;
	if(this.isEmpty() || c.isEmpty())
	    return false;
	Iterator<E> iterador1 = this.iterator();
	Iterator<?> iterador2 = c.iterator();
	boolean estaAqui;
	while(iterador2.hasNext()) {
	    estaAqui = false;
	    Object elem = iterador2.next();
	    if(!this.contains(elem)) {
		return false;
	    }
	}
	return true;
    }

    /**
     * Método que añade los elementos de una colección a esta colección
     * @param c Colección de elementos por añadir
     * @return Devuelve true si por lo menos un elemento de c pudo añadirse o false de lo contrario
     * @throws NullPointerException En caso de recibir un parámetro null
     */
    public boolean addAll(Collection<? extends E> c) {
	if(c == null)
	    throw new NullPointerException();
	if(this.equals(c))
	    throw new IllegalArgumentException("Una coleccion no se puede agregar a si misma");
	if(c.isEmpty())
	    return false;
	Iterator<?> iterador = c.iterator();
	boolean cambio=false;
	while(iterador.hasNext()) {
	    E elem = (E) iterador.next();
	    boolean adicion = this.add(elem);
	    cambio = adicion || cambio;
	}
	return cambio;
    }

    /**
     * Método que remueve un elemento de la colección
     * @param o Objeto por comparar
     * @return Devuelve true si encontro y elimino un elemento igual al objeto, false de lo contrario
     */
    public boolean remove(Object o) {
	if(this.isEmpty())
	    return false;
	Iterator<E> iterador = this.iterator();
	while(iterador.hasNext()) {
	    Object e = iterador.next();
	    if(Objects.equals(o,e)) {
		iterador.remove();
		return true;
	    }
	}
	return false;
    }

    /**
     * Método que remueve otra colección de elementos de esta colección 
     * @param c Colección de elementos por remover
     * @return Devuelve true si pudo borrar por lo menos un elemento de esta colección o false de lo contrario
     * @throws NullPointerException En caso de recibir un parámetro null
     */
    public boolean removeAll(Collection<?> c) {
	if(c == null)
	    throw new NullPointerException();
	if(c.isEmpty() || this.isEmpty())
	    return false;
	if(this.equals(c)) {
	    this.clear();
	    return true;
	}
	Iterator<?> iterador = c.iterator();
	boolean cambio = false;
	while(iterador.hasNext()) {
	    Object elem = iterador.next();
	    if(c.contains(elem)) {
		iterador.remove();
		if(!cambio)
		    cambio = true;
	    }
	}
	return cambio;
    }

    /**
     * Método borra los elementos de esta colección que no estan contenidos en otra colección
     * @param c Colección de elementos por comparar
     * @return Devuelve true si borro por lo menos un elemento de esta colección o false de lo contrario
     * @throws NullPointerException En caso de recibir un parámetro null
     */
    public boolean retainAll(Collection<?> c) {
	if(c == null)
	    throw new NullPointerException();
	if(this.isEmpty() || this.equals(c))
	    return false;
	if(c.isEmpty()) {
	    this.clear();
	    return true;
	}
	Iterator<E> iterador = this.iterator();
	boolean cambio = false;
	while(iterador.hasNext()) {
	    E e = iterador.next();
	    if(!c.contains(e)) {
		iterador.remove();
		if(!cambio)
		    cambio = true;
	    }
	}
	return cambio;
    }

    /**
     * Método que vacia por completo una colección
     */
    public void clear() {
	Iterator<E> iterador = this.iterator();
	while(iterador.hasNext()) {
	    iterador.next();
	    iterador.remove();
	}
    }

    /**
     * Método que compara esta colección con un objeto
     * @param o Objeto por comparar
     * @return Devuelve true si el objeto y la colección son iguales o false de lo contrario
     */
    @Override
    public boolean equals(Object o) {
	if(o == null || !Objects.equals(this.getClass(), o.getClass())) {
	    return false;
	}
	Collection<E> c = (Collection) o;
	if(this.isEmpty() && c.isEmpty())
	    return true;
	if(this.isEmpty() || c.isEmpty())
	    return false;
	Iterator<E> iterador = this.iterator();
	Iterator<E> iterador2 = c.iterator();
	while(iterador.hasNext() && iterador2.hasNext()) {
	    E e1 = iterador.next();
	    E e2 = iterador2.next();
	    if(!Objects.equals(e1, e2)) {
		return false;
	    }
	}
	if(iterador.hasNext() || iterador2.hasNext())
	    return false;
	return true;
    }

    /**
     * Método que asigna un código único a cada colección
     * @return Devuelve un codigo formado por el tamaño de la colección y los hashCodes de los elementos
     */
    public int hashCode() {
	Iterator<E> iterador = this.iterator();
	String codigo = ""+this.size();
	while(iterador.hasNext()) {
	    codigo += ""+iterador.next().hashCode();
	}
	int cod = Integer.parseInt(codigo);
	return cod;
    }

    /**
     * Método que devuelve una cadena con todos los elementos de una colección
     * @return Una cadena con todos los elementos de la colección concatenados
     */
    @Override
    public String toString() {
	if(this.isEmpty()) {
	    return "Coleccion vacia";
	}
	Iterator<E> iterador = this.iterator();
	String cadena = "";
	while(iterador.hasNext()) {
	    cadena += iterador.next()+"\n";
	}
	return cadena;
    }
}
