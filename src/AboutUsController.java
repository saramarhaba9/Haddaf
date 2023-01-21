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
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class AboutUsController implements Initializable {

    @FXML
    private void back(MouseEvent e) throws IOException{
        Stage stage =(Stage)((Node)e.getSource()).getScene().getWindow();
        String page ;
        if ((String) stage.getUserData()!=null )
            page = "signHome.fxml";
        else 
            page = "hh.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(page));
        
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
        // TODO
    }    
    
}
