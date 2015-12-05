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
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("introduzca ecuacion");
        String b = input.nextLine();
        Interpretador a = new Interpretador(b);
        System.out.println(a.getResultado(2));
    }

    public Interpretador(String funcion) {
        setFuncion(funcion);
    }

    public void setFuncion(String funcion) {
       ToPostfix a = new ToPostfix(funcion);
       this.funcion = a.getPostfix();
    }

    public double getResultado(double x) {
        double resultado = 0;
        
        String[] post = funcion.split(" "); 
        //Declaración de las pilas
    Stack < String > E = new Stack < String > (); //Pila entrada
    Stack < String > P = new Stack < String > (); //Pila de operandos
    //Añadir post (array) a la Pila de entrada (E)
    for (int i = post.length - 1; i >= 0; i--) {
      E.push(post[i]);
    }
    
    //Algoritmo de Evaluación Postfija
    String operadores = "+-*/^"; 
    String trigonometricos = "tansincosloglncscseccot";
    while (!E.isEmpty()) {
      if (operadores.contains("" + E.peek())) {
             P.push(evaluar(E.pop(), P.pop(), P.pop()) + "");
      }else {
        if(trigonometricos.contains("" + E.peek())){
           
            P.push(evaluar(E.pop(),P.pop())+"");
        }else{
           
        P.push(E.pop());
    }
      } 
    }
    resultado = Double.parseDouble(P.peek());
   
        return resultado;
    }
  private double evaluar(String op, String n2, String n1) {
    double num1 = Double.parseDouble(n1);
    double num2 = Double.parseDouble(n2);
    if (op.equals("+")) return (num1 + num2);
    if (op.equals("-")) return (num1 - num2);
    if (op.equals("*")) return (num1 * num2);
    if (op.equals("/")) return (num1 / num2);
    if(op.equals("^"))  return Math.pow(num1,num2); 
    return 0;
  }
  private double evaluar(String op, String n1) {
    double num1 = Double.parseDouble(n1);
    if(op.equals("sin"))  return Math.sin(num1); 
    if(op.equals("cos"))  return Math.cos(num1); 
    if(op.equals("log"))  return Math.log10(num1); 
    if(op.equals("ln"))  return Math.log(num1); 
    if(op.equals("tan"))  return Math.tan(num1); 
    if(op.equals("cot"))  return Math.cos(num1)/Math.sin(num1); 
    if(op.equals("csc"))  return 1/Math.sin(num1); 
    if(op.equals("sec"))  return 1/Math.cos(num1); 
    return 0;
  }
    
    

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
