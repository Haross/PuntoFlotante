//Conversión de notación Infija a Postfija mediante uso de pilas
package Interpretador;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Javier
 */
public class ToPostfix {

    String funcion;

    public ToPostfix(String funcion) {
        this.funcion = depurar(funcion);
    }

    public String getPostfix() {
        String[] arrayInfix = funcion.split(" ");

        //Declaración de las pilas
        Stack< String> E = new Stack< String>(); //Pila entrada
        Stack< String> P = new Stack< String>(); //Pila temporal para operadores
        Stack< String> S = new Stack< String>(); //Pila salida

        //Añadir la array a la Pila de entrada (E)
        for (int i = arrayInfix.length - 1; i >= 0; i--) {
            E.push(arrayInfix[i]);
        }
        try {
            //Algoritmo Infijo a Postfijo
            while (!E.isEmpty()) {
                switch (pref(E.peek())) {
                    case 1:
                        P.push(E.pop());
                        break;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                        while (pref(P.peek()) >= pref(E.peek())) {
                            S.push(P.pop());
                        }
                        P.push(E.pop());
                        break;
                    case 2:
                        while (!P.peek().equals("(")) {
                            S.push(P.pop());
                        }
                        P.pop();
                        E.pop();
                        break;
                    default:
                        S.push(E.pop());
                }
            }

            //Eliminacion de `impurezas´ en la expresiones algebraicas
            String postfix = S.toString().replaceAll("[\\]\\[,]", "");

            return postfix;

        } catch (Exception ex) {

        }
        return "";
    }

    //Jerarquia de los operadores
    private int pref(String op) {
        int prf = 99; //default
        if (op.equals("ln")) {
            prf = 13;
        }
        if (op.equals("csc")) {
            prf = 12;
        }
        if (op.equals("sec")) {
            prf = 11;
        }
        if (op.equals("cot")) {
            prf = 10;
        }
        if (op.equals("log")) {
            prf = 9;
        }
        if (op.equals("tan")) {
            prf = 8;
        }
        if (op.equals("cos")) {
            prf = 7;
        }
        if (op.equals("sin")) {
            prf = 6;
        }
        if (op.equals("^")) {
            prf = 5;
        }
        if (op.equals("*") || op.equals("/")) {
            prf = 4;
        }
        if (op.equals("+") || op.equals("-")) {
            prf = 3;
        }
        if (op.equals(")")) {
            prf = 2;
        }
        if (op.equals("(")) {
            prf = 1;
        }
        return prf;
    }

    //Depurar expresión algebraica

    private String depurar(String s) {
        s = s.replaceAll("\\s+", ""); //Elimina espacios en blanco
        s = "(" + s + ")";
        String simbols = "+-*/()^";
        String trig = "sincostanloglnseccsccot";
        String str = "";

        //Deja espacios entre operadores
        for (int i = 0; i < s.length(); i++) {
            if (simbols.contains("" + s.charAt(i))) {
                str += " " + s.charAt(i) + " ";

            } else {

                try {
                    if("ln".equals(""+s.charAt(i)+s.charAt(i+1))){
                         str += " " + s.charAt(i) + s.charAt(i + 1) +" ";
                         i++;
                    }else{
                        if (trig.contains("" + s.charAt(i) + s.charAt(i + 1) + s.charAt(i + 2))) {
                            str += " " + s.charAt(i) + s.charAt(i + 1) + s.charAt(i + 2) + " ";

                            i = i + 2;

                        } else {
                            str += s.charAt(i);

                        }
                    }
                } catch (Exception e) {
                    str += s.charAt(i);
                }
            }
        }
        return str.replaceAll("\\s+", " ").trim();
    }

}
