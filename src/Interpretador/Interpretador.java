
package Interpretador;

import java.util.Stack;

/**
 *
 * @author Javier
 */
public class Interpretador {
    
    private final String PI = "3.141592653589793";
    private final String e = "2.71828182846";

    String funcion;
     String funcionAux = "";
    int tipo = 3;
    int k = 5;

    /**
     * Constructor que inicializa las variables de función 
     */
    public Interpretador() {
        funcion = "";
    }

    /**
     *
     * @param funcion La función a evaluar
     */
    public Interpretador(String funcion) {
        setFuncion(funcion);
    }

    /**
     * Esta función establece si se usará redondeo, truncamiento o todos los digitos en las operaciones
     * de la expresión. Si se manda un valor que  no concuerde con las opciones, se tomará todos los digitos.
     * @param tipo aquí se establece el tipo de acuerdo a las siguientes opciones: 1.truncamiento 2.redondeo 3.Todos los digitos
     *  
     * 
     */
    public void setTipoValores(int tipo) {
        this.tipo = tipo;
    }

    /**
     *
     * @param k establece el valor del kernel. El valor por default es 7
     *
     */
    public void setK(int k) {
        this.k = k;
    }

    /**
     *
     * @param funcion establece la función a evaluar.
     */
    public void setFuncion(String funcion) {
        ToPostfix a = new ToPostfix(funcion);
        this.funcion = a.getPostfix();
        funcionAux = this.funcion;
    }

    /**
     *
     * @param x valor que será sustituido en todas las incognitas
     * @return retorna el resultado evaluado de la función con incognita.
     */
    public double getResultado(double x) {
        //Se reemplaza el valor de x, así como también valores de PI y e que existan en la expresión.
        funcionAux = funcion.replaceAll("x", ""+x); 
        return getResultado();
    }
    
    public double getResultado(){
        funcionAux = funcionAux.replaceAll("PI",PI);
        funcionAux = funcionAux.replaceAll("e",e);
        double resultado = 0;

        String[] post = funcionAux.split(" ");
        //Declaración de las pilas
        Stack< String> E = new Stack< String>(); //Pila entrada
        Stack< String> P = new Stack< String>(); //Pila de operandos
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
            } else {
                if (trigonometricos.contains("" + E.peek())) {

                    P.push(evaluar(E.pop(), P.pop()) + "");
                } else {

                    P.push(E.pop());
                }
            }
        }
        resultado = Double.parseDouble(P.peek());
        resultado = getValue(resultado);
        return resultado;
    }
    private double evaluar(String op, String n2, String n1) {
        double num1 = Double.parseDouble(n1);
        double num2 = Double.parseDouble(n2);

            if (op.equals("+")) {
                return (getValue(num1) + getValue(num2));
            }
            if (op.equals("-")) {
                return (getValue(num1) - getValue(num2));
            }
            if (op.equals("*")) {
                return (getValue(num1) * getValue(num2));
            }
            if (op.equals("/")) {
                return (getValue(num1) / getValue(num2));
            }
            if (op.equals("^")) {
                return getValue(Math.pow(getValue(num1), num2));
            }
        return 0;
    }

    private double evaluar(String op, String n1) {
        double num1 = Double.parseDouble(n1);
        if (op.equals("sin")) {
            return getValue(Math.sin(getValue(num1)));
        }
        if (op.equals("cos")) {
            return getValue(Math.cos(getValue(num1)));
        }
        if (op.equals("log")) {
            return getValue(Math.log10(getValue(num1)));
        }
        if (op.equals("ln")) {
            return getValue(Math.log(getValue(num1)));
        }
        if (op.equals("tan")) {
            return getValue(Math.tan(getValue(num1)));
        }
        if (op.equals("cot")) {
            return getValue(getValue(Math.cos(getValue(num1))) / getValue(Math.sin(getValue(num1))));
        }
        if (op.equals("csc")) {
            return getValue(1 / getValue(Math.sin(getValue(num1))));
        }
        if (op.equals("sec")) {
            return getValue(1 / getValue(Math.cos(getValue(num1))));
        }
        return 0;
    }

    /**
     *
     * @return regresa true si cumple con el formato del parentesis, false si no
     * lo cumple.
     */
    public boolean checarParentesis(String funcion) {
        Stack<String> aux = new Stack<String>();
        int i=0;
		while(i<funcion.length()){
			char caracter = funcion.charAt(i);
			if(caracter == '('){
				aux.push(caracter+"");
			}
			if(caracter == ')'){
				if(aux.isEmpty()){
					return false;
				}else{
					aux.pop();	
				}
				
			}
			i++;
		}
		if(aux.isEmpty())
			return true;
		return false;
    }

    /**
     * Función para truncar o redondear un número dependiendo de la variable
     * truncamiento.
     *
     * @param double numero, número que se quiere truncar
     *
     * @return double que representa el nuevo número truncado
     */
    public double getValue(double numero) {
        switch (tipo){
            case 1: 
                 if (numero > 0) {
                numero = Math.floor(numero * Math.pow(10, k)) / Math.pow(10, k);
            } else {
                numero = Math.ceil(numero * Math.pow(10, k)) / Math.pow(10, k);
            }
            return numero;
            case 2: 
                int cifras = (int) Math.pow(10, k);
            return Math.rint(numero * cifras) / cifras;
            case 3: 
            default:
                return numero;
               
        }
 
    }

}
