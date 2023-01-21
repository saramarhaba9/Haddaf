/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Sc5Controller implements Initializable {
    @FXML TextField numCode;
    @FXML Label msg;
    private Stage stage;
    
    @FXML
    private void go (Event e) throws IOException{
        if(numCode.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);   
            a.setTitle("خطأ");
            a.setContentText("تأكد من البيانات المدخلة");
            a.setTitle("خطأ");
            a.setHeaderText("البيانات المدخلة خاطئة ");
            a.showAndWait();
        } else if(mysql.cheackCode(numCode.getText())){
            FXMLLoader loader = new FXMLLoader (getClass().getResource("match.fxml"));
            Parent rooot = loader.load();
            matchController matchcontroller = loader.getController(); // scene 2 controler 
            matchcontroller.set(mysql.getMatchId(numCode.getText()));
            Stage window = (Stage)(((Node)e.getSource()).getScene().getWindow());
            window.setScene(new Scene(rooot));
            window.show();
        } else 
            msg.setText("الكود المدخل خاطئ");
    }
    
    @FXML 
    private void enter (KeyEvent event) throws IOException{
        if (event.getCode() == KeyCode.ENTER )
            go((Event)event);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML 
    private void hover (Event e ) { 
        ((Node)e.getSource()).setEffect(new DropShadow (BlurType.GAUSSIAN , Color.rgb(0, 0, 0, 0.3), 10, 0.5 , 0.0, 0.0));
    }
    
    @FXML
    private void realese (Event e ) { 
        ((Node)e.getSource()).setEffect(null);
    }
    
       @FXML
    private void back (MouseEvent e) throws IOException{
        Parent Scene = FXMLLoader.load(getClass().getResource("sc4.fxml"));
        Scene s11 = new Scene (Scene);
        Stage window = (Stage)(((Node)e.getSource()).getScene().getWindow());
        window.setScene(s11);
        window.show();
    }
}
