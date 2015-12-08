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
import javafx.scene.control.RadioButton;
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
    private TextField txtFuncion, txtP, txtTol, txtN,txtK,txtRe;
    @FXML
    public TextArea txtArea;
    @FXML
    Interpretador interpretador;
     private RadioButton rbtnTruncamiento, rbtnRedondeo;
     
     /**
     * Metodo que obtiene el valor de la K
     * @param e
     * 
     */
  
    private void getK(){
        if(txtK.getText()!= ""){
            interpretador.setK(Integer.parseInt(txtK.getText()));
        }
    }
    
    /**
     * Metodo que identifica si se ha seleccionado redondeo o truncamiento
     *  
     * @param e
     */
    public void setType(){
        if(rbtnTruncamiento.isSelected()){
                interpretador.setTipoValores(1);
            }else{
                interpretador.setTipoValores(2);
            }
            getK();
    }
     
  
     private double f(double x) {
        if(x<0){
            double x1 = x*-1;
            return interpretador.getResultado("!"+x1);
        }
       return interpretador.getResultado(x+"");
    }
     
     /**
     * Metodo que  realiza los calculos del algoritmo
     * Evalua el valor ingreado de P(0) en la función 
     * @param e
     */
  
    @FXML
    private void CalcularValores() {
        txtArea.setText("");//limpiamos el textArea
       
        int iteraciones = Integer.parseInt(txtN.getText());//se obtinen las iteraciones
        double P0 = Double.parseDouble(txtP.getText());//se obtiene P0
        double Tolerancia = Double.parseDouble(txtTol.getText());//se obtiene la tolerancia
        
        int contador = 1; //incializamos el contador en 1
        
        double p;
        do {
            p =  f(P0);//P obtine se valor de P0 evaluado en la funcion
            txtArea.setText(txtArea.getText()+"Iteracion " + contador//imprimimos los resultados en el textArea
                    + "\n\t" + "\n\tF(p)= " + f(P0)+"\n\n");
            
            System.out.println("Iteracion: "+contador+"-------"+p+"------------");
          
            if (Math.abs(p - P0) <= Tolerancia) {//se evalua la condicion que determina el fin del Programa
                txtRe.setText(p+"");
                System.out.println("-------------final------------------");
                System.out.println(p);
                System.out.println("-------------------------------");
                return;
            } else {
                
                contador++;
                P0 = p;// se sustituye el valor de P con el obtenido de previamete en P
                
            
            }

        } while (contador <= iteraciones);

    }
    /**
     * Metodo que se encarga de limpiar los campos
     * @param e
     * 
     */
  
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
