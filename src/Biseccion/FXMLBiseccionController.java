/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Biseccion;

import Interpretador.Interpretador;
import java.math.BigDecimal;
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
     * Metodo que recibe el valor a evaluar en la funcion 
     * @param x recibe el valor a evaluar
     * @return retorna el resultado de la funcion
     */
    private double f(double x) {
        if(x<0){
            double x1 = x*-1;
            return interpretador.getResultado("!"+x1);
        }
       return interpretador.getResultado(x+"");
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
    
    /**
     * Metodo para obtener la K
    */
    private void getK(){
        if(txtK.getText() != ""){
            interpretador.setK(Integer.parseInt(txtK.getText()));
        }
    }
    
    /**
     * Metodo que selecciona el tipo operacion ya sea
     * truncamiento o redondeo
    */
    public void setType(){
        if(rbtnTruncamiento.isSelected()){
                interpretador.setTipoValores(1);
            }else{
                interpretador.setTipoValores(2);
            }
            getK();
    }
    /**
     * Metodo que obtiene los datos y realiza los calculos con el metodo de biseccion
     */
    @FXML
    public void calculoRaiz() {
        try{
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
                Interpretador in = new Interpretador();
                if(a<0 && b<0){
                    double a1 = a*-1;
                    double b1 = b *-1;
                    //Esto es igual a -a+(a+!b)/2 
                    in.setFuncion("!"+a1+"+("+a1+"+!"+b1+")/"+2);
                }else{
                    if(a<0){
                         double a1 = a*-1;
                        in.setFuncion("!"+a1+"+("+b+"+"+a1+")/"+2);
                    }else{
                        if(b<0){
                          double b1 = b *-1;
                           in.setFuncion(a+"+("+a+"+!"+b1+")/"+2); 
                        }else{
                             in.setFuncion(a+"+("+b+"-"+a+")/"+2);
                        }
                    }
                }



                p = in.getResultado();

               // p = interpretador.getValue(interpretador.getValue(a)+interpretador.getValue((interpretador.getValue(b)-interpretador.getValue(a))/2));
                double FP = f(p);

                txtArea.setText(txtArea.getText()+"Iteracion " + i
                        + "\n\t" + "P= " + interpretador.getFlotante(new BigDecimal(p))+
                        "\n\tF(p)= " + interpretador.getFlotante(new BigDecimal(f(p)))
                        +"\n\ta= " + interpretador.getFlotante(new BigDecimal(a))+
                        "\n\tb= " + interpretador.getFlotante(new BigDecimal(b))+
                        "\n\tF(a)= " +interpretador.getFlotante(new BigDecimal(f(a)))+"\n\n");

                System.out.println("-------Iteracion " + i + "--------");
                System.out.println("P= " + interpretador.getFlotante(new BigDecimal(p)));
                System.out.println("F(p)= " + interpretador.getFlotante(new BigDecimal(f(p))));
                System.out.println("a= " + interpretador.getFlotante(new BigDecimal(a)));
                System.out.println("b= " + interpretador.getFlotante(new BigDecimal(b)));
                System.out.println("F(a)= " + interpretador.getFlotante(new BigDecimal(f(a))));
                System.out.println("---------------------------");
                if(FP == 0 || (b-a)/2 < tol){
                    txtRaiz.setText(interpretador.getFlotante(new BigDecimal(p))+"");
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
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Información");
            alert.setContentText("Error de sintaxis. Verifique que haya escrito la función correctamente");

            alert.showAndWait();
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
