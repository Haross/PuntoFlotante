/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoFijo;

import Interpretador.Interpretador;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hugo
 */
public class StaticController implements Initializable {

    @FXML
    private Button btnCalcular;
    @FXML
    private TextField txtFuncion, txtP, txtTol, txtN;
    @FXML
    public TextArea txtArea;
    @FXML
    Interpretador interpretador;
  
     private Double f(double x) {
         System.out.println("############"+interpretador.getResultado(x));
        return interpretador.getResultado(x);
    }
     @FXML
    private void CalcularValores() {
        txtArea.setText("");
        int iteraciones = Integer.parseInt(txtN.getText());
        double P0 = Double.parseDouble(txtP.getText());
        double Tolerancia = Double.parseDouble(txtTol.getText());
        
        int contador = 1;
        
        double p;
        do {
            p =  f(P0);
            txtArea.setText(txtArea.getText()+"Iteracion " + contador
                    + "\n\t" + "\n\tF(p)= " + f(P0)+"\n\n");
            
            System.out.println("Iteracion: "+contador+"-------"+p+"------------");
          
            if (Math.abs(p - P0) <= Tolerancia) {
                System.out.println("-------------final------------------");
                System.out.println(p);
                System.out.println("-------------------------------");
                return;
            } else {
                
                contador++;
                P0 = p;
                
            
            }

        } while (contador <= iteraciones);

    }
    @FXML
    private void Borrar(ActionEvent e){
        txtFuncion.setText("");
        txtTol.setText("");
        txtN.setText("");
        txtArea.setText("");
        txtP.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        interpretador = new Interpretador();
        btnCalcular.setOnMousePressed((e)->{
            if(!interpretador.checarParentesis(txtFuncion.getText())){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Información");
                alert.setContentText("Error de sintaxis. Verifique que haya escrito la función correctamente");

                alert.showAndWait();
                            }else{
                interpretador.setFuncion(txtFuncion.getText());
                System.out.println("//////////////////////"+txtFuncion.getText());
            }
        
        
        });
    }

}
