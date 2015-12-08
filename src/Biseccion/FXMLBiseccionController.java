/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Biseccion;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author W8
 */
public class FXMLBiseccionController implements Initializable {

    @FXML
    private Button btnCalcular;
    @FXML
    private TextField txtFuncion, txtA, txtB, txtTol, txtN, txtRaiz, txtK;
    @FXML
    private TextArea txtArea;
    @FXML
    private RadioButton rbtnTruncamiento, rbtnRedondeo;
    Interpretador interpretador;
    
    double p;

    /**
     * Metodo que recibe la funcion y realiza los calculos
     * @param x recibe el valor de x
     * @return retorna el resultado de la funcion
     */
    private double f(double x) {
       return interpretador.getResultado(x);
    }
    
    /**
     * Metodo que limpia los componentes
     * @param e 
     */
    @FXML
    private void Borrar(ActionEvent e){
        txtFuncion.setText("");
        txtA.setText("");
        txtB.setText("");
        txtTol.setText("");
        txtN.setText("");
        txtRaiz.setText("");
        txtK.setText("");
        txtArea.setText("");
    }
    private void getK(){
        if(txtK.getText() != ""){
            interpretador.setK(Integer.parseInt(txtK.getText()));
        }
    }
    public void setType(){
        if(rbtnTruncamiento.isSelected()){
                interpretador.setTipoValores(1);
            }else{
                interpretador.setTipoValores(2);
            }
            getK();
    }
    /**
     * Metodo que obtiene los datos y realiza los calculos
     */
    @FXML
    public void calculoRaiz() {
        setType();
         //Primero limpiamos el text area para eliminar lo que se calculo anteriormente.
            txtArea.setText("");
        double a = Double.parseDouble(txtA.getText());
        double b = Double.parseDouble(txtB.getText());
        double tol = Double.parseDouble(txtTol.getText());
        int n = Integer.parseInt(txtN.getText());
        double p = a;
        int i = 1;
        double FA = f(a);
        while(i <= n){
            p = interpretador.getValue(interpretador.getValue(a)+interpretador.getValue((interpretador.getValue(b)-interpretador.getValue(a))/2));
            double FP = f(p);
            
            txtArea.setText(txtArea.getText()+"Iteracion " + i
                    + "\n\t" + "P= " + p+"\n\tF(p)= " + f(p)+"\n\ta= " + a+"\n\tb= " + b+
                    "\n\tF(a)= " + f(a)+"\n\n");
            
            System.out.println("-------Iteracion " + i + "--------");
            System.out.println("P= " + p);
            System.out.println("F(p)= " + f(p));
            System.out.println("a= " + a);
            System.out.println("b= " + b);
            System.out.println("F(a)= " + f(a));
            System.out.println("---------------------------");
            if(FP == 0 || (b-a)/2 < tol){
                txtRaiz.setText(p+"");
                return;
            }
            //Esta condición se puso por si se quiere obtener el valor de una P especifica
            if(n == i){
                txtRaiz.setText(p+"");
                return;
            }
            i = i+1;
            if(FA*FP > 0){
                a = p;
                FA = FP;
            }else{
                b = p;
            }
        }
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
            }
        
        
        });
    }    
    
}
