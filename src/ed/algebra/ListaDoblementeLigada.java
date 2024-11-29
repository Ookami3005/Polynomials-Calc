package ed.algebra;

import java.util.*;

/**
 * Clase que implementa una lista doblemente ligada extendiendo la clase ColecciónAbstracta
 * @author Fernando Romero Cruz
 * @version 1.0 (27 Abril 2022)
 * @see ColecciónAbstracta
 * @see List
 */
public class ListaDoblementeLigada<E> extends ColecciónAbstracta<E> implements List<E> {

    Nodo centinela;
    Nodo actual;

    /**
     * Constructor de una lista vacía por defecto
     */
    public ListaDoblementeLigada() {
	this.centinela = new Nodo(null,null,null);
	this.centinela.anterior = centinela;
	this.centinela.siguiente = centinela;
    }

    /**
     * Método que agrega un elemento en cierto índice de la lista
     * @param index Índice donde se desea insertar el elemento (0<index<=tam)
     * @param element Elemento por insertar
     * @throws IndexOutOfBoundsException En caso de recibir un índice fuera del rango de la lista
     */
    public void add(int index, E element) {
	if(index < 0 || index>tam)
	    throw new IndexOutOfBoundsException();
	ListIterator<E> i = this.listIterator(index);
	i.add(element);
    }

    /**
     * Método que agrega un elemento al final de la lista
     * @param e Elemento por agregar
     * @return Devuelve true de haberlo agregado o false de lo contrario
     */
    public boolean add(E e) {
	int tamOriginal = tam;
	Iterador i = new Iterador(this,false);
	i.add(e);
	return tamOriginal != tam;
    }

    /**
     * Método que devuelve un iterador para la lista
     * @return Devuelve un objeto capaz de recorrer en orden los elementos de la lista
     * @see Iterator
     */
    public Iterator<E> iterator() {
	return new Iterador(this);
    }

    /**
     * Método que inserta los elementos de otra colección a partir de cierto índice
     * @param index Índice a partir del cual se desea insertar los elementos
     * @param c Colección con los elementos por agregar
     * @return Devuelve true en caso de haber agregado por lo menos un elemento o false de lo contrario
     * @throws IndexOutOfBoundsException En caso de recibir un índice fuera del rango de la lista
     * @throws IllegalArgumentException En caso de recibir la misma lista como parámetro
     * @throws NullPointerException En caso de recibir un parámetro null como colección
     */
    public boolean addAll(int index, Collection<? extends E> c) {
	if(0 > index || index > tam)
	    throw new IndexOutOfBoundsException();
	if(this.equals(c))
	    throw new IllegalArgumentException("No puede agregarse a si misma");
	if(c == null)
	    throw new NullPointerException();
	Iterator<?> i = c.iterator();
	int tamOriginal = tam;
	while(i.hasNext()) {
	    this.add(index++,(E) i.next());
	}
	return tamOriginal != tam;
    }

    /**
     * Método que devuelve el elemento posicionado en cierto índice
     * @param index Índice donde se ubica el elemento deseado
     * @return Devuelve el elemento posicionado en el índice dado
     * @throws IndexOutOfBoundsException En caso de recibir un índice fuera del rango de la lista
     */
    public E get(int index) {
	if(0 > index || index >= tam)
	    throw new IndexOutOfBoundsException();
	ListIterator<E> i = this.listIterator(index);
	return i.next();
    }

    /**
     * Método que devuelve el índice de cierto objeto en caso de estar contenido en la lista
     * @param o Objeto del cual buscamos el índice
     * @return Devuelve el índice del objeto recibido o -1 en caso de no estar contenido en la lista
     */
    public int indexOf(Object o) {
	ListIterator<E> i = this.listIterator();
	while(i.hasNext()) {
	    E elem = i.next();
	    if(Objects.equals(elem,o)) {
		return i.previousIndex();
	    }
	}
	return -1;
    }

    /**
     * Método que devuelve el último indice donde se ubique cierto elemento
     * @param o Objeto del cual se desea su último índice
     * @return Devuelve el ultimo índice en el que se encuentre posicionado un elemento igual al objeto recibido o -1 de no estar contenido en la lista
     *
     */
    public int lastIndexOf(Object o) {
	ListIterator<E> i = new Iterador(this,false);
	while(i.hasPrevious()) {
	    E elem = i.previous();
	    if(Objects.equals(elem,o)) {
		return i.nextIndex();
	    }
	}
	return -1;
    }

    /**
     * Método que devuelve un iterador de tipo lista
     * @return Un objeto capaz de recorrer hacia adelante o atras los elementos de la lista, además de conocer sus índices y poder modificar los elementos
     * @see ListIterator
     */
    public ListIterator<E> listIterator() {
	return new Iterador(this);
    }

    /**
     * Método que devuelve un iterador de tipo lista posicionado en cierto índice
     * @param index Índice del cual se desea partir
     * @return Un objeto capaz de recorrer hacia adelante y atras los elementos de la lista, entre otras propiedades que esta posicionado en cierto índice de la lista
     * @throws IndexOutOfBoundsException En caso de recibir un índice fuera del rango de la lista
     * @see ListIterator
     */
    public ListIterator<E> listIterator(int index) {
	if(0 > index || index > tam)
	    throw new IndexOutOfBoundsException();
        Iterador i;
	double mitad = tam/2;
	if(index <= mitad) {
	    i = new Iterador(this,true);
	    while(i.hasNext()) {
		if(i.nextIndex() == index)
		    break;
		i.next();
	    }
	}
	else {
	    i = new Iterador(this,false);
	    while(i.hasPrevious()) {
		if(i.nextIndex() == index)
		    break;
		i.previous();
	    }
	}
	i.sentido = 0;
	return i;
    }

    /**
     * Método que remueve el elemento posicionado en cierto índice
     * @param index Índice del elemento que se desea eliminar
     * @return Devuelve el elemento eliminado
     * @throws IndexOutOfBoundsException En caso de recibir un índice fuera del rango de la lista
     */
    public E remove(int index) {
	if(0 > index || index >= tam)
	    throw new IndexOutOfBoundsException();
	ListIterator<E> i = this.listIterator(index);
	E temp = i.next();
	i.remove();
        return temp;
    }

    /**
     * Método sobreescrito que elimina todos los elementos de la lista (O(1))
     */
    @Override
    public void clear() {
	this.centinela.anterior = centinela;
	this.centinela.siguiente = centinela;
	tam = 0;
    }

    /**
     * Método que cambia el valor de un elemento posicionado en cierto índice
     * @param index Índice del elemento que se desea modificar
     * @param element Nuevo valor por asignar a la posición que indica el índice
     * @return Devuelve el valor que se hallaba antes de ser modificado
     * @throws IndexOutOfBoundsException En caso de recibir un índice fuera del rango de la lista
     */
    public E set(int index, E element) {
	if(0 > index || index >= tam)
	    throw new IndexOutOfBoundsException();
	ListIterator<E> i = this.listIterator(index);
	E temp = i.next();
	i.set(element);
	return temp;
    }

    /**
     * Método que devuelve una sublista de una lista, capaz de reflejar los cambios que se hagan en la lista original
     * @param fromIndex Índice del primer elemento de la sublista
     * @param toindex Índice del primer elemento que ya no estará en la sublista
     * @return Devuelve una Lista acotada capaz de reflejar los cambios en la lista original
     * @throws IndexOutOfBoundsException En caso de recibir índices fuera del alcance de la lista o invertidos
     */
    public List<E> subList(int fromIndex, int toIndex) {
	if(fromIndex < 0 || toIndex > tam || fromIndex > toIndex)
	    throw new IndexOutOfBoundsException();
	return new Sublista(this,fromIndex,toIndex);
    }

    /**
     * Método auxiliar que devuelve el nodo del elemento posicionado segun cierto índice
     * @param index Índice del elemento del cual se desea el nodo que lo contiene
     * @return Devuelve el nodo que contiene el elemento
     * @throws IndexOutOfboundsException En caso de recibir un índice fuera del alcance de la lista
     */
    private Nodo getNodo(int index) {
	if(index < 0 || index >= tam)
	    throw new IndexOutOfBoundsException();
	int NodoBuscado;
	Nodo temp = centinela;
	for(int i = 0;i <= index;i++){
	    temp = temp.siguiente;
	}
	return temp;
    }

    /**
     * Clase interna que implementa nodos con 2 referencias
     * @author Fernando Romero Cruz
     * @version 1.0 (27 Abril 2022)
     */
    private class Nodo {
	Nodo anterior;
	Nodo siguiente;
	E valor;

	/**
	 * Constructor de un nodo que contiene un valor y 2 referencias a otros nodos
	 * @param valor Valor que almacenará el nod
	 * @param anterior Primera referencia a otro nodo
	 * @param siguiente Segunda referencia a otro nodo
	 */
	public Nodo(E valor, Nodo anterior, Nodo siguiente) {
	    this.valor = valor;
	    this.anterior = anterior;
	    this.siguiente = siguiente;
	}
    }

    /**
     * Clase interna que implementa un iterador de lista para una Lista doblemente ligada
     * @author Fernando Romero Cruz
     * @version 1.0 (27 Abril 2022)
     * @see ListIterator
     */
    private class Iterador implements ListIterator<E> {
	Nodo apuntador;
	int sentido = 0;
	double auxIndices;
	ListaDoblementeLigada<E> lista;

	/**
	 * Constructor que instancía un iterador que parte del inicio de la lista
	 * @param lista Lista de la cual se desea el iterador
	 */
	public Iterador(ListaDoblementeLigada<E> lista) {
	    this.lista = lista;
	    this.apuntador = new Nodo(null,centinela,centinela.siguiente);
	    this.auxIndices = -0.5;
	}

	/**
	 * Constructor que instancia un iterador que puede partir del inicio o final de la lista
	 * @param lista Lista de la cual se desea el iterador
	 * @param inicio Bandera que define el punto de partida del iterador, true para el principio y false para el final
	 */
	public Iterador(ListaDoblementeLigada<E> lista, boolean inicio) {
	    this.lista = lista;
	    if(inicio) {
		this.apuntador = new Nodo(null,centinela,centinela.siguiente);
		this.auxIndices = -0.5;
	    }
	    else {
		this.apuntador = new Nodo(null,centinela.anterior,centinela);
		this.auxIndices = tam - 0.5;
	    }
	}

	/**
	 * Método que anuncia si hay un elemento previo a la posición del iterador o no
	 * @return Devuelve true en caso de haber un elemento previo que se pueda recorrer o false de lo contrario
	 */
	public boolean hasPrevious() {
	    if(!Objects.equals(this.apuntador.anterior, centinela))
		return true;
	    return false;
	}

	/**
	 * Método que anuncia si hay un elemento siguiente a la posición del iterador o no
	 * @return Devuelve true en caso de haber un elemento siguiente por recorrer o false de lo contrario
	 */
	public boolean hasNext() {
	    if(!Objects.equals(this.apuntador.siguiente, centinela))
		return true;
	    return false;
	}

	/**
	 * Método que devuelve el elemento previo y recorre el iterador hacia atrás
	 * @return Devuelve el elemento previo a la posición del iterador
	 * @throws NoSuchElementException En caso de llamarse a este método cuando no hay elementos previos
	 */
	public E previous() {
	    if(!this.hasPrevious())
		throw new NoSuchElementException();
	    this.auxIndices--;
	    this.sentido = -1;
	    E temp = this.apuntador.anterior.valor;
	    this.apuntador = new Nodo(null,apuntador.anterior.anterior,apuntador.anterior);
	    return temp;
	}

	/**
	 * Método que devuelve el elemento siguiente y recorre el iterador hacia adelante
	 * @return Devuelve el elemento siguiente a la posición del iterador
	 * @throws NoSuchElementException EN caso de llamarse a este método cuando no hay más elementos siguientes
	 */
	public E next() {
	    if(!this.hasNext())
		throw new NoSuchElementException();
	    this.auxIndices++;
	    this.sentido=1;
	    E temp = this.apuntador.siguiente.valor;
	    this.apuntador = new Nodo(null,apuntador.siguiente,apuntador.siguiente.siguiente);
	    return temp;
	}

	/**
	 * Método que devuelve el índice del elemento previo al iterador
	 * @return El índice del elemento previo a la posición del iterador
	 */
	public int previousIndex() {
	    return (int) (auxIndices - 0.5);
	}

	/**
	 * Método que devuelve el índice del elemento siguiente al iterador
	 * @return Devuelve el índice del elemento siguiente a la posición del iterador 
	 */
	public int nextIndex() {
	    return (int) (auxIndices + 0.5);
	}

	/**
	 * Método que agrega un elemento a la lista antes del elemento siguiente del iterador y recorre a este último hacia adelante
	 * @param Elemento por agregar
	 */
	public void add(E e) {
	    Nodo nuevo = new Nodo(e,apuntador.anterior,apuntador.siguiente);
	    nuevo.anterior.siguiente = nuevo;
	    nuevo.siguiente.anterior = nuevo;
	    this.apuntador.anterior = nuevo;
	    tam++;
	    this.sentido = 0;
	}

	/**
	 * Método que remueve el último elemento retornado por next() o previous()
	 * @throws IllegalStateException En caso de llamarse a este método sin haber invocado next() o previous() primero
	 */
	public void remove() {
	    if(sentido == 0)
		throw new IllegalStateException();
	    else if(sentido == 1) {
		apuntador.anterior.anterior.siguiente = apuntador.siguiente;
		apuntador.siguiente.anterior = apuntador.anterior.anterior;
		apuntador.anterior = apuntador.anterior.anterior;
	    }
	    else if(sentido == -1) {
		apuntador.siguiente.siguiente.anterior = apuntador.anterior;
		apuntador.anterior.siguiente = apuntador.siguiente.siguiente;
		apuntador.siguiente = apuntador.siguiente.siguiente;
	    }
	    tam--;
	    this.sentido = 0;
	}

	/**
	 * Método que modifica el ultimo elemento de la lista retornado por next() o previous()
	 * @param e Nuevo valor por asignar al elemento
	 * @throws IllegalStateException En caso de llamarse este método sin haber invocado next() o previous() primero
	 */
	public void set(E e) {
	    if(sentido == 0)
		throw new IllegalStateException();
	    else if(sentido == 1) {
		apuntador.anterior.valor = e;
	    }
	    else if(sentido == -1) {
		apuntador.siguiente.valor = e;
	    }
	}
    }

    /**
     * Clase interna que gestiona las sublistas de una ListaDoblementeLigada
     * @author Fernando Romero Cruz
     * @version 1.0 (3 Mayo 2022)
     * @see List
     */
    private class Sublista extends ListaDoblementeLigada<E> {
	private ListaDoblementeLigada<E> superLista;
	private int frente, tras;

	/**
	 * Constructor parametrizado de una Sublista acotada dentro de otra Lista
	 */
	public Sublista(ListaDoblementeLigada<E> superLista, int frente, int tras) {
	    this.superLista = superLista;
	    this.frente = frente;
	    this.tras = tras;
	}

	/**
	 * Método que determina si un objeto esta o no en la Sublista
	 * @param o Objeto que se desea saber si esta contenido o no
	 * @return Devuelve true de estar contenido o false de lo contrario
	 */
	@Override
	public boolean contains(Object o) {
	    int indice = superLista.indexOf(o);
	    if(indice >= frente && indice < tras)
		return true;
	    return false;
	}

	/**
	 * Método que agrega un elemento a la sublista en un índice específico y que se ve reflejado en la lista original
	 * @param index Índice donde se desea introducir el elemento
	 * @param element Elemento por introducir
	 * @throws IndexOutOfBoundsException En caso de recibir un índice fuera del alcance de la sublista
	 */
	@Override
	public void add(int index, E element) {
	    if(index < 0 || index > this.size())
		throw new IndexOutOfBoundsException();
	    superLista.add((index+frente),element);
	    tras++;
	}

	/**
	 * Método que agrega un elemento al final de la sublista y se ve reflejado en la lista original
	 * @param e Elemento por agregar
	 * @return Devuelve veradero de haberlo agregado o false de lo contrario
	 */
	@Override
	public boolean add(E e) {
	    int tamOriginal = this.size();
	    superLista.add(tras,e);
	    tras++;
	    return this.size() != tamOriginal;
	}

	/**
	 * Método que agrega los elementos de una colección a la sublista y se ven reflejados en la lista original
	 * @param c Colección que contiene los elementos por agregar
	 * @return Devuelve verdadero de haber agregado por lo menos uno o false de lo contrario
	 * @throws IndexOutOfBoundsException En caso de recibir un índice fuera del alcance de la sublista
	 * @throws NullPointerException Si recibe un parámetro null
	 * @throws IllegalArgumentException Si se recibe a si misma como parámetro
	 */
	@Override
	public boolean addAll(int index,Collection<? extends E> c) {
	    if(index < 0 || index > this.size())
		throw new IndexOutOfBoundsException();
	    if(c == null)
		throw new NullPointerException();
	    if(this.equals(c))
		throw new IllegalArgumentException();
	    Iterator<?> i = c.iterator();
	    int tamOriginal = this.size();
	    while(i.hasNext()) {
		this.add(index++,(E) i.next());
	    }
	    return tamOriginal != this.size();
	}

	/**
	 * Método que devuelve el tamaño de la sublista
	 * @return Devuelve el valor del tamaño de la sublista
	 */
	@Override
	public int size() {
	    return tras-frente;
	}

	/**
	 * Método que remueve un elemento en cierto índice de la sublista y tambien de su respectiva posición en la lista original
	 * @param index Índice del elemento por remover
	 * @return Devuelve el elemento que se elimino de la lista
	 * @throws IndexOutOfBoundsException En caso de recibir un índice fuera del alcance de la sublista
	 */
	@Override
	public E remove(int index) {
	    if(index < 0 || index >= this.size())
		throw new IndexOutOfBoundsException();
	    E temp = superLista.remove(frente+index);
	    tras--;
	    return temp;
	}

	/**
	 * Método que remueve todos los elementos de la sublista y de la respectiva lista original
	 */
	@Override
	public void clear() {
	    while(frente != tras) {
		this.remove(0);
	    }
	}

	/**
	 * Método que devuelve una sublista de esta sublista (Experimental)
	 * @param fromIndex Índice desde donde se desea comenzar a acotar la lista
	 * @param toIndex Índice del elemento a partir del cual ya no estarán en la sublista
	 * @return Devuelve una sublista donde los cambios realizados a esta se ven reflejados en la lista/sublista original
	 * @throws IndexOutOfBoundsException En caso de recibir índices fuera del alcance de la lista original o de estar invertidos
	 */
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
	    if(fromIndex < 0 || toIndex > tam || fromIndex > toIndex)
		throw new IndexOutOfBoundsException();
	    return new Sublista(this,fromIndex,toIndex);
	}

	/**
	 * Método que define si la sublista esta vacía o no
	 * @return Devuelve true si la sublista no contiene elementos ofalse de lo contrario
	 */
	@Override
	public boolean isEmpty() {
	    return frente == tras;
	}

	/**
	 * Método que devuelve un iterador de esta sublista
	 * @return Devuelve un objeto Iterador capaz de recorrer los elementos de esta lista
	 */
	@Override
	public Iterator<E> iterator() {
	    return new Subiterador(this,true);
	}

	/**
	 * Método que devuelve un iterador de lista para esta sublista
	 * @return Devuelve un objeto Iterador de lista capaz de recorrer hacia atrás y hacia adelante esta sublista
	 * @see ListIterator
	 */
	@Override
	public ListIterator<E> listIterator() {
	    return new Subiterador(this,true);
	}

	/**
	 * Método que devuelve un iterador de lista ya posicionado sobre cierto índice para esta sublista
	 * @param index Índice donde se desea posicionar el iterador
	 * @return Devuelve un objeto Iterador de lista ya posicionado sobre cierto índice
	 * @throws IndexOutOfBoundsException En caso de recibir un índice fuera del alcance de la sublista
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
	    if(index < 0 || index > this.size())
		throw new IndexOutOfBoundsException();
	    Subiterador i = new Subiterador(this,true);
	    while(i.hasNext()) {
		if(i.nextIndex() == index) {
		    break;
		}
		i.next();
	    }
	    return i;
	}

	/**
	 * Método que devuelve el elemento de la sublista posicionado sobre cierto índice
	 * @param index Índice del elemento que se desea
	 * @return El elemento posicionado en dicho índice
	 * @throws IndexOutOfBoundsException En caso de recibir un índice fuera del alcance de la sublista
	 */
	@Override
	public E get(int index) {
	    if(index < 0 || index >= size())
		throw new IndexOutOfBoundsException();
	    return this.superLista.get(frente+index);
	}

	/**
	 * Método que asigna un nuevo valor a una posición de la sublista
	 * @param index Índice donde se desea reasignar un elemento
	 * @param element Nuevo elemento por asignar a esa posición
	 * @return Devuelve el elemento que se encontraba en esa posición antes de asignarle una nueva
	 * @throws IndexOutOfBoundsException En caso de recibir un índice fuera del alcance de la sublista
	 */
	@Override
	public E set(int index, E element) {
	    if(index < 0 || index >= size())
		throw new IndexOutOfBoundsException();
	    return superLista.set((index+frente),element);
	}

	/**
	 * Método que devuelve el índice de la primera aparición de cierto elemento
	 * @param o Objeto del que se desea su índice
	 * @return Devuelve el primer índice donde se encuentre ese objeto o -1 de no encontrarlo
	 */
	@Override
	public int indexOf(Object o) {
	    ListIterator<E> i = superLista.listIterator(frente);
	    while(i.hasNext()) {
		if(i.nextIndex() >= tras)
		    break;
		if(Objects.equals(i.next(),o))
		    return i.previousIndex()-frente;
	    }
	    return -1;
	}

	/**
	 * Método que devuelve el último índice de la aparición de un objeto
	 * @param o Objeto del que se desea su índice
	 * @return Devuelve el último índice donde se encuentre dicho objeto o -1 de no encontrarlo
	 */
	@Override
	public int lastIndexOf(Object o) {
	    ListIterator<E> i = superLista.listIterator(tras);
	    while(i.hasPrevious()) {
		if(i.previousIndex() == frente-1)
		    break;
		if(Objects.equals(i.previous(),o))
		    return i.nextIndex()-frente;
	    }
	    return -1;
	}

	/**
	 * Clase interna de la clase Sublista que gestiona los iteradores "especiales" que se usan para una sublista
	 * @author Fernando Romero Cruz
	 * @version 1.0 (3 Mayo 2022)
	 * @see ListIterator
	 */
	private class Subiterador implements ListIterator<E> {
	    private Sublista sublista;
	    private Nodo frente, tras, apuntador;
	    private double auxIndices;
	    private int sentido;

	    /**
	     * Constructor parametrizado de un Iterador para una sublista
	     * @param sublista Sublista sobre la cual estamos trabajando
	     * @param inicio Bandera que indica si el Iterador inicia al principio o al final de la sublista
	     */
	    public Subiterador(Sublista sublista, boolean inicio) {
		this.sublista = sublista;
		this.frente=superLista.getNodo(sublista.frente-1);
		this.tras = superLista.getNodo(sublista.tras);

		if(inicio) {
		    apuntador = new Nodo(null,frente,frente.siguiente);
		    auxIndices = -0.5;
		}
		else {
		    apuntador = new Nodo(null,tras.anterior,tras);
		    auxIndices = sublista.size() - 0.5;
		}
	    }

	    /**
	     * Método que define si hay un elemento previo a la posición del Iterador
	     * @return Devuelve true de haber uno o false de lo contrario
	     */
	    public boolean hasPrevious() {
		if(Objects.equals(apuntador.anterior,frente))
		    return false;
		return true;
	    }

	    /**
	     * Método que define si hay un elemento siguiente a la posición del iterador
	     * @return Devuelve true si hay un elemento siguiente o false de lo contrario
	     */
	    public boolean hasNext() {
		if(Objects.equals(apuntador.siguiente,tras))
		    return false;
		return true;
	    }

	    /**
	     * Método que devuelve el elemento siguiente a la posición del iterador
	     * @return Devuelve el elemento siguiente a la posición del iterador
	     * @throws NoSuchElementException En caso de llamarse a este método cuando ya no hay elementos siguientes
	     */
	    public E next() {
		if(!hasNext())
		    throw new NoSuchElementException();
		E temp = apuntador.siguiente.valor;
		apuntador = new Nodo(null,apuntador.siguiente,apuntador.siguiente.siguiente);
		auxIndices++;
		sentido =1;
		return temp;
	    }

	    /**
	     * Método que devuelve el elemento previo a la posición del iterador
	     * @return Devuelve el elemento previo a la posición del iterador
	     * @throws NoSuchElementException En caso de llamarse a este método cuando ya no hay elementos previos
	     */
	    public E previous() {
		if(!hasPrevious())
		    throw new NoSuchElementException();
		E temp = apuntador.anterior.valor;
		apuntador = new Nodo(null,apuntador.anterior.anterior,apuntador.anterior);
		auxIndices--;
		sentido = -1;
		return temp;
	    }

	    /**
	     * Método que devuelve el índice siguiente según el iterador
	     * @return Devuelve el índice del elemento siguiente del iterador
	     */
	    public int nextIndex() {
		return (int) (auxIndices+0.5);
	    }

	    /**
	     * Método que devuelve el índice previo según el iterador
	     * @return Devuelve el índice del elemento previo del iterador
	     */
	    public int previousIndex() {
		return (int) (auxIndices-0.5);
	    }

	    /**
	     * Método que agrega un elemento según la posición del iterador
	     * @param e Elemento por agregar a la sublista
	     */
	    public void add(E e) {
		Nodo nuevo = new Nodo(e,apuntador.anterior,apuntador.siguiente);
		nuevo.anterior.siguiente = nuevo;
		nuevo.siguiente.anterior = nuevo;
		this.apuntador.anterior = nuevo;
		this.sentido = 0;
		sublista.tras++;
	    }

	    /**
	     * Método que remueve un elemento según el ultimo elemento regresado por el iterador
	     * @throws IllegalStateException Si se llama a este método sin haber llamado a next o previous antes
	     */
	    public void remove() {
		if(sentido == 0)
		    throw new IllegalStateException();
		else if(sentido == 1) {
		    apuntador.anterior.anterior.siguiente = apuntador.siguiente;
		    apuntador.siguiente.anterior = apuntador.anterior.anterior;
		    apuntador.anterior = apuntador.anterior.anterior;
		}
		else if(sentido == -1) {
		    apuntador.siguiente.siguiente.anterior = apuntador.anterior;
		    apuntador.anterior.siguiente = apuntador.siguiente.siguiente;
		    apuntador.siguiente = apuntador.siguiente.siguiente;
		}
		this.sentido = 0;
		sublista.tras--;
	    }

	    /**
	     * Método que modifica un elemento de la sublista según la posición del iterador
	     * @throws IllegalStateException En caso de llamarse este método sin haber llamado a next o previous primero
	     */
	    public void set(E e) {
		if(sentido == 0)
		    throw new IllegalStateException();
		else if(sentido == 1) {
		    apuntador.anterior.valor = e;
		}
		else if(sentido == -1) {
		    apuntador.siguiente.valor = e;
		}
	    }
	}
	
    }
    
}
