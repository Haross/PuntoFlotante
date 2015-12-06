package Interpretador;

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
        Stack< String> Infija = new Stack< String>(); //Pila entrada
        Stack< String> TempO = new Stack< String>(); //Pila temporal para operadores
        Stack< String> Postfija = new Stack< String>(); //Pila salida

        //Añadir la array a la Pila de entrada (E)
        for (int i = arrayInfix.length - 1; i >= 0; i--) {
            Infija.push(arrayInfix[i]);
        }
        try {
            //Algoritmo Infijo a Postfijo
            while (!Infija.isEmpty()) {
                switch (getJerarquia(Infija.peek())) {
                    case 1:
                        TempO.push(Infija.pop());
                        break;
                    case 2:
                        while (!TempO.peek().equals("(")) {
                            Postfija.push(TempO.pop());
                        }
                        TempO.pop();
                        Infija.pop();
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
                        while (getJerarquia(TempO.peek()) >= getJerarquia(Infija.peek())) {
                            Postfija.push(TempO.pop());
                        }
                        TempO.push(Infija.pop());
                        break;
                    
                    default:
                        Postfija.push(Infija.pop());
                }
            }
            String postfix = Postfija.toString().replaceAll("[\\]\\[,]", "");
            return postfix;

        } catch (Exception ex) {
        }
        return "";
    }

    //Jerarquia de los operadores
    private int getJerarquia(String op) {
        int jerarquia = 99;
        if (op.equals("ln")) {
            jerarquia = 13;
        }
        if (op.equals("csc")) {
            jerarquia = 12;
        }
        if (op.equals("sec")) {
            jerarquia = 11;
        }
        if (op.equals("cot")) {
            jerarquia = 10;
        }
        if (op.equals("log")) {
            jerarquia = 9;
        }
        if (op.equals("tan")) {
            jerarquia = 8;
        }
        if (op.equals("cos")) {
            jerarquia = 7;
        }
        if (op.equals("sin")) {
            jerarquia = 6;
        }
        if (op.equals("^")) {
            jerarquia = 5;
        }
        if (op.equals("*") || op.equals("/")) {
            jerarquia = 4;
        }
        if (op.equals("+") || op.equals("-")) {
            jerarquia = 3;
        }
        if (op.equals(")")) {
            jerarquia = 2;
        }
        if (op.equals("(")) {
            jerarquia = 1;
        }
        return jerarquia;
    }

    //Depurar expresión algebraica

    private String depurar(String s) {
        String expresion = "";
        s = s.replaceAll("\\s+", ""); 
        s = "(" + s + ")";
        String simbols = "+-*/()^";
        String trig = "sincostanloglnseccsccot";
        
        for (int i = 0; i < s.length(); i++) {
                    //Deja espacios entre operadores
            if (simbols.contains("" + s.charAt(i))) {
                expresion += " " + s.charAt(i) + " ";

            } else {

                try {
                            //Deja espacios entre operadores
                    if("ln".equals(""+s.charAt(i)+s.charAt(i+1))){
                         expresion += " " + s.charAt(i) + s.charAt(i + 1) +" ";
                         i++;
                    }else{
                        if (trig.contains("" + s.charAt(i) + s.charAt(i + 1) + s.charAt(i + 2))) {
                            expresion += " " + s.charAt(i) + s.charAt(i + 1) + s.charAt(i + 2) + " ";

                            i = i + 2;

                        } else {
                            expresion += s.charAt(i);

                        }
                    }
                } catch (Exception e) {
                    expresion += s.charAt(i);
                }
            }
        }
        return expresion.replaceAll("\\s+", " ").trim();
    }

}
