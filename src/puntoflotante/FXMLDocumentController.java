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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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
    private Rectangle LineBar, root, Title;
    @FXML
    private Button btnBinario, btnSecante,btnPuntoFijo,btnFalsaPosicion,btnNewton,btnOperaciones,btnBiseccion,btnLagrange;
    @FXML
    private MaterialDesignIconView close;
    private double xOffset = 0;
    private double yOffset = 0;
    private void openWindowWithOption(String file) {
        Stage stage = new Stage();
        Parent root = null;
        String[] extesion = new String[2];
        extesion = file.split("\\.");
        System.out.println("Extesion[0]: "+extesion[0]);
        if (extesion[0].equals("/Biseccion/FXMLBiseccion")) {
            stage.setTitle("Biseccion");
            stage.setResizable(false);
        }
        if (extesion[0].equals("/FalsePosition/FXMLFalsePosition")) {
            stage.setTitle("Falsa Posicion");
            stage.setResizable(false);
        }
        if (extesion[0].equals("/OperacionesFlotantes/OperacionesFlotantes")) {
            stage.setTitle("Operaciones Flotantes");
            stage.setResizable(false);
        }
        if (extesion[0].equals("/NewtonR/Newton")) {
            stage.setTitle("Newton Raphson");
            stage.setResizable(false);
        }
        if (extesion[0].equals("/PuntoFijo/Static")) {
            stage.setTitle("Punto Fijo");
            stage.setResizable(false);
        }
        if (extesion[0].equals("/Secante/FXMLSecante")) {
            stage.setTitle("Secante");
            stage.setResizable(false);
        }
        if (extesion[0].equals("/BinaryMachine/FXMLBinary")) {
            stage.setTitle("Maquina Binaria");
            stage.setResizable(false);
        }        
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
       openWindowWithOption("/PuntoFijo/Static.fxml");
    }
    
    @FXML private void Secante(){
       openWindowWithOption("/Secante/FXMLSecante.fxml");
    }
    
    @FXML private void Binario(){
       openWindowWithOption("/BinaryMachine/FXMLBinary.fxml");
    }
    @FXML private void Lagrange(){
       openWindowWithOption("/Lagrange/FXMLLagrange.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnCerrar.setOnMouseClicked((e)->{
        System.exit(0);  
        
        });
        btnCerrar.setOnMouseEntered((e)->{
         close.setStyle("-fx-fill:RED;");
         LineBar.setStyle("-fx-fill:RED;");
         Title.setStyle("-fx-fill:RED;-fx-opacity:0.5;");

        });
         btnCerrar.setOnMouseExited((e)->{
         close.setStyle("-fx-fill:WHITE;");
         LineBar.setStyle("-fx-fill:#4bc55d;");
         Title.setStyle("-fx-fill:BLACK;");
        Title.setStyle("-fx-opacity:0.75;");
        });
         setDragWindow();
      
    }    

    /**
     *
     */
    public void setDragWindow(){
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = btnCerrar.getScene().getWindow().getX() - event.getScreenX();
                yOffset = btnCerrar.getScene().getWindow().getY() - event.getScreenY();
               
            }
        });
      root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnCerrar.getScene().getWindow().setX(event.getScreenX() + xOffset);
                btnCerrar.getScene().getWindow().setY(event.getScreenY() + yOffset);
            }
        });
    }
    
}

    
  

