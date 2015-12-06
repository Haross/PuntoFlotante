/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoFlotante;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Javier
 */
public class FXMLDocumentController implements Initializable {
    @FXML 
    private Button btnCerrar;
    @FXML
    private Rectangle LineBar;
    @FXML
    private MaterialDesignIconView close;
    
    private void openWindowWithOption(String file) {
        Stage stage = new Stage();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource(file));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
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
    @FXML private void Operaciones(){
       openWindowWithOption("/OperacionesFlotantes/OperacionesFlotantes.fxml");
    }
    
    @FXML private void NewtonR(){
       openWindowWithOption("/NewtonR/Newton.fxml");
    }
    
    @FXML private void PuntoFijo(){
       
    }
    
    @FXML private void Secante(){
       
    }
    
    @FXML private void Binario(){
       openWindowWithOption("/BinaryMachine/FXMLBinary.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnCerrar.setOnMouseClicked((e)->{
        System.exit(0);  
        
        });
        btnCerrar.setOnMouseEntered((e)->{
         close.setStyle("-fx-fill:RED;");
         LineBar.setStyle("-fx-fill:RED;");
        });
         btnCerrar.setOnMouseExited((e)->{
         close.setStyle("-fx-fill:WHITE;");
        });
    }    
    
}

    
  

