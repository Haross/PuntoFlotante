/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpretador;

import java.util.Stack;

/**
 *
 * @author Javier
 */
public class Interpretador {

    String funcion;

    public Interpretador() {
        funcion = "";
    }

    public Interpretador(String funcion) {
        this.funcion = funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

   /* public double getResultado(double x) {

    }*/

    public boolean checarParentesis() {
        Stack<String> pila = new Stack<String>();
        int i = 0;
        while (i < funcion.length()) {  // Recorremos la expresión carácter a carácter
            if (funcion.charAt(i) == '(') {
                pila.push("(");
            } // Si el paréntesis es de apertura apilamos siempre                               
            else if (funcion.charAt(i) == ')') {  // Si el paréntesis es de cierre actuamos según el caso
                if (!pila.empty()) {
                    pila.pop();
                } // Si la pila no está vacía desapilamos
                else {
                    pila.push(")");
                    break;
                } // La pila no puede empezar con un cierre, apilamos y salimos
            }
            i++;
        }
        if (pila.empty()) {
            return true;
        } else {
            return false;
        }
    }

    public double suma(double num1, double num2) {
        return num1 + num2;
    }

    public double resta(double num1, double num2) {
        return num1 + (-num2);
    }

}
