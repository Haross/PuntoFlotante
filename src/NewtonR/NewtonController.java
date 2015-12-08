package NewtonR;

import Interpretador.Interpretador;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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
    private TextField txtFuncion, txtFuncionD, txtK, txtP0, txtTol, txtN, txtRaiz;
    @FXML
    private TextArea txtArea;
    @FXML
    private RadioButton rbtnTruncamiento, rbtnRedondeo;
    Interpretador interpretador;
    Interpretador interpretadorD;
    /**
     * Metoto para limpiar los rectangulos blancos
     * @param e Evento cuando se presiona el boton 
     */
    @FXML
    private void Borrar(ActionEvent e){
        txtFuncion.setText("");
        txtFuncionD.setText("");
        txtP0.setText("");
        txtTol.setText("");
        txtN.setText("");
        txtRaiz.setText("");
        txtK.setText("");
        txtArea.setText("");
    }
     /**
     * Metodo que recibe un valor a evaluar en la funcion principal
     * Si el valor es menor que 0 significa que se tiene que hace un cambio el cual es
     * !x = -x 
     * @param x recibe un valor que se evaluara en la funcion
     * @return retorna el resultado de la funcion
     */
    private double f(double x) {
        if(x<0){
            double x1 = x*-1;
            return interpretador.getResultado("!"+x1);
        }
       return interpretador.getResultado(x);
    }
    /**
     * Metodo que recibe un valor a evaluar en la funcion derivada 
     * Si el valor es menor que 0 significa que se tiene que hace un cambio el cual es
     * !x = -x 
     * @param x valor que se evaluara en la funcion derivada
     * @return retorna el resultado de la funcion
     */
    private double fd(double x) {
        if(x<0){
            double x1 = x*-1;
            return interpretador.getResultado("!"+x1);
        }
       return interpretadorD.getResultado(x);
    }
    /**
     * Metodo para obtener la K
    */
    private void getK(){
        if(txtK.getText() != ""){
            interpretador.setK(Integer.parseInt(txtK.getText()));
        }
    }
    /**
     * Metodo que seleccion el tipo operacion que se efecturara
    */
    public void setType(){
        if(rbtnTruncamiento.isSelected()){
                interpretador.setTipoValores(1);
        }else{
                interpretador.setTipoValores(2);
        }
       getK();
    }
    /**
     * Metodo que comprueba que las funciones ingresadas este correctamente escrita
     */
    private void setFuncion(){
         if(!interpretador.checarParentesis(txtFuncion.getText()) && !interpretadorD.checarParentesis(txtFuncionD.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Información");
                alert.setContentText("Error de sintaxis. Verifique que haya escrito la función correctamente");
                alert.showAndWait();
            }else{
                interpretador.setFuncion(txtFuncion.getText());
                interpretadorD.setFuncion(txtFuncionD.getText());
            }
    }
    
    /**
     *Metodo que calcula raiz mediante Newton Raphson
     */
    @FXML
    public void calculoRaiz() {
        setType();
        setFuncion();
         //Primero limpiamos el text area para eliminar lo que se calculo anteriormente.
        txtArea.setText("");
        double tol = Double.parseDouble(txtTol.getText());
        int n = Integer.parseInt(txtN.getText());
        int i = 1;
        double P0 = Double.parseDouble(txtP0.getText());
        double P;
        while (i < n) {            
            P = P0 - (f(P0)/fd(P0));
            if (Math.abs(P - P0) < tol) {
                txtRaiz.setText(P+"");
                 return;
            }
            txtArea.setText(txtArea.getText()+"\n\tIteracion " + i
            + "\n\t" + "P= " +P+"\n\t" + "P0= " + P0);
            i = i +1;
            P0 = P;
        }
    }
    /**
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        interpretador = new Interpretador();
        interpretadorD = new Interpretador();

    }    
    
}
