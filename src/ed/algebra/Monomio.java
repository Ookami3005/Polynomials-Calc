package ed.algebra;

import java.util.*;

/**
 * Clase Monomio que implementa monomios mátematicos para ser utilizados en polinomios
 * @author Fernando Romero Cruz
 * @version 1.0 (5 Mayo 2022)
 */
public class Monomio {
    private double coeficiente;
    private int grado;

    /**
     * Constructor parametrizado de un Monomio con coeficiente distinto de 0 y grado mayor o igual a 0
     * @param a Coeficiente distinto de 0
     * @param b Exponente de la variable mayor o igual a 0
     * @throws IllegalArgumentException En caso de incumplir las condiciones del coeficiente o el grado
     */
    public Monomio(double a, int b) {
	this.asignarCoeficiente(a);
        this.asignarGrado(b);
    }

    /**
     * Constructor copia de un objeto Monomio
     * @param a Monomio del que se desea una instancia copia
     */
    public Monomio(Monomio a) {
	this.coeficiente = a.coeficiente;
	this.grado = a.grado;
    }

    /**
     * Método inspector del coeficiente de un monomio
     * @return Devuelve el coeficiente del monomio
     */
    public double obtenerCoeficiente() {
	return this.coeficiente;
    }

    /**
     * Método inspector del grado de un monomio
     * @return Devuelve el grado de un monomio
     */
    public int obtenerGrado() {
	return this.grado;
    }

    /**
     * Método modificador del coeficiente de un monomio
     * @param nuevoCoeficiente Coeficiente por asignar al monomio
     * @throws IllegalArgumentException En caso de recibir un coeficiente 0
     */
    public void asignarCoeficiente(double nuevoCoeficiente) {
	if(nuevoCoeficiente == 0)
	    throw new IllegalArgumentException();
	this.coeficiente = nuevoCoeficiente;
    }

    /**
     * Método modificador del grado de un monomio
     * @param nuevoGrado Grado por asignar al monomio
     * @throws IllegalArgumentException En caso de recibir un grado negativo
     */
    public void asignarGrado(int nuevoGrado) {
	if(nuevoGrado < 0)
	    throw new IllegalArgumentException();
	this.grado = nuevoGrado;
    }

    /**
     * Método que devuelve el resultado de la suma de este monomio con otro en una nueva instancia
     * @param sumando Monomio que vamos a sumar
     * @return Devuelve una nueva instancia del resultado de la suma de los monomios
     * @throws IllegalArgumentException En caso de recibir un monomio de grado diferente pues el resultado no sería un monomio
     */
    public Monomio más(Monomio sumando) {
	if(sumando.grado != this.grado)
	    throw new IllegalArgumentException();
	return new Monomio((sumando.coeficiente+this.coeficiente),this.grado);
    }

    /**
     * Método que multiplica el monomio por otro y devuelve el resultado en una nueva instancia
     * @param factor Monomio por multiplicar
     * @return Devuelve una nueva instancia del producto de los 2 monomios
     */
    public Monomio por(Monomio factor) {
	return new Monomio((this.coeficiente*factor.coeficiente),(this.grado+factor.grado));
    }

    /**
     * Método que devuelve una cadena que representa el monomio para ser imprimida en pantalla
     * @return Devuelve un String del monomio en la forma "+- ax^(b)"
     */
    @Override
    public String toString() {
	if(coeficiente == -1) {
	    if(grado == 0)
		return "- "+(-coeficiente);
	    else if(grado == 1)
		return "- x";
	    else
		return "- x^("+grado+")";
	}
        if(coeficiente < 0) {
	    if(grado == 0)
		return "- "+(-coeficiente);
	    else if(grado == 1)
		return "- "+(-coeficiente)+"x";
	    else
		return "- "+(-coeficiente)+"x^("+grado+")";
	}
	else if(coeficiente == 1) {
	    if(grado == 0)
		return "+ "+coeficiente;
	    else if(grado == 1)
		return "+ x";
	    else
		return "+ x^("+grado+")";
	}
	else {
	    if(grado == 0)
		return "+ "+coeficiente;
	    else if(grado == 1)
		return "+ "+coeficiente+"x";
	    else
		return "+ "+coeficiente+"x^("+grado+")";
	}
    }
}
