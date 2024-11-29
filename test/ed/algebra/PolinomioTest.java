package ed.algebra;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias para la clase Polinomio que ponen a prueba sus métodos
 * @author Fernando Romero Cruz
 * @version 1.0 (6 Mayo 2022)
 */
class PolinomioTest {

    /**
     * Prueba que verifica que el polinomio se imprima bien en pantalla de acuerdo a sus componentes
     */
    @Test
    void cadenaPolinomio() {
	System.out.println("/ToStringPolinomio:");
        ListaDoblementeLigada<Monomio> l = new ListaDoblementeLigada();
	l.add(new Monomio(3.1,2));
	l.add(new Monomio(-7,1));
	l.add(new Monomio(4.5,4));
	l.add(new Monomio(-1,0));
	Polinomio p = new Polinomio(l);
	System.out.println("(3.1,2)+(-7,1)+(4.5,4)+(-1,0) ---> "+p);
	assertEquals("3.1x^(2) - 7.0x + 4.5x^(4) - 1.0",p.toString());
    }

    /**
     * Prueba que verifica que la simplificación de un polinomio se realice satisfactoriamente
     */
    @Test
    void simplificaPolinomio() {
        System.out.println("/SimplificaPolinomio");
        ListaDoblementeLigada<Monomio> l = new ListaDoblementeLigada();
	l.add(new Monomio(3.1,2));
	l.add(new Monomio(-7,3));
	l.add(new Monomio(4,3));
	l.add(new Monomio(13,2));
	l.add(new Monomio(1.9,5));
	Polinomio p = new Polinomio(l);
	System.out.println(p);
	p = p.simplifica();
	System.out.println("= "+p);
	assertEquals("16.1x^(2) - 3.0x^(3) + 1.9x^(5)",p.toString());
    }

    /**
     * Prueba que verifica que los polinomios se simplifiquen y ordenen correctamente de mayor a menor grado
     */
    @Test
    void ordenaPolinomio() {
	System.out.println("/OrdenaPolinomio:");
	ListaDoblementeLigada<Monomio> l = new ListaDoblementeLigada();
	l.add(new Monomio(3.1,2));
	l.add(new Monomio(7,0));
	l.add(new Monomio(-4,1));
	l.add(new Monomio(-3.1,2));
	l.add(new Monomio(2.5,3));
	l.add(new Monomio(-1.9,1));
	Polinomio p = new Polinomio(l);
	System.out.println(p);
	Polinomio nuevo = p.ordena();
	System.out.println("= "+nuevo);
	assertEquals("2.5x^(3) - 5.9x + 7.0",nuevo.toString());
	assertEquals(3, nuevo.obtenerGrado());
    }

    /**
     * Prueba que verifica que la suma de 2 polinomios sea correcta
     */
    @Test
    void sumaPolinomio() {
	System.out.println("/SumaPolinomio:");
	ListaDoblementeLigada<Monomio> l1 = new ListaDoblementeLigada();
	l1.add(new Monomio(3.1,2));
	l1.add(new Monomio(1,7));
	l1.add(new Monomio(-7,0));
	l1.add(new Monomio(4,1));
	l1.add(new Monomio(13,2));
	l1.add(new Monomio(2,1));
	Polinomio p1 = new Polinomio(l1);
	ListaDoblementeLigada<Monomio> l2 = new ListaDoblementeLigada();
	l2.add(new Monomio(28,8));
	l2.add(new Monomio(-4,2));
	l2.add(new Monomio(4.1,7));
	l2.add(new Monomio(13.4,2));
	l2.add(new Monomio(-1.7,1));
	Polinomio p3 = new Polinomio(l2);
	Polinomio res = p1.más(p3);
	System.out.println(p1);
	System.out.println(p3);
	System.out.println("= "+res);
	assertEquals("28.0x^(8) + 5.1x^(7) + 25.5x^(2) + 4.3x - 7.0",res.toString());
	assertEquals(8,res.obtenerGrado());
    }

    /**
     * Prueba que verifica que el producto de 2 polinomios sea correcto
     */
    @Test
    void productoPolinomio() {
	System.out.println("/ProductoPolinomio:");
	ListaDoblementeLigada<Monomio> l1 = new ListaDoblementeLigada();
	l1.add(new Monomio(3.1,2));
	l1.add(new Monomio(7,0));
	Polinomio p1 = new Polinomio(l1);
	ListaDoblementeLigada<Monomio> l2 = new ListaDoblementeLigada();
	l2.add(new Monomio(2,2));
	l2.add(new Monomio(-5,0));
	Polinomio p3 = new Polinomio(l2);
	Polinomio res = p1.por(p3);
	System.out.println(p1);
	System.out.println(p3);
	System.out.println("= "+res);
	assertEquals("6.2x^(4) - 1.5x^(2) - 35.0",res.toString());
	assertEquals(4,res.obtenerGrado());
    }
    
}
