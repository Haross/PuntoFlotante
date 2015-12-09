
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
    private final String E = "*10^";
    String funcion;
    String funcionAux = "";
    ArrayList expresiones = new ArrayList();
    int tipo = 3;
    int k = 10;

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
     * @param k establece el valor de la mantisa. El valor por default es 7
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
        funcion = funcion.replace("E", E);
        funcion = funcion.replace("^-","^!");
        
        funcion = funcion.replace("!sin","!1*sin");
        funcion = funcion.replace("!cos","!1*cos");
        funcion = funcion.replace("!tan","!1*tan");
        funcion = funcion.replace("!cot","!1*cot");
        funcion = funcion.replace("!csc","!1*csc");
        funcion = funcion.replace("!sec","!1*sec");
        funcion = funcion.replace("!ln","!1*ln");
        funcion = funcion.replace("!log","!1*log");
        
        ToPostfix a = new ToPostfix(funcion);
        this.funcion = a.getPostfix();
        funcionAux = this.funcion;
    }

 
    /**
     *
     * @param x valor que será sustituido en todas las incognitas
     * @return retorna el resultado evaluado de la función con incognita.
     */
    public double getResultado(String x) {
        //Se reemplaza el valor de x, así como también valores de PI y e que existan en la expresión.
        funcionAux = funcion.replaceAll("x",x); 
        return getResultado();
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
    
    /**
     * Asigna las variables “PI y e” 
     * con sus valores numéricos correspondientes.
     */
    public void setConstantes(){
        expresiones = new ArrayList();
        funcionAux = funcionAux.replaceAll("PI",PI);
        funcionAux = funcionAux.replaceAll("e",e);
    }
    
    /**
     * Metodo que evalua la funcion 
     * @return retorna el resultado del resultado de la funcion
     */
    public double getResultado(){
        setConstantes();
        double resultado = 0;
        
        
        
        String[] operaciones = funcionAux.split(" ");

        Stack< String> EntradaO = new Stack< String>(); //Entrada de datos
        Stack< String> Operandos = new Stack< String>(); //Operandos

        
        for (int i = operaciones.length - 1; i >= 0; i--) {
            EntradaO.push(operaciones[i]);
        }

        String operadores = "+-*/^";
        String trigonometricos = "tansincosloglncscseccot";
        System.out.println(EntradaO);
        while (!EntradaO.isEmpty()) {
            if(EntradaO.peek().contains("!")){
    
                Operandos.push("-"+EntradaO.pop().replace("!",""));
            }else{
            if (operadores.contains("" + EntradaO.peek())) {
                Operandos.push(evaluar(EntradaO.pop(), Operandos.pop(), Operandos.pop()) + "");
            } else {
                if (trigonometricos.contains("" + EntradaO.peek())) {
                    Operandos.push(evaluar(EntradaO.pop(), Operandos.pop()) + "");
                } else {
                    Operandos.push(EntradaO.pop());
                }
            }}
        }
        resultado = Double.parseDouble(Operandos.peek());
        return resultado;
    }

    /**
     * Guarda los resultado finales de cada operacion
     * @return retorna un arraylist con los resultados
     */
    public ArrayList getOperaciones(){
        return expresiones;
    }
    
    /**
     * Metodo que evalua operaciones aritmeticas
     * @param op se refiere a los operandos (+,-,*,/,^)
     * @param n2 se refiere al operador
     * @param n1 se refiere al operador
     * @return retorna el resultado de la operacion
     */
    private double evaluar(String op, String n2, String n1) {
        BigDecimal num1 = new BigDecimal(n1);
        BigDecimal num2 = new BigDecimal(n2);
        System.out.println("start getV");
        num1 = getValue(num1);
        num2 = getValue(num2);
        System.out.println("finish getV");
        BigDecimal operacion = new BigDecimal(0);

        switch(op){
            case "+":
                operacion = num1.add(num2);
                
                System.out.println("operaciones1" +operacion);
                expresiones.add(getFlotante(new BigDecimal(n1))+" + "+getFlotante(new BigDecimal(n2)) +" = " + getFlotante(operacion) );
                break;
            case "-":
                operacion = num1.subtract(num2); 
                expresiones.add(getFlotante(new BigDecimal(n1))+" - "+getFlotante(new BigDecimal(n2)) +" = " + getFlotante(operacion) );
                break;
            case "*":
                operacion = num1.multiply(num2);
                expresiones.add(getFlotante(new BigDecimal(n1))+" * "+getFlotante(new BigDecimal(n2)) +" = " + getFlotante(operacion) );
                break;   
            case "/":
                MathContext mc = new MathContext(k, RoundingMode.DOWN);
                if(tipo == 2){
                     mc = new MathContext(k,  RoundingMode.HALF_EVEN);
                   
                }
               operacion = num1.divide(num2, mc);
                expresiones.add(getFlotante(new BigDecimal(n1))+" / "+getFlotante(new BigDecimal(n2)) +" = " + getFlotante(operacion) );
                break;
            case "^":
                double oper = getValue(Math.pow(num1.doubleValue(),num2.doubleValue())); 
   
                //operacion = num1.pow(num2.intValue());
                expresiones.add(getFlotante(new BigDecimal(n1))+" ^ "+num2 +" = " + getFlotante(operacion) );
                return oper;
                      
        }
       return operacion.doubleValue();
    }
    
    /**
     * Metodo que evalua operaciones trigonometricas
     * @param op se refiere a las funciones:
     *  sin,cos,log,ln,tan, csc, cot, sec
     * @param n1 operador a evaluar
     * @return retorna el resultado de la operacion
     */
    private double evaluar(String op, String n1) {
        double num1 = Double.parseDouble(n1);
        switch(op){
            case "sin":
                return getValue(Math.sin(getValue(num1)));
            case "cos":
                return getValue(Math.cos(getValue(num1)));
            case "log":
                 return getValue(Math.log10(getValue(num1)));
            case "ln":
                return getValue(Math.log(getValue(num1)));
            case "tan":
                return getValue(Math.tan(getValue(num1)));
            case "cot":
                return getValue(getValue(Math.cos(getValue(num1))) / getValue(Math.sin(getValue(num1))));
            case "csc":
                 return getValue(1 / getValue(Math.sin(getValue(num1))));
            case "sec":
                return getValue(1 / getValue(Math.cos(getValue(num1))));
                
        }
        return 0;
    }

    /**
     * 
     * @param funcion
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
     * Sirve para operaciones trigonometricas
     * @param numero, número que se quiere truncar
     *
     * @return double que representa el nuevo número truncado
     */
    public double getValue(double numero) {
        BigDecimal a = new BigDecimal(numero+"");
        return getValue(a).doubleValue();

 
    }
    /**
     * Función para truncar o redondear un número dependiendo de la variable
     * truncamiento.
     * Sirve para operaciones aritmeticas
     * @param numero, número que se quiere truncar
     *
     * @return numero regresa un numero en BigDecimal.
     */
    public BigDecimal getValue(BigDecimal numero) {
        switch (tipo){
            case 1: 
                String ec =  getFlotante(numero);
                String[] ecs = ec.split("E");
                System.out.println("splitE");
                if(ecs.length == 2){
                   //numero = new BigDecimal(evaluar("*", ecs[0],ecs[1])); 
                    System.out.println(ecs[0]+" "+ecs[1]);
                    BigDecimal nume1 = new BigDecimal(ecs[0]);
                    BigDecimal nume2 = new BigDecimal(ecs[1]);
                    
                   numero = nume1.multiply( new BigDecimal(   Math.pow((double) 10,nume2.doubleValue())  ));
                    System.out.println(numero);
                    System.out.println("calculo");
                   return numero;
                }
                
            return numero.setScale(k, RoundingMode.DOWN);
            case 2: 
                 String ec2 =  getFlotante(numero);
                String[] ecs2 = ec2.split("E");
                if(ecs2.length == 2){
                   //numero = new BigDecimal(evaluar("*", ecs2[0],ecs2[1])); 
                  BigDecimal nume1 = new BigDecimal(ecs2[0]);
                    BigDecimal nume2 = new BigDecimal(ecs2[1]);
                  numero = nume1.multiply( new BigDecimal(   Math.pow((double) 10,nume2.doubleValue())  ));
                    System.out.println("calculo redondeo");
                   return numero;
                }
                return numero.setScale(k, BigDecimal.ROUND_HALF_UP);
            case 3: 
            default:
                return numero;
               
        }
 
    }
    

    
    
    public String getFlotante(BigDecimal a){
        	System.out.println("start getFlotante");
                
		int cont = 0;
		String  ec = a+"";
		if(a.doubleValue() != 0){
                    System.out.println("if 0");
			boolean band = true;
			String[] au1x = ec.split("\\.");
			
	while(band){
            System.out.println("while");
            System.out.println(a);
		String[] aux = ec.split("\\.");

		if(aux.length == 2){
					
			if(aux[1].matches("0.*")&& aux[0].matches("0")){
                            System.out.println("matches 0.*");
				if(a.doubleValue() <0.1){
					System.out.println("mul");
					a = a.multiply(new BigDecimal(10));
					ec = a+"";
					cont--;
				}
			}else{
				if(aux[0].matches("0") ||aux[0].matches("-0"))
				band = false;
				else{
					System.out.println("div");
				a = a.divide(new BigDecimal(10));
				cont++;
				System.out.println("matches 0.* ending else");
					ec = a+"";
					
				}
					
			}

			
		}else{
			if(a.doubleValue()>1){
				System.out.println("div");
				a = a.divide(new BigDecimal(10));
				cont++;
			}else{
				band = false;
			}
			
		}
	

	}}
	//System.out.println(a+"E"+cont);
        //System.out.println(getFk(a));
        String ecuacion = getFk(a)+"E"+cont;
         ecuacion = ecuacion.replace("E0", " ");
        //System.out.println(ecuacion +"ex");
        return ecuacion;

	}
    
    
    public BigDecimal getFk( BigDecimal numero){
        System.out.println("start getFK");
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
