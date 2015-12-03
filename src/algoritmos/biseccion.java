/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

/**
 *
 * @author Javier
 */
public class biseccion {

    double a, b, tol, p;
    int n;

    public biseccion(double a1, double b1, double tol1, int n1) {
        a = a1;
        b = b1;
        tol = tol1;
        n = n1;
    }

    private double f(double x) {

        double r = Math.pow(x, 3) - (7 * Math.pow(x, 2)) + (14 * x) - 6;
        return r;
    }

    public double calculoRaiz() {
        double p = a;
        int i = 0;
        double ba = 1;

        while (f(p) != 0 & i <= n & ba > tol) {
            double pa = p;
            p = (a + b) / 2;
            if (f(p) * f(a) > 0) {
                a = p;
            } else if (f(p) * f(a) < 0) {
                b = p;
            }
            i = i + 1;
            ba = Math.abs(p - pa) / p;
            System.out.println("-------Iteracion " + i + "--------");
            System.out.println("P= " + p);
            System.out.println("F(p)= " + f(p));
            System.out.println("a= " + a);
            System.out.println("b= " + b);
            System.out.println("F(a)= " + f(a));
            System.out.println("---------------------------");
        }
        return p;
    }

   /* public static void main(String[] args) {
        biseccion c = new biseccion(0, 1, 1e-2, 10);
        double raiz = c.calculoRaiz();
        System.out.println("La raiz es: " + raiz);
    }*/
}
