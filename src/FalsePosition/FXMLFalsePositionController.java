/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FalsePosition;

import Interpretador.Interpretador;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author W8
 */
public class FXMLFalsePositionController implements Initializable {

    @FXML
    private Button btnCalcular;
    @FXML
    private TextField txtP0, txtP1, txtFuncion, txtTol, txtRaiz, txtN, txtK;
    @FXML
    private RadioButton rbtnTruncamiento, rbtnRedondeo;
    @FXML
    private TextArea txtArea;
    Interpretador interpretador = new Interpretador();
    Interpretador in = new Interpretador();
    private final String PI = "3.141592653589793";
    
    @FXML private void Borrar(ActionEvent e){
        txtP0.setText("");
        txtP1.setText("");
        txtFuncion.setText("");
        txtTol.setText("");
        txtRaiz.setText("");
        txtN.setText("");
        txtK.setText("");
    }
    
    
    
     /**
     *este metodo nos permite corroborar que la funcion introducida por el usuario esta bien escrita 
     * en caso contrario nos manda un alerta y si esta bien escrita llama nuestro interprete y convierte esa ecuacion 
     * de tipo String a una ecuacion logica para la computadora 
     */
     /**
     * Metodo para obtener la K
    */
    private void getK(){
        if(!"".equals(txtK.getText())){
            interpretador.setK(Integer.parseInt(txtK.getText()));
             in.setK(Integer.parseInt(txtK.getText()));
        }
    }
    
    /**
     * Metodo que seleccion el tipo operacion que se efecturara
    */
    public void setType(){
        if(rbtnTruncamiento.isSelected()){
                interpretador.setTipoValores(1);
                in.setTipoValores(1);
        }else{
                interpretador.setTipoValores(2);
                in.setTipoValores(2);
        }
       getK();
    }
    
    /**
     * Este metodo nos toma el valor de P0 y con ayuda del interprete lo hace un valor logico para la computadora 
     * @return el valor de P0 
     */
     public double getValueP0(){
        Interpretador in = new Interpretador();
        String aux = txtP0.getText().replace("-","!");
        in.setFuncion(aux);
        return in.getResultado();
    }
    
    /**
     * Este metodo nos toma el valor de P1 y con ayuda del interprete lo hace un valor logico para la computadora 
     * @return el valor de P1 
     */
    private double getValueP1(){
        Interpretador in = new Interpretador();
        String aux = txtP1.getText().replace("-","!");
        in.setFuncion(aux);
        return in.getResultado();
    }
    /*
    * Este metodo establece la función con la cual funcionará el interprete
    */
    private void setFuncion(){
        if (!interpretador.checarParentesis(txtFuncion.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Información");
                alert.setContentText("Error de sintaxis. Verifique que haya escrito la función correctamente");

                alert.showAndWait();
            } else {
                interpretador.setFuncion(txtFuncion.getText());
            }
    }
    /*
    * Este método calcula el valor de la raiz con el algoritmo de false position
    */
    @FXML
    private void calculoRaiz() {
        
       try{ 
           setType();
           txtArea.setText("");
        double p0 = getValueP0();
        double p1 = getValueP1();
        setFuncion();
        double tol = Double.parseDouble(txtTol.getText());
        int n = Integer.parseInt(txtN.getText());
        int i = 2;
        double q0 = f(p0);
        double q1 = f(p1);
        while (i <= n) {
           String ecuacion =  "p1 - q1 * (p1 - p0) / (q1 - q0)";
                if(p1<0){
                    String aux = p1+"";
                    aux = aux.replace("-", "!");              
                    ecuacion = ecuacion.replace("p1",aux );
                }else{
                     ecuacion = ecuacion.replace("p1",p1+"" );
                }

                if(p0<0){
                    String aux = p0+"";
                    aux = aux.replace("-", "!");              
                    ecuacion = ecuacion.replace("p0",aux );
                }else{
                     ecuacion = ecuacion.replace("p0",p0+"" );
                }

                if(q1<0){
                    String aux = q1+"";
                    aux = aux.replace("-", "!");              
                    ecuacion = ecuacion.replace("q1",aux );
                }else{
                     ecuacion = ecuacion.replace("q1",q1+"" );
                }
                if(q0<0){
                    String aux = q0+"";
                    aux = aux.replace("-", "!");              
                    ecuacion = ecuacion.replace("q0",aux );
                }else{
                     ecuacion = ecuacion.replace("q0",q0+"" );
                }
                in.setFuncion(ecuacion);
                double p = in.getResultado();

            int aux = i-1;
            
            txtArea.setText(txtArea.getText()+"Iteracion " + aux
                    + "\n\t" + "P= " + p+"\n\tF(p)= " + f(p)+"\n\n");
            
            if (Math.abs(p - p1) < tol) {
                txtRaiz.setText(p + "");
                return;
            }
            i++;
            double q = f(p);
            if (q * q1 < 0) {
                p0 = p1;
                q0 = q1;
            }
            p1 = p;
            q1 = q;
        }
       }catch(Exception e){
           Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Información");
                alert.setContentText("Error de sintaxis. Verifique que haya escrito la función correctamente");

                alert.showAndWait();
       }
    }

   private double f(double x) {
        return interpretador.getResultado(x);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}
