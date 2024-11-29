package ed.algebra;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias para la clase Monomio que ponen aprueba sus métodos
 */
class MonomioTest {

    /**
     * Prueba que comprueba que un monomio se imprima correctamente según los parametros de su instanciación
     */
    @Test
    void cadenaMonomio() {
	System.out.println("ToStringMonomio:");
	Monomio m1 = new Monomio(3.5,3);
	Monomio m2 = new Monomio(28,8);
	Monomio m3 = new Monomio(-4.3,0);
	Monomio m4 = new Monomio(1,3);
	System.out.println("(3.5,3) ---> "+m1);
	System.out.println("(28,8) ---> "+m2);
	System.out.println("(4.3,0) ---> "+m3);
	System.out.println("(1.2,3) ---> "+m4);
	assertEquals("+ 3.5x^(3)",m1.toString());
	assertEquals("+ 28.0x^(8)",m2.toString());
	assertEquals("- 4.3",m3.toString());
	assertEquals("+ x^(3)",m4.toString());
    }

    /**
     * Prueba que intenta instanciar un monomio de grado negativo, esperando recibir una excepción
     * @throws IllegalStateException En caso de que no reciba una excepción IllegalArgumentException
     */
    @Test()
    void gradoNegativo() {
	System.out.println("GradoNegativoMonomio: ");
	System.out.println("Revisa que se lance una IllegalArgumentException en caso de querer construir un monomio de grado negativo");
	try {
	    Monomio m1 = new Monomio(5.4,-3);
	    System.out.println("*No se lanzo!");
	    throw new IllegalStateException("No se recibió la excepción");
	}
	catch(IllegalArgumentException e) {
	    System.out.println("*Correcto!");
	}
    }

    /**
     * Prueba que intenta instanciar un monomio de coeficiente 0, esperando recibir una excepción
     * @throws IllegalStateException En caso de que no reciba una excepción illegalArgumentException
     */
    @Test()
    void coeficienteCero() {
	System.out.println("CoeficienteCeroMonomio: ");
	System.out.println("Revisa que se lance una IllegalArgumentException en caso de querer construir un monomio de coeficiente 0");
	try {
	    Monomio m1 = new Monomio(0,1);
	    System.out.println("*No se lanzo!");
	    throw new IllegalStateException("No se recibió la excepción correspondiente");
	}
	catch(IllegalArgumentException e) {
	    System.out.println("*Correcto!");
	}
    }

    /**
     * Prueba que verifica que la suma de 2 monomios de mismo grado
     */
    @Test
    void sumaMonomio() {
	System.out.println("SumaMonomio:");
	Monomio monomio1 = new Monomio(5.2, 2);
	Monomio monomio2 = new Monomio(1.2, 2);
	Monomio r = monomio1.más(monomio2);
	System.out.println("("+monomio1 + ") + " + "(" +monomio2 + ") = " + r);
	assertEquals(6.4, r.obtenerCoeficiente());
	assertEquals(2, r.obtenerGrado());
	Monomio monomio3 = new Monomio(-21.6, 4);
	Monomio monomio4 = new Monomio(6.3, 4);
	Monomio r2 = monomio3.más(monomio4);
	System.out.println("("+monomio3 + ") + " + "(" +monomio4 + ") = " + r2);
	assertEquals(-15.3, r2.obtenerCoeficiente());
	assertEquals(4, r2.obtenerGrado());
    }

    /**
     * Prueba que intenta realizar una suma de monomios de diferente grado, esperando recibir una excepción
     * @throws IllegalStateException En caso de no recibir una excepción IllegalArgumentException
     */
    @Test
    void sumaMonomioGradoDiferente() {
	System.out.println("Suma de monomios de grado diferente");
	Monomio m1 = new Monomio(2.3,4);
	Monomio m2 = new Monomio(3.1,2);
	try{
	    m1.más(m2);
	    System.out.println("*No se lanzo una excepción!");
	    throw new IllegalStateException("No se recibió una excepción");
	}
	catch(IllegalArgumentException e) {
	    System.out.println("*Correcto!");
	}
    }

    /**
     * Prueba que verifica que el producto de 2 monomios sea correcto
     */
    @Test
    void productoMonomio() {
	System.out.println("ProductoMonomio:");
	Monomio monomio1 = new Monomio(7.1, 3);
	Monomio monomio2 = new Monomio(1.2, 1);
	Monomio r = monomio1.por(monomio2);
	System.out.println("("+monomio1 + ") * " + "(" +monomio2 + ") = " + r);
	assertEquals(8.52, r.obtenerCoeficiente());
	assertEquals(4, r.obtenerGrado());
	Monomio monomio3 = new Monomio(-3.4, 4);
	Monomio monomio4 = new Monomio(6, 2);
	Monomio r2 = monomio3.por(monomio4);
	System.out.println("("+monomio3 + ") * " + "(" +monomio4 + ") = " + r2);
	assertEquals(-20.4, r2.obtenerCoeficiente());
	assertEquals(6, r2.obtenerGrado());
    }
    
}
