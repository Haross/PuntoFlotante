/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Secante;

/**
 *
 * @author Diana
 */
public class Secante {
    
    int i;
    double q0,q1,p0,p1,p, fp;
    
    Secante(){
       i=0; 
       q0= 0;
       q1= 0;
       p0= 0;
       p1= 0;
       p= 0;
       
    }
    
     Secante(double p0, double p1){
        q0 = p0;
        q1 = p1;
        this.p1 = p1;
        this.p0 = p0;
        p = 0;
    }
     
    /**
     *este metodo nos sirve para hacer asiganacion de p1 en q1
     * @param p1 es nuestro segundo valor introducido por el usuario
     * 
     */
    public void setP1(double p1){
         this.p1 = p1;
          q1 = p1;
    }
    
    /**
     *este metodo nos sirve para hacer asiganacion de p0 en q0
     * @param p0 es nuestro primer valor introducido por el usuario
     */
    public void setP0(double p0){
         this.p0 = p0;
          q0 = p0;
    }
    
    /**
     *este es el metodo principal en el cual se realiza el calculo de raices con el metodo de la secante 
     * para ello se calcula el valor de p y de ahi se verifica si se cumple la condicion de la Tolerancia o no 
     * luego se procede a hacer asignaciones correspondientes
     * @param TOL esta es la variable de la Tolerancia que nos ayuda como variable de paro 
     * @param N esta variable nos indica el numero de iteraciones 
     * @return este metodo nos retorna el valor de p en donde se cumpla la condicion de paro o hasta el 
     * numero de iteraciones indicadas
     */
    public double calculoRaiz(double TOL, int N){
        int i = 2;
        while (i<= N){
            p = p1 -q1*(p1-p0)/(q1-q0);
            if(p-p1 < TOL){
                return p;
            }
            i= i+1;
            
            p0 = p1;
            q0= q1;
            p1 = p;
            q1 = fp;
        }
        return p;
    }
     
    
    private double funcion(double p){
        return p;
    }
    
    /**
     *
     * @param f es el string que contiene la funcion introducida por el usuario
     */
    public void setFuncion(String f){
        
    }
    
    
    
    
}
