import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;
import com.email.durgesh.Email;
import java.io.IOException;
import java.util.Properties;
import javafx.event.Event;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;



public class Sc3Controller implements Initializable {
    @FXML TextField  nameof3;
    @FXML TextField  Email;
    @FXML TextField  passward;
    @FXML TextField  phone;
    @FXML RadioButton first;
    @FXML RadioButton sec;
    @FXML RadioButton top;
    @FXML ToggleGroup level;
    @FXML Label msg;
    private Stage stage;
    @FXML TextField label1;
    static int randCode=0;
    int rand,max=9999,min=1000;
    static String randCode1 = null;
    
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
          emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    @FXML
    private void newaccount (Event e) throws IOException, MessagingException{
        RadioButton selected = (RadioButton)level.getSelectedToggle();
        if(nameof3.getText().isEmpty()){
            msg.setText("يجب إدخال اسمك الثلاثي ");
        } else if(isValidEmailAddress(Email.getText())==false){
            System.out.println("error");
        } else if(!Email.getText().endsWith("@gmail.com")){
            System.out.println("error");
        } else if(Email.getText().isEmpty()){
            msg.setText("يجب إدخال البريد الالكتروني ");
        } else if (passward.getText().isEmpty()){
            msg.setText("كلمة المرور لا يمكن ان تكون فارغة");
        } else if (phone.getText().isEmpty()){
            msg.setText("رقم الجوال لا يمكن أن يكون فارغا");
        } else if (phone.getText().length()!=10){
            msg.setText("رقم الجوال يجب أن يكون عشرة أرقام تماماً");
        }else if (selected.getText().isEmpty()){
            msg.setText("يجب عليك اختيار مستوى !");
        } else { 
            addToDB(selected.getText());
            homePage(e);
            Email email = new Email("CSJavaLab.2@gmail.com", "@SecurityLab2"); //sender email
            email.setFrom("CSJavaLab.2@gmail.com", "haddaf boot"); //from my boot email
            email.setSubject("welcome to haddaf"); //email subject
            randCode=(int)Math.floor(Math.random()*(max-min+1)+min); //generate random code
            randCode1=""+randCode;
            email.setContent("<h1>please enter this code : "+randCode +" </h1>", "text/html");//content of the message
            email.addRecipient(Email.getText()); //user email
            email.send();
       }
    }
      @FXML
    private void back(MouseEvent e) throws IOException{
        Stage stage =(Stage)((Node)e.getSource()).getScene().getWindow();
        String page ;
        page = "sc2.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(page));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   }
    @FXML 
    private void enter(KeyEvent e ) throws Exception{
        if (e.getCode() == KeyCode.ENTER)
            newaccount((Event)e);
    }
    
    private void addToDB(String selected){
        msg.setText(mysql.insertToUser( Email.getText(),nameof3.getText(),
                passward.getText(),phone.getText(),selected));
    }
    
    private void homePage(Event e ) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("check.fxml"));////هوووممم بييييجججج
        stage =(Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        username.textProperty().bind(sc2Controller.textName.textProperty());
    }    
    
    @FXML 
    private void hover (Event e ) { 
        ((Node)e.getSource()).setEffect(new DropShadow (BlurType.GAUSSIAN , Color.rgb(0, 0, 0, 0.3), 10, 0.5 , 0.0, 0.0));
    }
    
    @FXML
    private void realese (Event e ) { 
        ((Node)e.getSource()).setEffect(null);
    }
    
}
