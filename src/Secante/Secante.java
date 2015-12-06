/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Secante;

/**
 *
 * @author Javier
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
     *
     * @param p1
     */
    public void setP1(double p1){
         this.p1 = p1;
          q1 = p1;
    }
    
    /**
     *
     * @param p0
     */
    public void setP0(double p0){
         this.p0 = p0;
          q0 = p0;
    }
    
    /**
     *
     * @param TOL
     * @param N
     * @return
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
     * @param f
     */
    public void setFuncion(String f){
        
    }
    
    
    
    
}
