import com.email.durgesh.Email;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.mail.MessagingException;


public class ForgetpasController implements Initializable {
    private Stage stage;
    @FXML TextField email;
    @FXML TextField newT;
    @FXML Label newL;
    @FXML Label codeL;
    @FXML TextField codeT;
    @FXML Text msgErr;
    private static int randCode=0;
    private int rand,max=9999,min=1000;
    private static String randCode1 = null;
    @FXML AnchorPane pane;
    @FXML Button buEntery;
    
//    private void update(Event e) throws IOException{
//        if(mysql.updatepass(email.getText(), newT.getText())){
//            msgErr.setText("تم التغيير بنجاح");
//            Parent root = FXMLLoader.load(getClass().getResource("sc2.fxml"));
//            stage =(Stage)((Node)e.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root);
//
//            stage.setScene(scene);
//            stage.show();
//        }
//        else
//            msgErr.setText("اسم المستخدم غير صحيح");
//    }
//     @FXML
//    private void cond(KeyEvent e) throws MessagingException, UnsupportedEncodingException{
//        Alert alert;
//        if(mysql.Evalidation(email.getText()) & e.getCode()== KeyCode.ENTER){
//                codeL.setVisible(true);
//                codeT.setVisible(true);
//                Email email1 = new Email("CSJavaLab.2@gmail.com", "@SecurityLab2"); //sender email
//        email1.setFrom("CSJavaLab.2@gmail.com", "haddaf boot"); //from my boot email
//        email1.setSubject("welcome to haddaf"); //email subject
//        randCode=(int)Math.floor(Math.random()*(max-min+1)+min); //generate random code
//        email1.setContent("<h1>please enter this code : "+randCode +" </h1>", "text/html");//content of the message
//        email1.addRecipient(email.getText()); //user email
//        email1.send();
////        randCode1=""+randCode;
//        if(e.getCode()== KeyCode.ENTER & codeT.getText().equals(randCode)){
//                newL.setVisible(true);
//                newT.setVisible(true);
//        }else{
//                alert = new Alert(Alert.AlertType.ERROR,"الرقم الذي ادخلته غير صحيح" ,ButtonType.OK);
//                alert.showAndWait();
//            }
//        }
//    }
    @FXML
    public void check1(KeyEvent e) throws MessagingException, UnsupportedEncodingException, IOException{
       if (e.getCode()== KeyCode.ENTER){
           forget1();
       }
    }
    @FXML
    public void check2(KeyEvent e) throws MessagingException, UnsupportedEncodingException, IOException{
       if (e.getCode()== KeyCode.ENTER){
           forget2();
       }
    }
    @FXML
    public void check3(KeyEvent e) throws IOException {
       if (e.getCode()== KeyCode.ENTER){
           forget3();
       }
    }
     @FXML
    private void back (MouseEvent e) throws IOException{
        Parent Scene = FXMLLoader.load(getClass().getResource("sc2.fxml"));
        Scene s11 = new Scene (Scene);
        Stage window = (Stage)(((Node)e.getSource()).getScene().getWindow());
        window.setScene(s11);
        window.show();
    }
     
    @FXML
    public void vis(Event e) throws MessagingException, UnsupportedEncodingException, IOException{
        if(newT.isVisible())
            forget3();
        else if(codeT.isVisible()){
                forget2();}
                else if(email.isVisible()){
                        forget1();
                        }
       
                
        
    }
    public void forget1() throws MessagingException, UnsupportedEncodingException{
        Alert alert;
        if(mysql.Evalidation(email.getText())){
                codeL.setVisible(true);
                codeT.setVisible(true);
                Email email1 = new Email("CSJavaLab.2@gmail.com", "@SecurityLab2"); //sender email
        email1.setFrom("CSJavaLab.2@gmail.com", "haddaf boot"); //from my boot email
        email1.setSubject("welcome to haddaf"); //email subject
        randCode=(int)Math.floor(Math.random()*(max-min+1)+min); //generate random code
        email1.setContent("<h1>please enter this code : "+randCode +" </h1>", "text/html");//content of the message
        email1.addRecipient(email.getText()); //user email
        email1.send();
        buEntery.setText(" سيصلك كود التحقق في ثواني");
        randCode1=""+randCode;
        }
        else{
            alert = new Alert(Alert.AlertType.ERROR, "ادخل بريد صحيح !" ,ButtonType.OK);
            alert.setTitle("خطأ");
            alert.setHeaderText("البريد الالكتروني غير صحيح ");
            alert.showAndWait();
        }
    }
    public void forget2(){
        Alert alert;
        System.out.println(codeT.getText());
        System.out.println(randCode1);
        if( codeT.getText().equals(randCode1)){
                newL.setVisible(true);
                newT.setVisible(true);
                buEntery.setText("أهلا بكلمة المرور الجديدة");
        }
        else{
            alert = new Alert(Alert.AlertType.ERROR,"ادخل كود صحيح !" ,ButtonType.OK);
            alert.setTitle("خطأ");
            alert.setHeaderText("الكود غير صحيح ");
            alert.showAndWait();
        }
    }

    public void forget3() throws IOException{
        mysql.updatepass(email.getText(), newT.getText());
            msgErr.setText("تم التغيير بنجاح");
            Parent root = FXMLLoader.load(getClass().getResource("signHome.fxml"));
            stage =(Stage)pane.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
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
