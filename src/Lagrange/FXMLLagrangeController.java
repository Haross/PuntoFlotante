package Lagrange;

import Interpretador.Interpretador;
import com.jfoenix.controls.JFXCheckBox;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Javier
 */
public class FXMLLagrangeController implements Initializable {

    @FXML
    private ComboBox numNodos;
    @FXML
    private Label lblx0, lbly0, lblx1, lbly1, lblx2, lbly2, lblx3, lbly3;
    @FXML
    private TextField txtx0, txty0, txtx1, txty1, txtx2,txtFuncion, txty2, txtx3, txty3;
    @FXML
    private TextArea txtArea;
    Interpretador interpretador = new Interpretador();
    
    
    @FXML
    private JFXCheckBox checkY;
    private BigDecimal x0, y0, x1, y1, x2, y2, x3, y3;
    private String num = "";

    @FXML
    public void calculo() {
        txtArea.setText("");
        switch(num){
            case "2":
                getP1();
                break;
            case "3":
                getP2();
                break;
            case "4":
                getP3();
                break;
        }
        
    }
    
     public double getValor(String value){
        Interpretador in = new Interpretador();
        String aux = value.replace("-","!");
         System.out.println("val"+aux);
        in.setFuncion(aux);
        double auxValor = in.getResultado();
        System.out.println("val"+auxValor);
        in.setFuncion(txtFuncion.getText());
         aux = value.replace("-","!");
        return in.getResultado(aux);
    }
    
   
    public void getXY() {
        System.out.println("getXY");
        int i = Integer.parseInt(numNodos.getValue().toString());
        for (int j = 1; j <= i; j++) {
            try {
                switch (j) {
                    case 1:
                        x0 = new BigDecimal(txtx0.getText());
                        if(checkY.isSelected())
                            y0 = new BigDecimal(txty0.getText());
                        else
                            y0 = new BigDecimal(getValor(txtx0.getText()));
                        System.out.println("hol"+y0);
                        break;
                    case 2:
                        x1 = new BigDecimal(txtx1.getText());
                         if(checkY.isSelected())
                            y1 = new BigDecimal(txty1.getText());
                        else
                            y1 = new BigDecimal(getValor(txtx1.getText()));
                        break;
                    case 3:
                        x2 = new BigDecimal(txtx2.getText());
                        if(checkY.isSelected())
                            y2 = new BigDecimal(txty2.getText());
                        else
                            y2 = new BigDecimal(getValor(txtx2.getText()));
                        break;
                    case 4:

                        x3 = new BigDecimal(txtx3.getText());
                         if(checkY.isSelected())
                            y3 = new BigDecimal(txty3.getText());
                        else
                            y3 = new BigDecimal(getValor(txtx3.getText()));
                        break;
                }
            } catch (Exception e) {

            }
        }

    }

    public BigDecimal getDivision(BigDecimal a, BigDecimal b) {
        MathContext mc = new MathContext(10, RoundingMode.DOWN);
        /*  if(tipo == 2){
         mc = new MathContext(k,  RoundingMode.HALF_EVEN);
                   
         }*/
        System.out.println(a);
        System.out.println(b+"hola");
        return a.divide(b, mc);

    }
    
    public void getP1(){
        getXY();
        BigDecimal a = x0.subtract(x1);
        BigDecimal b = x1.subtract(x0);
        BigDecimal a1 = getDivision(new BigDecimal(1), a);
        BigDecimal b1 = getDivision(new BigDecimal(1), b);
        txtArea.setText("L0 = (x - " + x1 + ")* " + a1);
        txtArea.setText(txtArea.getText() + "\n\nL1 = (x - " + x0 + ")  *" + b1);
         a = getDivision(y0, a);
        b = getDivision(y1, b);
        
        BigDecimal c1 = a.add(b);
        BigDecimal c2 =  a.multiply(x1).add(b.multiply(x0)).multiply(new BigDecimal(-1));
        if (isNegative(c1)) {
            txtArea.setText(txtArea.getText() + "\n\n" + c1 + "x  ");
        } else {
            txtArea.setText(txtArea.getText() + "\n\n" + c1 + "x  ");
        }
        if (isNegative(c2)) {
            txtArea.setText(txtArea.getText() + c2 );
        } else {
            txtArea.setText(txtArea.getText() +"+"+ c2 );
        }
        
    }

    public void getP2() {
        getXY();
        BigDecimal a = x0.subtract(x1).multiply(x0.subtract(x2));

        BigDecimal b = x1.subtract(x0).multiply(x1.subtract(x2));

        BigDecimal c = x2.subtract(x0).multiply(x2.subtract(x1));

        BigDecimal a1 = getDivision(new BigDecimal(1), a);
        BigDecimal b1 = getDivision(new BigDecimal(1), b);
        BigDecimal c1 = getDivision(new BigDecimal(1), c);
        txtArea.setText("L0 = (x - " + x1 + ") * (x - " + x2 + ") *" + a1);
        txtArea.setText(txtArea.getText() + "\n\nL1 = (x - " + x0 + ") * (x - " + x2 + ") *" + b1);
        txtArea.setText(txtArea.getText() + "\n\nL2 = (x - " + x0 + ") * (x - " + x1 + ")  *" + c1);

        a = getDivision(y0, a);
        b = getDivision(y1, b);
        c = getDivision(y2, c);

        c1 = a.add(b).add(c);
        BigDecimal c2 = a.multiply(x1.add(x2)).add(b.multiply(x0.add(x2))).add(c.multiply(x0.add(x1))).multiply(new BigDecimal(-1));
        BigDecimal c3 = a.multiply(x1).multiply(x2).add(b.multiply(x0).multiply(x2)).add(c.multiply(x0).multiply(x1));

        if (isNegative(c1)) {
            txtArea.setText(txtArea.getText() + "\n\n" + c1 + "x^2  ");
        } else {
            txtArea.setText(txtArea.getText() + "\n\n" + c1 + "x^2  ");
        }
        if (isNegative(c2)) {
            txtArea.setText(txtArea.getText() + c2 + "x ");
        } else {
            txtArea.setText(txtArea.getText() +"+"+ c2 + "x  ");
        }
        if (isNegative(c3)) {
            txtArea.setText(txtArea.getText() + c3);
        } else {
            txtArea.setText(txtArea.getText() +"+"+ c3);
        }

    }
    public static boolean isNegative(BigDecimal b)
    {
        return b.signum() == -1;
    }

    public void getP3() {

        getXY();
        BigDecimal a = x0.subtract(x1).multiply(x0.subtract(x2)).multiply(x0.subtract(x3));
        BigDecimal b = x1.subtract(x0).multiply(x1.subtract(x2)).multiply(x1.subtract(x3));
        BigDecimal c = x2.subtract(x0).multiply(x2.subtract(x1)).multiply(x2.subtract(x3));
        BigDecimal d = x3.subtract(x0).multiply(x3.subtract(x1)).multiply(x3.subtract(x2));

        BigDecimal a1 = getDivision(new BigDecimal(1), a);
        BigDecimal b1 = getDivision(new BigDecimal(1), b);
        BigDecimal c1 = getDivision(new BigDecimal(1), c);
        BigDecimal d1 = getDivision(new BigDecimal(1), d);
        txtArea.setText("L0 = (x - " + x1 + ") * (x - " + x2 + ") * (x - +" + x3 + ") *" + a1);
        txtArea.setText(txtArea.getText() + "\n\nL1 = (x - " + x0 + ") * (x - " + x2 + ") * (x - +" + x3 + ") *" + b1);
        txtArea.setText(txtArea.getText() + "\n\nL2 = (x - " + x0 + ") * (x - " + x1 + ") * (x - +" + x3 + ") *" + c1);

        txtArea.setText(txtArea.getText() + "\n\nL3 = (x - " + x0 + ") * (x - " + x1 + ") * (x - +" + x2 + ") *" + d1);
        a = getDivision(y0, a);
        b = getDivision(y1, b);
        c = getDivision(y2, c);
        d = getDivision(y3, d);

        c1 = a.add(b).add(c).add(d);
        BigDecimal c2 = a.multiply(x1.add(x2).add(x3)).add(b.multiply(x0.add(x2).add(x3)))
                .add(c.multiply(x0.add(x1).add(x3))).add(d.multiply(x0.add(x1).add(x2))).abs().multiply(new BigDecimal(-1));
        BigDecimal c3 = a.multiply(x1.multiply(x2).add(x1.multiply(x3)).add(x2.multiply(x3)))
                .add(b.multiply(x0.multiply(x2).add(x0.multiply(x3)).add(x2.multiply(x3))))
                .add(c.multiply(x0.multiply(x1).add(x0.multiply(x3)).add(x1.multiply(x3))))
                .add(d.multiply(x0.multiply(x1).add(x0.multiply(x2)).add(x1.multiply(x2))));
        BigDecimal c4 = a.multiply(x1).multiply(x2).multiply(x3)
                .add(b.multiply(x0).multiply(x2).multiply(x3))
                .add(c.multiply(x0).multiply(x1).multiply(x3))
                .add(d.multiply(x0).multiply(x1).multiply(x2)).multiply(new BigDecimal(-1));

        System.out.println("1.  " + a.multiply(x1).multiply(x2).multiply(x3));
        System.out.println(a);
        System.out.println(a.multiply(x1));
        System.out.println(a.multiply(x1).multiply(x2));
        System.out.println(a.multiply(x1).multiply(x2).multiply(x3));

        if (isNegative(c1)) {
            txtArea.setText(txtArea.getText() + "\n\n" + c1 + "x^3  ");
        } else {
            txtArea.setText(txtArea.getText() + "\n\n" + c1 + "x^3   ");
        }
        if (isNegative(c2)) {
            txtArea.setText(txtArea.getText() + c2 + "x^2  ");
        } else {
            txtArea.setText(txtArea.getText() + c2 +"+"+ "x^2  ");
        }
        if (isNegative(c3)) {
            txtArea.setText(txtArea.getText() + c3 + "x  ");
        } else {
            txtArea.setText(txtArea.getText() + c3 +"+"+ "x  ");
        }
        if (isNegative(c4)) {
            txtArea.setText(txtArea.getText() + c4);
        } else {
            txtArea.setText(txtArea.getText() +"+"+ c4);
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        setInvisibleX();
                    setInvisibleY();
        inicializarCombo();
        inicializarCheckY();
    }
    public void inicializarCheckY(){
        checkY.setOnAction((e)->{
            check();
        });
        
    }
    private void check(){
        if(checkY.isSelected()){
            switch(numNodos.getValue().toString()){
                 case "4":
                lbly3.setVisible(true);
                txty3.setVisible(true);
                lbly2.setVisible(true);
                txty2.setVisible(true);
                lbly1.setVisible(true);
                txty1.setVisible(true);
                  lbly0.setVisible(true);
                  txty0.setVisible(true);
                break;
            case "3":
                lbly2.setVisible(true);
                   txty2.setVisible(true);
                   lbly1.setVisible(true);
                   txty1.setVisible(true);
                    lbly0.setVisible(true);
                    txty0.setVisible(true);
                break;
            case "2":
                  lbly1.setVisible(true);
                   txty1.setVisible(true);
                    lbly0.setVisible(true);
                     txty0.setVisible(true);
                break;
            }}else{
                setInvisibleY();
            }
    }

    private void setInvisibleX() {
        lblx3.setVisible(false);
        
        txtx3.setVisible(false);
        
        lblx2.setVisible(false);
        
        txtx2.setVisible(false);
        
        lblx1.setVisible(false);
       
        txtx1.setVisible(false);
       
        lblx0.setVisible(false);
        
        txtx0.setVisible(false);
       
    }
    private void setInvisibleY(){
       
        lbly3.setVisible(false);
        
        txty3.setVisible(false);
       
        lbly2.setVisible(false);
        
        txty2.setVisible(false);
        
        lbly1.setVisible(false);
        
        txty1.setVisible(false);
      
        lbly0.setVisible(false);
        
        txty0.setVisible(false);
    }


    

    private void inicializarCombo() {

        numNodos.setOnAction((e) -> {
            num = numNodos.getValue().toString();
            switch (num) {
                case "4":
                    lblx3.setVisible(true);                   
                    txtx3.setVisible(true);                   
                    lblx2.setVisible(true);                   
                    txtx2.setVisible(true);                   
                    lblx1.setVisible(true);                   
                    txtx1.setVisible(true);                  
                    lblx0.setVisible(true);              
                    txtx0.setVisible(true);     
                   check();
                    break;
                case "3":
                    setInvisibleX();
                    setInvisibleY();
                    lblx2.setVisible(true);                    
                    txtx2.setVisible(true);                 
                    lblx1.setVisible(true);                    
                    txtx1.setVisible(true);                    
                    lblx0.setVisible(true);                  
                    txtx0.setVisible(true);
                   check();
                    break;
                case "2":
                    setInvisibleX();
                    setInvisibleY();
                    lblx1.setVisible(true);                
                    txtx1.setVisible(true);                   
                    lblx0.setVisible(true);                   
                    txtx0.setVisible(true);
                    check();
                    break;
            }
        });
    }

}
