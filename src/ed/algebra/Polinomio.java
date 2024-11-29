package ed.algebra;
import java.util.*;

/**
 * Clase que implementa polinomios mátematicos
 * @author Fernando Romero Cruz
 * @version 1.0 (5 Mayo 2022)
 */
public class Polinomio {
    private ListaDoblementeLigada<Monomio> componentes;
    private int grado;

    /**
     * Constructor parametrizado que crea un polinomio a partir de una lista de monomios
     * @param c Lista ligada de monomios que componen el polinomio
     * @throws IllegalArgumentException En caso de recibir una lista vacía
     */
    public Polinomio(ListaDoblementeLigada<Monomio> c) {
	if(c.isEmpty())
	    throw new IllegalArgumentException();
	int max=0;
	ListaDoblementeLigada<Monomio> l = new ListaDoblementeLigada();
	ListIterator<Monomio> i = c.listIterator();
	while(i.hasNext()) {
	    Monomio actual = new Monomio(i.next());
	    int gradoActual = actual.obtenerGrado();
	    l.add(actual);
	    if(gradoActual > max)
		max = gradoActual;
	}
	this.componentes = l;
	this.grado = max;
    }

    /**
     * Constructor copia de un polinomio a partir de otro
     * @param p Polinomio por copiar en una nueva instancia
     */
    public Polinomio(Polinomio p) {
	this.componentes = new ListaDoblementeLigada();
	ListIterator<Monomio> i = p.componentes.listIterator();
	while(i.hasNext()) {
	    this.componentes.add(new Monomio(i.next()));
	}
	this.grado = p.grado;
    }

    /**
     * Método inspector del grado de un polinomio
     * @return Devuelve el grado más alto de los monomios que componen el polinomio
     */
    public int obtenerGrado() {
	return this.grado;
    }

    /**
     * Método que simplifica el polinomio y devuelve una nueva instancia de este
     * @return Devuelve el polinomio simplificado como una nueva instancia
     */
    public Polinomio simplifica() {
	Polinomio simplificado = new Polinomio(this);
	ListaDoblementeLigada<Monomio> l = simplificado.componentes;
        for(int i=0; i < l.size();i++) {
	    for(int j=i+1;j < l.size();j++) {
		Monomio m1 = l.get(i);
		Monomio m2 = l.get(j);
		if(m1.obtenerGrado() == m2.obtenerGrado()) {
		    l.remove(m1);
		    l.remove(m2);
		    if(m1.obtenerCoeficiente() != -m2.obtenerCoeficiente()){
			l.add(i,m1.más(m2));
			j--;
		    }
		    else {
			i--;
			break;
		    }
		}
	    }
	}
	return new Polinomio(l);
    }

    /**
     * Método que ordena el polinomio de mayor a menor grado
     * @return Devuelve el polinomio simplificado ordenado de mayor amenor grado
     */
    public Polinomio ordena() {
	Polinomio ordenado = this.simplifica();
	ListaDoblementeLigada<Monomio> l = new ListaDoblementeLigada();
	for(int i=0;i <= ordenado.grado;i++) {
	    l.add(null);
	}
	ListIterator<Monomio> i = ordenado.componentes.listIterator();
	while(i.hasNext()) {
	    Monomio actual = i.next();
	    int gradoActual = actual.obtenerGrado();
	    l.set((ordenado.grado - gradoActual),actual);
	}
	while(l.contains(null)) {
	    l.remove(null);
	}
	ordenado.componentes = l;
	return ordenado;
    }

    /**
     * Método que suma este polinomio con otro
     * @param sumando Polinomio por sumar
     * @return Devuelve el polinomio resultante de sumar dichos polinomios como una nueva instancia
     */
    public Polinomio más(Polinomio sumando) {
	Polinomio p = new Polinomio(this);
	if(sumando.grado > p.grado)
	    p.grado = sumando.grado;
        p.componentes.addAll(sumando.componentes);
	return p.ordena();
    }

    /**
     * Método que resta este polinomio con otro
     * @param sustraendo Polinomio por restar
     * @return Devuelve el resultado de la resta del polinomio menos el parámetro como una nueva instancia
     */
    public Polinomio menos(Polinomio sustraendo) {
	ListaDoblementeLigada<Monomio> l = new ListaDoblementeLigada();
	l.add(new Monomio(-1,0));
	Polinomio sus = sustraendo.por(new Polinomio(l));
	return this.más(sus);
	
    }

    /**
     * Método que multiplica el polinomio con otro
     * @param factor Polinomio por multiplicar
     * @return Devuelve el resultado del producto como una nueva instancia
     */
    public Polinomio por(Polinomio factor) {
	Polinomio f1 = this.simplifica();
	Polinomio f2 = factor.simplifica();
	ListaDoblementeLigada<Monomio> ultima = new ListaDoblementeLigada();
	ListIterator<Monomio> i1=f1.componentes.listIterator(), i2;
	while(i1.hasNext()) {
	    Monomio factorUno = i1.next();
	    i2 = f2.componentes.listIterator();
	    while(i2.hasNext()) {
		ultima.add(factorUno.por(i2.next()));
	    }
	}
	Polinomio resultado = new Polinomio(ultima);
	return resultado.ordena();
    }

    /**
     * Método que genera una cadena que representa el polinomio
     * @return Devuelve un String con la suma/resta de monomios que componen el polinomio
     */
    public String toString() {
	String cadena = "";
        for(int i=0;i < componentes.size();i++) {
	    String m1 = componentes.get(i).toString();
	    if(i == 0 && componentes.get(i).obtenerCoeficiente() > 0) {
		cadena += m1.substring(2,m1.length())+" ";
	    }
	    else if(i < componentes.size()-1){
		cadena += m1+" ";
	    }
	    else
		cadena += m1;
	}
	return cadena;
    }
}
