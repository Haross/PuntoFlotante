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
    
     Interpretador in = new Interpretador();
            
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
            return interpretadorD.getResultado("!"+x1);
        }
       return interpretadorD.getResultado(x);
    }
    /**
     * Metodo para obtener la K
    */
    private void getK(){
        if(txtK.getText() != ""){
            interpretador.setK(Integer.parseInt(txtK.getText()));
            in.setK(Integer.parseInt(txtK.getText()));
        }
    }
    /**
     * Metodo que seleccion el tipo operacion que se efecturara
    */
    public void setType(){
        if(rbtnTruncamiento.isSelected()){
                interpretador.setTipoValores(1);
                in.setTipoValores(1);
        }else{
                interpretador.setTipoValores(2);
                in.setTipoValores(2);
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
    
    public double getP0(){
        Interpretador in = new Interpretador();
        String aux = txtP0.getText().replace("-","!");
        in.setFuncion(aux);
        return in.getResultado();
    }
    
    
    public String getEcuacion(String a,String reemplazo, double valor){
        if(valor<0)
            return a.replace(reemplazo, "!"+valor);
        return a.replace(reemplazo,""+valor);
    }
    
    
    /**
     *Metodo que calcula raiz mediante Newton Raphson
     */
    @FXML
    public void calculoRaiz() {
        try{
            setType();
            setFuncion();
             //Primero limpiamos el text area para eliminar lo que se calculo anteriormente.
            txtArea.setText("");
            double tol = Double.parseDouble(txtTol.getText());
            int n = Integer.parseInt(txtN.getText());
            int i = 1;
            double P0 = getP0();
            double P;
            while (i < n) {  

                String ecuacion = "P0 -(f(P0)/fd(P0))";

                System.out.println("test11"+f(P0));
                System.out.println("test12"+fd(P0));

                if(f(P0)<0){
                    double aux = f(P0);
                    String aux2 = aux +"";
                    aux2 = aux2.replace("-","!");

                    ecuacion = ecuacion.replace("f(P0)", aux2);
                }else{
                    double aux = f(P0);

                    ecuacion = ecuacion.replace("f(P0)", ""+aux);
                }
                if(fd(P0)<0){
                    double aux = fd(P0);
                    String aux2 = aux +"";
                    aux2 = aux2.replace("-","!");

                    ecuacion = ecuacion.replace("fd(P0)", aux2);
                }else{
                    double aux = fd(P0);

                    ecuacion = ecuacion.replace("fd(P0)", ""+aux);
                }
                if(P0<0){
                    String a = P0+"";
                    a = a.replace("-","!");
                    ecuacion = ecuacion.replace("P0",a);
                }else{
                    ecuacion = ecuacion.replace("P0",P0+"");
                }
                System.out.println(ecuacion+"hola");
                in.setFuncion(ecuacion);

                P = in.getResultado();
                System.out.println(P);
                 txtArea.setText(txtArea.getText()+"\n\tIteracion " + i
                + "\n\t" + "P= " +P+"\n\t" + "P0= " + P0);
                if (Math.abs(P - P0) < tol) {
                    txtRaiz.setText(P+"");
                     return;
                }

                i = i +1;
                P0 = P;
            }
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Información");
            alert.setContentText("Error de sintaxis. Verifique que haya escrito la función correctamente");
            alert.showAndWait();
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
