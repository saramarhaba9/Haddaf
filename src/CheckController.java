import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CheckController implements Initializable {
    private Stage stage;
    @FXML TextField chk;
    
    @FXML
    private void check(Event e) throws IOException{
        Alert alert;
        Parent root = FXMLLoader.load(getClass().getResource("signHome.fxml"));
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("sc2.fxml"));
        FXMLLoader loader3 = new FXMLLoader(getClass().getResource("sc3.fxml"));
        Sc2Controller sc2Controller = loader2.getController();
        Sc3Controller sc3Controller = loader3.getController();
        if(chk.getText().equals(Sc2Controller.randCode1)){
          stage =(Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
        else if(chk.getText().equals(Sc3Controller.randCode1)){
         stage =(Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
        else{
            alert = new Alert(Alert.AlertType.ERROR,"الرقم الذي ادخلته غير صحيح" ,ButtonType.OK);
            alert.showAndWait();
        }
        
    }
    
    @FXML 
    private void enter(KeyEvent e ) throws Exception{
        if (e.getCode() == KeyCode.ENTER)
            check(e);
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
    
    @FXML
    private void back(MouseEvent e) throws IOException{
        Stage stage =(Stage)((Node)e.getSource()).getScene().getWindow();
        String page = "sc2.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(page));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
