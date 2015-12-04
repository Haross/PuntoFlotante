/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Biseccion;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private TextField txtFuncion, txtA, txtB, txtTol, txtN, txtRaiz;
    @FXML
    private TextArea txtArea;
    @FXML
    private Label lblArea;

    
    double p;

    private double f(double x) {

        double r = Math.pow(x, 3) - (7 * Math.pow(x, 2)) + (14 * x) - 6;
        return r;
    }

    @FXML
    public void calculoRaiz() {
        double a = Double.parseDouble(txtA.getText());
        double b = Double.parseDouble(txtB.getText());
        double tol = Double.parseDouble(txtTol.getText());
        int n = Integer.parseInt(txtN.getText());
        double p = a;
        int i = 0;
        double ba = 1;

        while (f(p) != 0 & i <= n & ba > tol) {
            double pa = p;
            p = (a + b) / 2;
            if (f(p) * f(a) > 0) {
                a = p;
            } else if (f(p) * f(a) < 0) {
                b = p;
            }
            i = i + 1;
            ba = Math.abs(p - pa) / p;
            
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
        }
        txtRaiz.setText(p+"");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}