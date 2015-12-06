
package Interpretador;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
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
    ArrayList expresiones = new ArrayList();
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
        expresiones = new ArrayList();
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
    
    public ArrayList getOperaciones(){
        return expresiones;
    }
    
    private double evaluar(String op, String n2, String n1) {

        BigDecimal num1 = new BigDecimal(n1);
        BigDecimal num2 = new BigDecimal(n2);
        num1 = getValue(num1);
        num2 = getValue(num2);
        BigDecimal operacion = new BigDecimal(0);

            if (op.equals("+")) {
                operacion = num1.add(num2);
                expresiones.add(num1+" + "+num2 +" = " + getValue(operacion) );
            }
            if (op.equals("-")) {
                operacion = num1.subtract(num2); 
                expresiones.add(num1+" - "+num2 +" = " + getValue(operacion));
            }
            if (op.equals("*")) {
                operacion = num1.multiply(num2);
                expresiones.add(num1+" * "+num2 +" = " + getValue(operacion) );
            }
            if (op.equals("/")) {
                MathContext mc = new MathContext(k, RoundingMode.DOWN);
                if(tipo == 2){
                     mc = new MathContext(k,  RoundingMode.UP);
                   
                }
               operacion = num1.divide(num2, mc);
                //double oper = getValue(num1.doubleValue()/num2.doubleValue());
                expresiones.add(num1+" / "+num2 +" = " + getValue(operacion) );
            }
            if (op.equals("^")) {
                operacion = num1.pow(num2.intValue());
                expresiones.add(num1+"^"+num2 +" = " + getValue(operacion) );
            }
       return operacion.doubleValue();
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
     * @param numero, número que se quiere truncar
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
    /**
     * Función para truncar o redondear un número dependiendo de la variable
     * truncamiento.
     *
     * @param numero, número que se quiere truncar
     *
     * @return numero regresa un numero en BigDecimal.
     */
    public BigDecimal getValue(BigDecimal numero) {
        switch (tipo){
            case 1: 
            return numero.setScale(k, RoundingMode.DOWN);
            case 2: 
                
                return numero.setScale(k, BigDecimal.ROUND_HALF_UP);
            case 3: 
            default:
                return numero;
               
        }
 
    }


}
