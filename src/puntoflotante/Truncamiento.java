/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoFlotante;

/**
 *
 * @author LosPro
 */
public class Truncamiento {

    /**
     * Función para truncar un número decimal
     *
     * @param nD
     * @param nDec
     * @return double que representa el nuevo número truncado
     */
    public double getTruncamiento(double nD, int nDec) {
        if (nD > 0) {
            nD = Math.floor(nD * Math.pow(10, nDec)) / Math.pow(10, nDec);
        } else {
            nD = Math.ceil(nD * Math.pow(10, nDec)) / Math.pow(10, nDec);
        }
        return nD;
    }
}
