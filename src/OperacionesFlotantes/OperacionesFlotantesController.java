/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OperacionesFlotantes;

import Interpretador.Interpretador;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
 * @author Javier
 */
public class OperacionesFlotantesController implements Initializable {

    @FXML
    private Button btnCalcular;
    @FXML
    private TextField txtFuncion, txtK, txtResultado;
    @FXML
    private TextArea txtArea;
    @FXML 
    private RadioButton truncamiento, redondeo; 
    Interpretador interprete;
    
    private void getK(){
        if(txtK.getText() != ""){
            interprete.setK(Integer.parseInt(txtK.getText()));
        }
    }
    private void setOperaciones(){
        txtArea.setText("");
        ArrayList aux = interprete.getOperaciones();
        int i = 0;
        while(i < aux.size()){
            txtArea.setText(txtArea.getText() + "\n"+aux.get(i++));
        }
            
    }

    @FXML
    private void calcular() {
        
        if (interprete.checarParentesis(txtFuncion.getText())) {
            interprete.setFuncion(txtFuncion.getText());
            if(truncamiento.isSelected()){
                interprete.setTipoValores(1);
            }else{
                interprete.setTipoValores(2);
            }
            getK();
            txtResultado.setText(interprete.getResultado()+"");
            setOperaciones();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Información");
            alert.setContentText("Error de sintaxis. Verifique que haya escrito la función correctamente");

            alert.showAndWait();
            
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtResultado.setEditable(false);
        interprete = new Interpretador();

    }

}
