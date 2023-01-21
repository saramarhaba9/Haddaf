/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.email.durgesh.Email;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.mail.MessagingException;

public class Sc2Controller implements Initializable {
    private Stage stage;
    @FXML TextField textName;
    @FXML TextField texePass;
    @FXML Sc3Controller sc3Controller;
    static int randCode=0;
    int rand,max=9999,min=1000;
    static String randCode1 = null;
   
    @FXML
    private void forgetpas(MouseEvent e) throws IOException{
        Parent Scene = FXMLLoader.load(getClass().getResource("forgetpas.fxml"));
        Scene s11 = new Scene (Scene);
        Stage window = (Stage)(((Node)e.getSource()).getScene().getWindow());
        window.setScene(s11);
        window.show();
    }
     
    @FXML 
    private void enter(KeyEvent e ) throws Exception{
        if (e.getCode() == KeyCode.ENTER)
            buentery((Event)e);
    }
     
    @FXML
    private void creAcc(MouseEvent e) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("sc3.fxml"));
        stage =(Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
     @FXML
    private void back (MouseEvent e) throws IOException{
        Parent Scene = FXMLLoader.load(getClass().getResource("hh.fxml"));
        Scene s11 = new Scene (Scene);
        Stage window = (Stage)(((Node)e.getSource()).getScene().getWindow());
        window.setScene(s11);
        window.show();
    }
     
    
    @FXML
    private void buentery (Event e) throws IOException, MessagingException{
        if ( mysql.validation(textName.getText(),texePass.getText())==true){
            Parent root = FXMLLoader.load(getClass().getResource("check.fxml"));////هوووممم بييييجججج
            stage =(Stage)((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setUserData(textName.getText());
            stage.setScene(scene);
            stage.show();
            Email email = new Email("CSJavaLab.2@gmail.com", "@SecurityLab2"); //sender email
            email.setFrom("CSJavaLab.2@gmail.com", "haddaf boot"); //from my boot email
            email.setSubject("welcome to haddaf"); //email subject
            randCode=(int)Math.floor(Math.random()*(max-min+1)+min); //generate random code
            email.setContent("<h1>please enter this code : "+randCode +" </h1>", "text/html");//content of the message
            email.addRecipient(textName.getText()); //user email
            email.send();
            randCode1=""+randCode;
        } else if (mysql.validation(textName.getText(),texePass.getText())==false){
            Alert a = new Alert(AlertType.ERROR);   
            a.setTitle("خطأ");
            a.setHeaderText("البيانات المدخلة خطأ ");
            a.setContentText("تأكد من البيانات المدخلة");
            a.showAndWait();
        }
    }

    @FXML 
    private void hover (Event e ) { 
        ((Node)e.getSource()).setEffect(new DropShadow (BlurType.GAUSSIAN , Color.rgb(0, 0, 0, 0.3), 10, 0.5 , 0.0, 0.0));
    }
    
    @FXML
    private void realese (Event e ) { 
        ((Node)e.getSource()).setEffect(null);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
