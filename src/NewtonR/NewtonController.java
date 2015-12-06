/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NewtonR;

import Interpretador.Interpretador;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ERIDE21
 */
public class NewtonController implements Initializable {

    @FXML
    private Button btnCalcular;
    @FXML
    private TextField txtFuncion, txtFuncionD, txtP0, txtTol, txtN, txtRaiz;
    @FXML
    private TextArea txtArea;
    @FXML
    private Label lblArea;
    Interpretador interpretador;
    Interpretador interpretadorD;
    double p;

    private double f(double x) {
       return interpretador.getResultado(x);
    }

    /**
     *
     */
    @FXML
    public void calculoRaiz() {
         //Primero limpiamos el text area para eliminar lo que se calculo anteriormente.
        txtArea.setText("");
        double tol = Double.parseDouble(txtTol.getText());
        int n = Integer.parseInt(txtN.getText());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        interpretador = new Interpretador();
        btnCalcular.setOnMousePressed((e)->{
            if(!interpretador.checarParentesis(txtFuncion.getText()) && !interpretadorD.checarParentesis(txtFuncionD.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Información");
                alert.setContentText("Error de sintaxis. Verifique que haya escrito la función correctamente");

                alert.showAndWait();
            }else{
                interpretador.setFuncion(txtFuncion.getText());
                interpretadorD.setFuncion(txtFuncionD.getText());
            }
        
        
        });
    }    
    
}
