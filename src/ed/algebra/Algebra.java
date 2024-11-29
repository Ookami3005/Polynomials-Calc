package ed.algebra;

public class Algebra {
    public static void main(String[] Args) {
	System.out.println("***POLINOMIOS***");
	ListaDoblementeLigada<Monomio> l = new ListaDoblementeLigada();
	l.add(new Monomio(2.3,2));
	l.add(new Monomio(1.9,3));
	l.add(new Monomio(-1,0));
	l.add(new Monomio(2.1,2));
	l.add(new Monomio(-4.8,1));
	Polinomio p = new Polinomio(l);
	l.clear();
	l.add(new Monomio(-1,2));
	l.add(new Monomio(4.8,1));
	l.add(new Monomio(-1,5));
	l.add(new Monomio(2,3));
	l.add(new Monomio(-1,3));
	l.add(new Monomio(-1.2,2));	
	l.add(new Monomio(3.8,5));
	l.add(new Monomio(8,1));
	l.add(new Monomio(2,0));
	Polinomio q = new Polinomio(l);
	System.out.println("Polinomio 1: "+p);
	System.out.println("Polinomio 2: "+q+"\n");

	System.out.println("1 Simplificado : "+p.simplifica());
	System.out.println("2 Simplificado : "+q.simplifica()+"\n");

	System.out.println("1 Ordenado : "+p.ordena());
	System.out.println("2 Ordenado : "+q.ordena()+"\n");

	System.out.println("Suma de 1 y 2: "+p.más(q)+"\n");

	System.out.println("Resta de 1 y 2: "+p.menos(q)+"\n");

	System.out.println("Producto de 1 y 2: "+p.por(q));
    }
}
