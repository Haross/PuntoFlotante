/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FalsePosition;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author W8
 */
public class FXMLFalsePositionController implements Initializable {

    @FXML private Button Calcular;
    @FXML private TextField txtP0, txtP1, txtFuncion, txtTol, txtRaiz, txtN;
    @FXML private TextArea txtArea;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
