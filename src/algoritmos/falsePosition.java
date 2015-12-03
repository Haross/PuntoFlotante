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
public class falsePosition {
    double  q0,q1;
    double p, p0, p1;
    falsePosition(){

        q0 = 0;
        q1 = 0;
        p = 0;
        p1 = 0;
        p0 = 0;
    }
    falsePosition(double p0, double p1){
        q0 = p0;
        q1 = p1;
        this.p1 = p1;
        this.p0 = p0;
        p = 0;
    }
    public void setP1(double p1){
         this.p1 = p1;
          q1 = p1;
    }
    public void setP0(double p0){
         this.p0 = p0;
          q0 = p0;
    }
    public double calculoRaiz(double TOL, int N){
        int i = 2;
        while (i<= N){
        p = p1 -q1*(p1-p0)/(q1-q0);
        if(p-p1 < TOL){
            return p;
        }
        i++;
        double q = funcion(p);
        if(q*q1 <0){
            p0 = p1;
            q0 = q1;
        }
        p1 = p;
        q1 = q;
        }
        return -0.000001;
    }
    private double funcion(double p){
        return p;
    }
    public void setFuncion(String f){
        
    }
}
