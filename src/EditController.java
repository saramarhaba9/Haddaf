/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import com.email.durgesh.Email;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * FXML Controller class
 *
 * @author Manar
 */
public class EditController implements Initializable {
    
    @FXML TextField name1;
    @FXML TextField email;
    @FXML TextField number;
    @FXML TextField level1;
    @FXML ToggleGroup level;
    @FXML Label msg;
    private double sec = 0.5;
    private String username ;


    public void set(Stage s){
        this.username = s.getUserData().toString();
    }

    public void getinfo(){
        System.out.println(username);
        User u = mysql.UserData(username);
        email.setText(u.getUsername());
        name1.setText(u.getName());
        number.setText(u.getMobile());
        level1.setText(u.getUserLevel());
    }
    
    @FXML
    private void change (Event e) throws IOException, MessagingException{
        RadioButton selected = (RadioButton)level.getSelectedToggle();
        if(name1.getText().isEmpty()){
            msg.setText("يجب إدخال اسمك الثلاثي ");
        } else if (number.getText().isEmpty()){
            msg.setText("رقم الجوال لا يمكن أن يكون فارغا");
        } else if (number.getText().length()!=10){
            msg.setText("رقم الجوال يجب أن يكون عشرة أرقام تماماً");
        }else if (selected.getText().isEmpty()){
            msg.setText("يجب عليك اختيار مستوى !");
        } else { 
            level1.setText(selected.getText());
            addToDB(selected.getText());
       }
    }
      @FXML
    private void back(MouseEvent e) throws IOException{
        Stage stage =(Stage)((Node)e.getSource()).getScene().getWindow();
        String page ;
        page = "signHome.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(page));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   }
    
    @FXML 
    private void enter(KeyEvent e ) throws Exception{
        if (e.getCode() == KeyCode.ENTER)
            change((Event)e);
    }
    
    private void addToDB(String selected){
        msg.setText(mysql.updateUser(email.getText(), name1.getText(), number.getText(), selected));
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
        // TODO
        Timeline timer = new Timeline ();
            timer.setCycleCount(1);//Timeline.INDEFINITE
            if (timer!=null)
                timer.stop();
            KeyFrame frame = new KeyFrame (Duration.seconds(1), e->{
                sec= sec-1; 
                System.out.println(sec);
                if (sec <= 0){
                    timer.stop();
                    getinfo();
                }
            });
            timer.getKeyFrames().add(frame);
            timer.playFromStart();
    }    
    
}
