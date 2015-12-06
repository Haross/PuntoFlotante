/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BinaryMachine;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    
    @FXML
    public void principal(ActionEvent e) {
        double r;
        int s = Integer.parseInt(txtS.getText());
        String c = txtC.getText();
        this.f = txtF.getText();
        System.out.println("matisa" + f);
        int exp = Integer.parseInt(c, 2);
        System.out.println("exp: "+exp);
        System.out.println("C = " + exp);
        System.out.println("");
        double c1 = exp - 1023;
        System.out.println("C1: "+c1);
        double m1 = 1 + matiza();
        System.out.println("M1: "+m1);
        r = revisaS(s) * Math.pow(2, c1) * m1;
        txtR.setText(r+"");
        txtArea.setText(txtArea.getText()+'\n'+"Resultado = "+revisaS(s)+"*"+Math.pow(2, c1)+ "*" + m1);
        System.out.println("Resultado: " + r);
    }
    
    @FXML
    private void LimtxtAreaS (KeyEvent e){
        if (txtS.getText().length()>=1){
            e.consume();
        }
    }
    
    @FXML
    private void LimtxtAreaC(KeyEvent e){
        if (txtC.getText().length()>=11){
            e.consume();
        }
    }
    
    @FXML
    private void LimtxtAreaF (KeyEvent e){
        if (txtF.getText().length()>=52){
            e.consume();
        }
    }

    public double revisaS(int s) {
        double s1 = s;
        if (s == 0) {
            s1 = Math.pow(1, s);
        }
        if (s == 1) {
            s1 = Math.pow(-1, s);
        }
        System.out.println(s1);
        return s1;
    }

    public double matiza() {
        System.out.println("Matt: "+f);
        double m = 0.000;
        for (int i = 0; i < f.length(); i++) {
            if (f.charAt(i) == '1') {
                m = m + Math.pow((0.5), i + 1);
                System.out.println("(1/2)^" + (i + 1));
                txtArea.setText(txtArea.getText()+"\n(1/2)^" + (i + 1) +" = " + m);
            }
        }
        return m;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
