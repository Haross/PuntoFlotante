/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BinaryMachine;

import Interpretador.Interpretador;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author W8
 */
public class FXMLBinaryController implements Initializable {

    @FXML
    private Button btnCalcular;
    @FXML
    private TextField txtS, txtF, txtR, txtC;
    @FXML
    private TextArea txtArea;
    

    String f;

    /**
     * Metodo que verifica que ingresen 1 o 0
     * @param d se refiere a los numeros ingresados
     * @return false si no es un 1 o 0
     */
    private boolean verifica(String d) {
        //String d = txtC.getText();
        System.out.println(d.length());
        System.out.println(d);
        for (int i = 0; i < d.length(); i++) {
            if ((d.charAt(i) == '1')) {
                return true;
            } else {
                if ((d.charAt(i) == '0')) {
                    return true;
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Info");
                    alert.setHeaderText(null);
                    alert.setContentText("Solo se permiten 0 y 1");
                    alert.showAndWait();
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Metodo principal en el que se realizan los calculos
     *
     * @param e Evento que se utiliza para realizar el calculo
     */
    @FXML
    public void principal(ActionEvent e) {
        String c = txtC.getText();
        this.f = txtF.getText();
        if (verifica(c) && verifica(f)) {
            double r;
            int s = Integer.parseInt(txtS.getText());
            int exp = Integer.parseInt(c, 2);
            double c1 = exp - 1023;
            double m1 = 1 + matiza();
            r = revisaS(s) * Math.pow(2, c1) * m1;
            txtR.setText(r + "");
            txtArea.setText(txtArea.getText() + '\n' + "Resultado = " + revisaS(s) + "*" + Math.pow(2, c1) + "*" + m1);
        }
    }

    /**
     * Metodo que da el limite de numeros ingresados en S
     *
     * @param e Recibe el evento de teclado
     */
    @FXML
    private void LimtxtAreaS(KeyEvent e) {

        if (txtS.getText().length() >= 1) {

            e.consume();
        }
    }

    /**
     * Metodo que da el limite de numeros ingresados en C
     *
     * @param e Recibe el evento de teclado
     */
    @FXML
    private void LimtxtAreaC(KeyEvent e) {
        if (txtC.getText().length() >= 11) {
            e.consume();
        }
    }

    /**
     * Metodo que da el limite de numeros ingresados en F
     *
     * @param e Recibe el evento de teclado
     */
    @FXML
    private void LimtxtAreaF(KeyEvent e) {
        if (txtF.getText().length() >= 52) {
            e.consume();
        }
    }

    /**
     * Metodo que limpia los componentes
     *
     * @param e
     */
    @FXML
    private void Borrar(ActionEvent e) {
        txtS.setText("");
        txtC.setText("");
        txtR.setText("");
        txtF.setText("");
        txtArea.setText("");
    }

    /**
     * Metodo que verifica si la S es un 0 o 1
     *
     * @param s recibe el numero ingresado
     * @return retorna el calculo con respecto a 1 o -1
     */
    public double revisaS(int s) {
        double s1 = s;
        if (s == 0) {
            s1 = Math.pow(1, s);
        } else {
            if (s == 1) {
                s1 = Math.pow(-1, s);
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Info");
                alert.setHeaderText(null);
                alert.setContentText("Solo se permiten 0 y 1");
                alert.showAndWait();
            }
        }

        System.out.println(s1);
        return s1;
    }

    /**
     * Metodo que realiza los calculos de la matiza
     *
     * @return retorna el resultado del calculo de F
     */
    public double matiza() {
        double m = 0.000;
        for (int i = 0; i < f.length(); i++) {
            if (f.charAt(i) == '1') {
                m = m + Math.pow((0.5), i + 1);
                txtArea.setText(txtArea.getText() + "\n(1/2)^" + (i + 1) + " = " + m);
            }
        }
        return m;
    }
    
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
