/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoFlotante;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Javier
 */
public class FXMLDocumentController2 implements Initializable {
    
    private void openWindowWithOption(String file) {
        Stage stage = new Stage();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource(file));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController2.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }
    
    @FXML private void Biseccion(){
        openWindowWithOption("/Biseccion/FXMLBiseccion.fxml");
    }
    
    @FXML private void FalsePosition(){
       openWindowWithOption("/FalsePosition/FXMLFalsePosition.fxml");
    }
    
    @FXML private void NewtonR(){
       
    }
    
    @FXML private void PuntoFijo(){
       
    }
    
    @FXML private void Secante(){
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
