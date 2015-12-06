/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Secante;

import Interpretador.Interpretador;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Diana
 */
public class FXMLSecanteController {
    
    @FXML
    private Button btnCalcular;
    @FXML
    private TextField txtP0, txtP1, txtFuncion, txtTol, txtRaiz, txtN;
    @FXML
    private TextArea txtArea;
    Interpretador interpretador = new Interpretador();
    private final String PI = "3.141592653589793";
    
    
     private double getValueP0(){
        Interpretador inter = new Interpretador();
        inter.setFuncion(txtP0.getText());
        return inter.getResultado();
    }
     
    private double getValueP1(){
        Interpretador inter = new Interpretador();
        inter.setFuncion(txtP1.getText());
        return inter.getResultado();
    }
   public void setFuncion(){
       if (!interpretador.checarParentesis(txtFuncion.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Información");
                alert.setContentText("Error de sintaxis. Verifique que haya escrito la función correctamente");

                alert.showAndWait();
            } else {
                interpretador.setFuncion(txtFuncion.getText());
            }
   }
    
    
    @FXML
    private void calculoRaiz() {
        txtArea.setText("");
        double p0 = getValueP0();
        double p1 = getValueP1();
        setFuncion();
        double tol = Double.parseDouble(txtTol.getText());
        int N = Integer.parseInt(txtN.getText());
        int i = 2;
        double q0 = f(p0);
        double q1 = f(p1);
        System.out.println("vacia. "+ q0);
        while (i <= N) {
            double p = p1 - q1 * (p1 - p0) / (q1 - q0);
            int aux = i-1;
            
            txtArea.setText(txtArea.getText()+"Iteracion " + aux
                    + "\n\t" + "P= " + p+"\n\tF(p)= " + f(p)+"\n\n");
            
            if (Math.abs(p - p1) < tol) {
                txtRaiz.setText(p + "");
                return;
            }
            i= i + 1;
            double fp = f(p);
            
            p0 = p1;
            q0 = q1;
            p1 = p;
            q1 = fp;
        }
    }

   private double f(double x) {
        return interpretador.getResultado(x);
    }

    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
}
